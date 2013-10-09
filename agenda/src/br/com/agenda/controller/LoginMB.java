package br.com.agenda.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.agenda.sessionbeans.LoginService;

@ManagedBean(name = "loginMB")
@RequestScoped
public class LoginMB extends BaseBeanMB {

	private static final long serialVersionUID = 1L;

	@EJB
	LoginService login;
	private String emailUser;
	private String senhaUser;

	public String getEmailUser() {
		return emailUser;
	}

	public String getSenhaUser() {
		return senhaUser;
	}

	public void logar() {
		try {
			login.doLogar(getEmailUser(), getSenhaUser());
		} catch (Exception e) {
			addErrorMessage("messagesLog", e.getMessage());
		}
	}

	public void sair() {
		login.doSair();
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
		        .getExternalContext().getRequest();
		req.getSession().invalidate();
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public void setSenhaUser(String senhaUser) {
		this.senhaUser = senhaUser;
	}

}
