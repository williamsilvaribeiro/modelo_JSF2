package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.CorVidro;

public interface CorVidroDao extends GenericDao<CorVidro> {

	public List<CorVidro> recuperarPorDescricao(CorVidro corVidro);
	
	public List<CorVidro> recuperarTodas();

}
