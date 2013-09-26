package br.com.agenda.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cpf_cnpj")
	private String cpfcnpj;

	@Column(name = "dt_nascimento")
	private Date dtnascimento;

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
		return dtnascimento;
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

	public void setDtnascimento(Date dtnascimento) {
		this.dtnascimento = dtnascimento;
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
