package br.com.agenda.controller.reports;

import java.sql.SQLException;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.agenda.entity.Agenda;
import br.com.agenda.entity.Pessoa;
import br.com.agenda.sessionbeans.AgendaRepository;
import br.com.agenda.sessionbeans.PessoaRepository;

@ManagedBean
@RequestScoped
public class AgendaReportMB extends BaseReportMB<Agenda> {

	private static final long serialVersionUID = 1L;

	@EJB
	private AgendaRepository agendaRep;

	@EJB
	private PessoaRepository pessoaRep;

	public void execute() {
		try {
			super.gerarRelatorio(agendaRep.getAgendasByDate());
		} catch (ClassNotFoundException e) {
			addMessage("", e.getMessage());
		} catch (SQLException e) {
			addMessage("", e.getMessage());
		}
	}

	public void executeMy() {
		try {

			// TODO pegar pessoa da session...
			Pessoa p = pessoaRep.getById(Pessoa.class, new Long(1));

			addParam("pPessoa", pessoaRep.formatCpfCnpj(p.getCpfcnpj()) + " - " + p.getNome());

			super.gerarRelatorio(agendaRep.getAgendasByDate(new Long(1)));
		} catch (ClassNotFoundException e) {
			addMessage("", e.getMessage());
		} catch (SQLException e) {
			addMessage("", e.getMessage());
		}
	}

	@Override
	String getJasperNameFile() {
		return "agendas";
	}

}
