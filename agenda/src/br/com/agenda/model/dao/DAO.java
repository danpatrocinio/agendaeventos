package br.com.agenda.model.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, I extends Serializable> {

	public List<T> getAll(Class<T> classe);

	public T getById(Class<T> classe, I id);

	public void remove(T entity) throws Exception;

	public T save(T entity) throws Exception;

	public T update(T entity) throws Exception;
}
