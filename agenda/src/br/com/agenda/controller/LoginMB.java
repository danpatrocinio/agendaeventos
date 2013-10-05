package br.com.agenda.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.agenda.controller.filters.LoginCheckFilter;
import br.com.agenda.entity.Pessoa;
import br.com.agenda.sessionbeans.PessoaRepository;

@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginMB extends BaseBeanMB {

	private static final long serialVersionUID = 1L;

	@EJB
	PessoaRepository pessoasRep;

	private String emailUser;
	private String senhaUser;

	public String getEmailUser() {
		return emailUser;
	}

	public String getSenhaUser() {
		return senhaUser;
	}

	public void logar() {
		Pessoa p = pessoasRep.getPessoaToLogin(getEmailUser(), getSenhaUser());
		if (p == null) {
			addErrorMessage("Email ou senha inválido(s)!");
		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

		HttpSession session = req.getSession();

		session.setAttribute(LoginCheckFilter.AGENDA_CURRENT_USER, p);

		addBemVindo(p.getNome());

	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public void setSenhaUser(String senhaUser) {
		this.senhaUser = senhaUser;
	}

}
