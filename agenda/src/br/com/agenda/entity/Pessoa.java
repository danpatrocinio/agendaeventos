package br.com.agenda.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.agenda.util.DateAdapter;

@Entity
@Table(name = "pessoas")
@XmlRootElement
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cpf_cnpj")
	private String cpfcnpj;

	@Column(name = "dt_nascimento")
	@XmlElement(name = "dtNascimento")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dt_nascimento;

	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_pessoa")
	private Long iPessoa;

	private String nome;

	private String senha;

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public Date getDtnascimento() {
		return dt_nascimento;
	}

	public String getEmail() {
		return email;
	}

	public Long getiPessoa() {
		return iPessoa;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public void setDtnascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setiPessoa(Long iPessoa) {
		this.iPessoa = iPessoa;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
