package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.TipoVidro;

@Repository("tipoVidroDao")
public class TipoVidroDaoImpl extends GenericDaoImpl<TipoVidro> implements TipoVidroDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<TipoVidro> recuperarPorDescricao(TipoVidro tipoVidro) {
		Query query = getEntityManager().createQuery("select tv from TipoVidro tv " + 
				"where tv.descricao like :descricao " + 
				"order by tv.descricao ");
		query.setParameter("descricao", "%" + tipoVidro.getDescricao() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TipoVidro> recuperarTodos() {
		Query query = getEntityManager().createQuery("select tv from TipoVidro tv order by tv.descricao ");
		return query.getResultList();
	}
	
}
