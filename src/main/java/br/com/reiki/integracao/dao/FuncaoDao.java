package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.Funcao;

public interface FuncaoDao extends GenericDao<Funcao> {

	public List<Funcao> recuperarPorDescricao(Funcao funcao);
	
	public List<Funcao> recuperarTodas();

}
