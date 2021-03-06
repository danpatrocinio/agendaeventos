package br.com.agenda.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.agenda.util.DateAdapter;

@Entity
@Table(name = "agendas")
@XmlRootElement
public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricao;

	@Column(name = "dh_fim")
	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement(name = "Fim")
	private Timestamp dh_fim;

	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement(name = "Inicio")
	@Column(name = "dh_inicio")
	private Timestamp dh_inicio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_agenda")
	private Long iAgenda;

	@Column(name = "i_agenda_original")
	private Long iagendaoriginal;

	@Column(name = "i_pessoa")
	private Long iPessoa;

	private String local;

	public String getDescricao() {
		return descricao;
	}

	public Timestamp getDhfim() {
		return dh_fim;
	}

	public Timestamp getDhinicio() {
		return dh_inicio;
	}

	public Long getiAgenda() {
		return iAgenda;
	}

	public Long getIagendaoriginal() {
		return iagendaoriginal;
	}

	public Long getiPessoa() {
		return iPessoa;
	}

	public String getLocal() {
		return local;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDhfim(Timestamp dh_fim) {
		this.dh_fim = dh_fim;
	}

	public void setDhinicio(Timestamp dh_inicio) {
		this.dh_inicio = dh_inicio;
	}

	public void setiAgenda(Long iAgenda) {
		this.iAgenda = iAgenda;
	}

	public void setIagendaoriginal(Long iagendaoriginal) {
		this.iagendaoriginal = iagendaoriginal;
	}

	public void setiPessoa(Long iPessoa) {
		this.iPessoa = iPessoa;
	}

	public void setLocal(String local) {
		this.local = local;
	}

}
