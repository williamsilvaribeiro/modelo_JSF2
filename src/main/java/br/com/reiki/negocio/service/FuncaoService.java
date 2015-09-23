package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.Funcao;

public interface FuncaoService {

	public List<Funcao> consultarFuncao(Funcao funcao);

	public Funcao carregarFuncao(Integer id);

	public Funcao gravarFuncao(Funcao funcaoCadastro) throws ReikiException;

	public void deletarFuncao(Integer id) throws ReikiException;
	
	public List<Funcao> recuperaTodas();

}
