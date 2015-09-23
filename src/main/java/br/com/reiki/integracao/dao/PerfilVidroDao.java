package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.PerfilVidro;

public interface PerfilVidroDao extends GenericDao<PerfilVidro> {

	public List<PerfilVidro> recuperarPorDescricao(PerfilVidro perfilVidro);
	
	public List<PerfilVidro> recuperarTodos();

}
