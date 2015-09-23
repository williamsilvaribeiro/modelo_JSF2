package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.PerfilVidro;

@Repository("perfilVidroDao")
public class PerfilVidroDaoImpl extends GenericDaoImpl<PerfilVidro> implements PerfilVidroDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<PerfilVidro> recuperarPorDescricao(PerfilVidro perfilVidro) {
		Query query = getEntityManager().createQuery("select pv from PerfilVidro pv " + 
				"where pv.descricao like :descricao " + 
				"order by pv.descricao ");
		query.setParameter("descricao", "%" + perfilVidro.getDescricao() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PerfilVidro> recuperarTodos() {
		Query query = getEntityManager().createQuery("select pv from PerfilVidro pv order by pv.descricao ");
		return query.getResultList();
	}
	
}
