package br.com.agenda.controller;

import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "homeMB")
@ViewScoped
public class HomeMB {

	private Date selectedDay;

	public String getMensagem() {
		return "Bem vindo a Agenda de Eventos!";
	}

	public Date getSelectedDay() {
		if (selectedDay == null) {
			Date dt = new Date(System.currentTimeMillis());
			selectedDay = dt;
		}
		return selectedDay;
	}

	public void setSelectedDay(Date date) {
		selectedDay = date;
	}

}
