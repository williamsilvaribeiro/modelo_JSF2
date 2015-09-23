package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.Acessorio;

@Repository("acessorioDao")
public class AcessorioDaoImpl extends GenericDaoImpl<Acessorio> implements AcessorioDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Acessorio> recuperarPorDescricao(Acessorio acessorio) {
		Query query = getEntityManager().createQuery("select a from Acessorio a " + 
				"where a.descricao like :descricao " + 
				"order by a.descricao ");
		query.setParameter("descricao", "%" + acessorio.getDescricao() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Acessorio> recuperarTodos() {
		Query query = getEntityManager().createQuery("select a from Acessorio a order by a.descricao ");
		return query.getResultList();
	}
	
}
