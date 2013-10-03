package br.com.agenda.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.agenda.controller.filters.LoginCheckFilter;
import br.com.agenda.entity.Pessoa;

@ManagedBean(name = "homeMB")
@ViewScoped
public class HomeMB {

	public void delete() {
		System.out.println("Deletando...");
	}

	public String getMensagem() {
		return "Bem vindo a Agenda de Eventos!";
	}

	public String getNomeCurrentPessoa() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

		Pessoa p = (Pessoa) req.getSession().getAttribute(LoginCheckFilter.AGENDA_CURRENT_USER);

		if (p != null) {
			return p.getNome();
		}

		return "";
	}

	public Date getToday() {
		return new Date(System.currentTimeMillis());
	}

	public void sair() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

		req.getSession().setAttribute(LoginCheckFilter.AGENDA_CURRENT_USER, null);
	}

	public void save() {
		System.out.println("Salvando...");
	}

	public void setToday(Date today) {

	}

	public void update() {
		System.out.println("atualizando...");
	}

}
