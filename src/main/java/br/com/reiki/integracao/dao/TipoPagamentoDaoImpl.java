package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.TipoPagamento;
import br.com.reiki.integracao.entity.Uf;

@Repository("tipoPagamentoDao")
public class TipoPagamentoDaoImpl extends GenericDaoImpl<TipoPagamento> implements TipoPagamentoDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<TipoPagamento> recuperaTodosTipoPagamento() {
		Query query = getEntityManager().createQuery("select tp from TipoPagamento tp ");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Uf> recuperaTodasUf() {
		Query query = getEntityManager().createQuery("select uf from Uf uf ");
		return query.getResultList();
	}
	
}
