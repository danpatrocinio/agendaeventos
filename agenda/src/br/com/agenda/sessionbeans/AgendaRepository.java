package br.com.agenda.sessionbeans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.agenda.entity.Agenda;

@Stateless
public class AgendaRepository extends BaseRepository<Agenda> {

	@EJB
	AgendaConvidadoRepository agendaConvidadoRep;

	public ResultSet getAgendasByDate() throws SQLException, ClassNotFoundException {

		Statement stmt = getConn().createStatement();

		ResultSet rs = stmt.executeQuery("SELECT i_agenda, i_agenda_original, i_pessoa, "
		        + "local, descricao, dh_inicio, dh_fim, "
		        + "DATE(dh_inicio) as dtinicio, DATE(dh_fim) as dtfim from agendas");

		return rs;
	}

	public ResultSet getAgendasByDate(Long iPessoa) throws SQLException, ClassNotFoundException {

		if (iPessoa == null) {
			return getAgendasByDate();
		}

		Statement stmt = getConn().createStatement();

		ResultSet rs = stmt.executeQuery("SELECT i_agenda, i_agenda_original, agendas.i_pessoa, "
		        + "local, descricao, dh_inicio, dh_fim, cpf_cnpj, nome, "
		        + "DATE(dh_inicio) as dtinicio, DATE(dh_fim) as dtfim FROM agendas "
		        + "JOIN pessoas ON (agendas.i_pessoa = pessoas.i_pessoa) WHERE agendas.i_pessoa = "
		        + iPessoa);

		return rs;
	}

	@Override
	public List<Agenda> getAll(Class<Agenda> classe) {
		return super.getAll(classe);
	}

	@Override
	public Agenda getById(Class<Agenda> classe, Serializable id) {
		return super.getById(classe, id);
	}

	@Override
	public void remove(Agenda agenda) throws Exception {

		agendaConvidadoRep.removeByAgenda(agenda.getiAgenda());

		super.remove(agenda);
	}

	@Override
	public Agenda save(Agenda agenda) throws Exception {
		validateEvento(agenda);
		return super.save(agenda);
	}

	@Override
	public Agenda update(Agenda agenda) throws Exception {
		validateEvento(agenda);
		return super.update(agenda);
	}

	private void validateEvento(Agenda agenda) throws Exception {
		if (agenda.getiPessoa() == null) {
			throw new Exception("A pessoa deve ser informada!");
		}
		if (agenda.getDescricao() == null) {
			throw new Exception("A descrição do evento deve ser informada!");
		}
		if (agenda.getDhinicio() == null) {
			throw new Exception("A data/horário de início do evento deve ser informada!");
		}
		if (agenda.getDhfim() != null && agenda.getDhinicio().after(agenda.getDhfim())) {
			throw new Exception(
			        "A data/horário de termino do evento deve ser posterior a data/horário de início!");
		}
	}

}
