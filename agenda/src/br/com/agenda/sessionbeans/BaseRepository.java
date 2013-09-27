package br.com.agenda.sessionbeans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.agenda.model.dao.DAO;

@Stateless
public abstract class BaseRepository<T extends Serializable> implements DAO<T, Serializable> {

	private Connection conn;

	@Resource
	private SessionContext context;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Class<T> classe) {
		List<T> list = this.em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e")
		        .getResultList();
		return list;
	}

	@Override
	public T getById(Class<T> classe, Serializable id) {
		T entity = this.em.find(classe, id);
		return entity;
	}

	public Connection getConn() {
		return conn;
	}

	public SessionContext getContext() {
		return context;
	}

	@Override
	public void remove(T entity) throws Exception {
		this.em.remove(entity);
	}

	@Override
	public T save(T entity) throws Exception {
		this.em.persist(entity);
		return entity;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setContext(SessionContext context) {
		this.context = context;
	}

	@Override
	public T update(T entity) throws Exception {
		this.em.merge(entity);
		return entity;
	}

	protected EntityManager getEm() {
		return em;
	}

	protected void setEm(EntityManager em) {
		this.em = em;
	}

	String format(String cpfCnpj) {
		if (cpfCnpj == null || "".equals(cpfCnpj)) {
			return "";
		}
		return cpfCnpj.length() == 11 ? cpfCnpj.substring(0, 3) + "." + cpfCnpj.substring(3, 6)
		        + "." + cpfCnpj.substring(6, 9) + "-" + cpfCnpj.substring(9, 11) : cpfCnpj
		        .substring(0, 2)
		        + "."
		        + cpfCnpj.substring(2, 5)
		        + "."
		        + cpfCnpj.substring(5, 8)
		        + "/" + cpfCnpj.substring(8, 12) + "-" + cpfCnpj.substring(12, 14);
	}

	@PreDestroy
	private void destroy() {
		try {
			getConn().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	private void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "root");
			setConn(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
