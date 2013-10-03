package br.com.agenda.sessionbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.agenda.entity.Pessoa;

@Stateless
public class PessoaRepository extends BaseRepository<Pessoa> {

	@EJB
	AgendaConvidadoRepository agendaConvidadoRep;

	public String formatCpfCnpj(String cpfCnpj) {
		return super.format(cpfCnpj);
	}

	@Override
	public List<Pessoa> getAll(Class<Pessoa> classe) {
		return super.getAll(classe);
	}

	@Override
	public Pessoa getById(Class<Pessoa> classe, Serializable id) {
		return super.getById(classe, id);
	}

	public Pessoa getPessoaByCpfCnpj(String cpfCnpj) {
		Query q = getEm().createQuery("SELECT p FROM Pessoa p WHERE cpfCnpj = :cpfcnpj");
		q.setParameter("cpfcnpj", cpfCnpj);
		Pessoa p = (Pessoa) q.getSingleResult();
		return p;
	}

	public Pessoa getPessoaToLogin(String email, String senha) {
		Query q = getEm().createQuery(
		        "SELECT p FROM Pessoa p WHERE email = :email AND senha = :senha");
		q.setParameter("email", email);
		q.setParameter("senha", senha);
		Pessoa p = (Pessoa) q.getSingleResult();
		return p;
	}

	@Override
	public void remove(Pessoa pessoa) throws Exception {

		agendaConvidadoRep.removeByPessoa(pessoa.getiPessoa());

		super.remove(pessoa);
	}

	@Override
	public Pessoa save(Pessoa pessoa) throws Exception {
		validatePessoa(pessoa);
		return super.save(pessoa);
	}

	@Override
	public Pessoa update(Pessoa pessoa) throws Exception {
		validatePessoa(pessoa);
		return super.update(pessoa);
	}

	private void validatePessoa(Pessoa pessoa) throws Exception {
		if (pessoa.getCpfcnpj() == null || "".equals(pessoa.getCpfcnpj())) {
			throw new Exception("Informe o CPF!");
		}
		if (pessoa.getNome() == null || "".equals(pessoa.getNome())) {
			throw new Exception("Informe o nome!");
		}
		if (pessoa.getSenha() == null || "".equals(pessoa.getSenha())) {
			throw new Exception("Informe a senha!");
		}

		int qtdNum = 0;
		int qtdLet = 0;
		String numeros = "0123456789";
		String letras = "abcdefghijklmnopqrstuvxzwyABCDEFGHIJKLMNOPQRSTUVXZWY";
		for (int i = 0; i < pessoa.getSenha().length(); i++) {
			String s = pessoa.getSenha().substring(i, i + 1);
			qtdNum += numeros.contains(s) ? 1 : 0;
			qtdLet += letras.contains(s) ? 1 : 0;
		}
		if (qtdNum < 3 || qtdLet < 3) {
			throw new Exception("Senha deve conter no mínimo 3 números e 3 letras!");
		}

		if (pessoa.getDtnascimento() == null || "".equals(pessoa.getDtnascimento())) {
			throw new Exception("Informe a data de nascimento!");
		}
	}

}
