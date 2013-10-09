package br.com.agenda.sessionbeans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.agenda.controller.filters.LoginCheckFilter;
import br.com.agenda.entity.Pessoa;

@Stateless
public class LoginService {

	@EJB
	PessoaRepository pessoaRep;

	public void doLogar(String email, String senha) throws Exception {
		Pessoa p = pessoaRep.getPessoaToLogin(email, senha);

		if (p == null) {
			throw new Exception("Email ou senha inválido(s)!");
		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();
		HttpSession session = req.getSession();

		session.setAttribute(LoginCheckFilter.AGENDA_CURRENT_USER, p);

		context.addMessage("bemvindo",
		        new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo", p.getNome()));

		try {
			RequestDispatcher rd = req.getRequestDispatcher("/home.xhtml");
			System.out.println("logando...");
			rd.forward(req, res);
		} catch (ServletException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);
		}

	}

	public void doSair() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();

		HttpSession session = req.getSession();

		session.removeAttribute(LoginCheckFilter.AGENDA_CURRENT_USER);

		try {
			RequestDispatcher rd = req.getRequestDispatcher("/index.xhtml");
			rd.forward(req, res);
		} catch (ServletException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);
		}
	}
}
