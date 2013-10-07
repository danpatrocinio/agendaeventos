package br.com.agenda.controller.cads;

import java.util.*;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.agenda.controller.BaseBeanMB;
import br.com.agenda.entity.Pessoa;
import br.com.agenda.sessionbeans.PessoaRepository;

@ManagedBean(name = "pessoasMB")
@ViewScoped
public class PessoasMB extends BaseBeanMB {

	private static final List<SelectItem> anos;
	private static final List<SelectItem> dias;
	private static final List<SelectItem> meses;
	private static final long serialVersionUID = 1L;

	@EJB
	PessoaRepository pessoaRep;

	private Integer anoNascimento;

	private Integer diaNascimento;
	private Integer mesNascimento;
	private String senha2;
	static {
		dias = new ArrayList<SelectItem>();
		for (int i = 1; i < 32; i++) {
			PessoasMB.dias.add(new SelectItem(i, String.valueOf(i)));
		}
		meses = new ArrayList<SelectItem>();
		PessoasMB.meses.add(new SelectItem(1, "Jan"));
		PessoasMB.meses.add(new SelectItem(2, "Fev"));
		PessoasMB.meses.add(new SelectItem(3, "Mar"));
		PessoasMB.meses.add(new SelectItem(4, "Abr"));
		PessoasMB.meses.add(new SelectItem(5, "Mai"));
		PessoasMB.meses.add(new SelectItem(6, "Jun"));
		PessoasMB.meses.add(new SelectItem(7, "Jul"));
		PessoasMB.meses.add(new SelectItem(8, "Ago"));
		PessoasMB.meses.add(new SelectItem(9, "Set"));
		PessoasMB.meses.add(new SelectItem(10, "Out"));
		PessoasMB.meses.add(new SelectItem(11, "Nov"));
		PessoasMB.meses.add(new SelectItem(12, "Dez"));
		anos = new ArrayList<SelectItem>();
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date(System.currentTimeMillis()));
		Integer ano = cal.get(Calendar.YEAR);
		for (int i = ano; i >= ano - 100; i--) {

			PessoasMB.anos.add(new SelectItem(i, String.valueOf(i)));
		}
	}

	public void criarConta(ActionEvent event) {
		System.out.println("Criando conta...");
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_MONTH, getDiaNascimento());
		cal.set(Calendar.MONTH, getMesNascimento());
		cal.set(Calendar.YEAR, getAnoNascimento());
		Date diaNascimento = new Date(cal.getTimeInMillis());
		System.out.println("Dia nascimento: " + diaNascimento);
		if (getSenha2() == null || "".equals(getSenha2())) {
			addErrorMessage("Confirme a senha!");
			return;
		}
	}

	public Integer getAnoNascimento() {
		return anoNascimento;
	}

	public List<SelectItem> getAnos() {
		return PessoasMB.anos;
	}

	public Long getCount() {
		return pessoaRep.countAll(Pessoa.class);
	}

	public Integer getDiaNascimento() {
		return diaNascimento;
	}

	public List<SelectItem> getDias() {
		return PessoasMB.dias;
	}

	public List<SelectItem> getMeses() {
		return PessoasMB.meses;
	}

	public Integer getMesNascimento() {
		return mesNascimento;
	}

	public Pessoa getPessoa() {
		return new Pessoa();
	}

	public List<Pessoa> getPessoas() {
		return pessoaRep.getAll(Pessoa.class);
	}

	public String getSenha2() {
		return senha2;
	}

	public void setAnoNascimento(Integer anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public void setDiaNascimento(Integer diaNascimento) {
		this.diaNascimento = diaNascimento;
	}

	public void setMesNascimento(Integer mesNascimento) {
		this.mesNascimento = mesNascimento;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

}
