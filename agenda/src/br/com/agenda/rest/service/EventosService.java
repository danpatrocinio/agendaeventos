package br.com.agenda.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.agenda.entity.Agenda;
import br.com.agenda.sessionbeans.AgendaRepository;

@Path("eventos")
@Stateless
public class EventosService {

	@EJB
	AgendaRepository agendaRep;

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String doCount() {
		return String.valueOf(agendaRep.countAll(Agenda.class));
	}

	@GET
	@Path("json")
	@Produces({ "application/json" })
	public List<Agenda> getEventos() {
		return new ArrayList<Agenda>(agendaRep.getAll(Agenda.class));
	}

	@GET
	@Path("xml")
	@Produces({ "text/xml" })
	public List<Agenda> getPessoasXML() {
		return new ArrayList<Agenda>(agendaRep.getAll(Agenda.class));
	}

}
