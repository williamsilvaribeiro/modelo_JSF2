package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.SituacaoPedido;

@Repository("situacaoPedidoDao")
public class SituacaoPedidoDaoImpl extends GenericDaoImpl<SituacaoPedido> implements SituacaoPedidoDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<SituacaoPedido> recuperaTodasSituacaoPedido() {
		Query query = getEntityManager().createQuery("select sp from SituacaoPedido sp order by sp.descricao ");
		return query.getResultList();
	}
}
