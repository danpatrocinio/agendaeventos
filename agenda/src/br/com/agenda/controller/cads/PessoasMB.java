package br.com.agenda.controller.cads;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.agenda.entity.Pessoa;

@ManagedBean(name = "pessoas")
public class PessoasMB {

	public Pessoa getPessoa() {
		return new Pessoa();
	}

	public void salvar(ActionEvent event) {
		System.out.println("Salvando pessoa: ");
	}

}
