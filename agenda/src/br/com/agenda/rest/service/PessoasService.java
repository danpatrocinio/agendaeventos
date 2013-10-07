package br.com.agenda.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.agenda.entity.Pessoa;
import br.com.agenda.sessionbeans.PessoaRepository;

@Path("pessoas")
@Stateless
public class PessoasService {

	@EJB
	PessoaRepository pessoasRep;

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String doCount() {
		return String.valueOf(pessoasRep.countAll(Pessoa.class));
	}

	@GET
	@Path("json")
	@Produces({ "application/json" })
	public List<Pessoa> getPessoasJSON() {
		return new ArrayList<Pessoa>(pessoasRep.getAll(Pessoa.class));
	}

	@GET
	@Path("xml")
	@Produces({ "text/xml" })
	public List<Pessoa> getPessoasXML() {
		return new ArrayList<Pessoa>(pessoasRep.getAll(Pessoa.class));
	}

}
