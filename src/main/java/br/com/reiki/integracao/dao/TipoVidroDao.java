package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.TipoVidro;

public interface TipoVidroDao extends GenericDao<TipoVidro> {

	public List<TipoVidro> recuperarPorDescricao(TipoVidro tipoVidro);
	
	public List<TipoVidro> recuperarTodos();

}
