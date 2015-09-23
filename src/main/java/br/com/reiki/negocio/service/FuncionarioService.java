package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.Funcionario;

public interface FuncionarioService {

	public List<Funcionario> consultarFuncionario(Funcionario funcionario);

	public Funcionario carregarFuncionario(Integer id);

	public Funcionario gravarFuncionario(Funcionario funcionarioCadastro) throws ReikiException;

	public void deletarFuncionario(Integer id) throws ReikiException;

	public Funcionario carregarFuncionarioCompleto(Integer id);

	public List<Funcionario> recuperaTodosVendedoresAtivos();

}
