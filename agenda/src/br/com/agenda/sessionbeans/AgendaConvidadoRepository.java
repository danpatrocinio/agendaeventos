package br.com.agenda.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.agenda.entity.AgendaConvidado;

@Stateless
public class AgendaConvidadoRepository extends BaseRepository<AgendaConvidado> {

	@Override
	public void remove(AgendaConvidado entity) throws Exception {
		super.remove(entity);
	}

	public void removeByAgenda(Long iAgenda) throws Exception {
		removeById("iAgenda", iAgenda);
	}

	public void removeByPessoa(Long iPessoa) throws Exception {
		removeById("iPessoa", iPessoa);
	}

	@Override
	public AgendaConvidado save(AgendaConvidado agendaConvidado) throws Exception {
		if (agendaConvidado == null || agendaConvidado.getIagenda() == null
		        || agendaConvidado.getIpessoa() == null) {
			throw new Exception("Não foi possível adicionar o convidado!");
		}

		return super.save(agendaConvidado);
	}

	@SuppressWarnings("unchecked")
	private void removeById(String field, Long id) throws Exception {
		Query q = getEm()
		        .createQuery("SELECT ac FROM AgendaConvidado ac WHERE " + field + " = :id");
		q.setParameter("id", id);

		List<AgendaConvidado> agendaConvidadoList = q.getResultList();

		for (AgendaConvidado agendaConvidado : agendaConvidadoList) {
			remove(agendaConvidado);
		}
	}

}
