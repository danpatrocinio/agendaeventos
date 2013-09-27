package br.com.agenda.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "homeMB")
@ViewScoped
public class HomeMB {

	private Date selectedDay;

	public String getMensagem() {
		return "Bem vindo a Agenda de Eventos!";
	}

	public Date getSelectedDay() {

		return selectedDay;
	}

	public void handleDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
		        format.format(event.getObject())));
	}

	public void setSelectedDay(Date date) {
		selectedDay = date;
	}

}
