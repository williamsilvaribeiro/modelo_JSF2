package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.CorVidro;

@Repository("corVidroDao")
public class CorVidroDaoImpl extends GenericDaoImpl<CorVidro> implements CorVidroDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<CorVidro> recuperarPorDescricao(CorVidro corVidro) {
		Query query = getEntityManager().createQuery("select cv from CorVidro cv " + 
				"where cv.descricao like :descricao " + 
				"order by cv.descricao ");
		query.setParameter("descricao", "%" + corVidro.getDescricao() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CorVidro> recuperarTodas() {
		Query query = getEntityManager().createQuery("select cv from CorVidro cv order by cv.descricao ");
		return query.getResultList();
	}
	
}
