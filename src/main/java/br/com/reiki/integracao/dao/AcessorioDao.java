package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.Acessorio;

public interface AcessorioDao extends GenericDao<Acessorio> {

	public List<Acessorio> recuperarPorDescricao(Acessorio acessorio);
	
	public List<Acessorio> recuperarTodos();

}
