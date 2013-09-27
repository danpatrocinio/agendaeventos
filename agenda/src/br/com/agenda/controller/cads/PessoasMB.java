package br.com.agenda.controller.cads;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.agenda.entity.Pessoa;
import br.com.agenda.sessionbeans.PessoaRepository;

@ManagedBean(name = "pessoas")
public class PessoasMB {

	@EJB
	PessoaRepository pessoaRep;

	public Pessoa getPessoa() {
		return new Pessoa();
	}

	public List<Pessoa> getPessoas() {
		return pessoaRep.getAll(Pessoa.class);
	}

	public void salvar(ActionEvent event) {
		System.out.println("Salvando pessoa: ");
	}

}
