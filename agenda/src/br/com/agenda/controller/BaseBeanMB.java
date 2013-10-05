package br.com.agenda.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class BaseBeanMB implements Serializable {

	private static final long serialVersionUID = 1L;

	protected void addBemVindo(String user) {
		FacesContext.getCurrentInstance().addMessage("bemvindo",
		        new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo", user));
	}

	protected void addErrorMessage(String errorMessage) {
		addErrorMessage(null, errorMessage);
	}

	protected void addErrorMessage(String componentId, String errorMessage) {
		addMessage(componentId, errorMessage, FacesMessage.SEVERITY_ERROR);
	}

	protected void addInfoMessage(String infoMessage) {
		addInfoMessage(null, infoMessage);
	}

	protected void addInfoMessage(String componentId, String infoMessage) {
		addMessage(componentId, infoMessage, FacesMessage.SEVERITY_INFO);
	}

	private void addMessage(String componentId, String errorMessage, Severity severity) {
		FacesMessage message = new FacesMessage(errorMessage);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(componentId, message);
	}
}
