package br.com.agenda.controller.reports;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.agenda.controller.filters.LoginCheckFilter;
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

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

			HttpSession session = req.getSession();

			Pessoa p = (Pessoa) session.getAttribute(LoginCheckFilter.AGENDA_CURRENT_USER);

			addParam("pPessoa", pessoaRep.formatCpfCnpj(p.getCpfcnpj()) + " - " + p.getNome());

			super.gerarRelatorio(agendaRep.getAgendasByDate(p.getiPessoa()));
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
