package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.Funcionario;

public interface FuncionarioDao extends GenericDao<Funcionario> {

	public List<Funcionario> consultarFuncionario(Funcionario funcionario);

	public List<Funcionario> recuperarFuncionarioCompleto(Integer id);

	public List<Funcionario> recuperaTodosVendedoresAtivos();

}
