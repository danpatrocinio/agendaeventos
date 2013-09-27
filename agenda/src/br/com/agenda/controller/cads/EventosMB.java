package br.com.agenda.controller.cads;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.agenda.entity.Agenda;
import br.com.agenda.sessionbeans.AgendaRepository;

@ManagedBean(name = "eventos")
public class EventosMB {

	@EJB
	AgendaRepository agendaRep;

	public Agenda getAgenda() {
		return new Agenda();
	}

	public List<Agenda> getEventos() {
		return agendaRep.getAll(Agenda.class);
	}

	public void salvar(ActionEvent event) {
		System.out.println("Salvando evento: ");
	}

}
