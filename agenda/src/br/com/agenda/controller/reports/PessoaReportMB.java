package br.com.agenda.controller.reports;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.agenda.entity.Pessoa;
import br.com.agenda.sessionbeans.PessoaRepository;

@ManagedBean(name = "pessoaReportMB")
@RequestScoped
public class PessoaReportMB extends BaseReportMB<Pessoa> {

	private static final long serialVersionUID = 1L;

	@EJB
	private PessoaRepository pessoaRep;

	public void execute() {
		gerarRelatorio(pessoaRep.getAll(Pessoa.class));
	}

	@Override
	String getJasperNameFile() {
		return "pessoas";
	}

}
