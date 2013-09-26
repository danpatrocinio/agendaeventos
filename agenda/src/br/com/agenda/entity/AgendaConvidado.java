package br.com.agenda.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agendas_convidados")
public class AgendaConvidado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "i_agenda")
	@Id
	private Long iagenda;

	@Column(name = "i_pessoa")
	@Id
	private Long ipessoa;

	public Long getIagenda() {
		return iagenda;
	}

	public Long getIpessoa() {
		return ipessoa;
	}

	public void setIagenda(Long iagenda) {
		this.iagenda = iagenda;
	}

	public void setIpessoa(Long ipessoa) {
		this.ipessoa = ipessoa;
	}

}
