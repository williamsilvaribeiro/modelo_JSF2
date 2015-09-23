package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.infra.util.Util;
import br.com.reiki.integracao.entity.Vidro;

@Repository("vidroDao")
public class VidroDaoImpl extends GenericDaoImpl<Vidro> implements VidroDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Vidro> consultarVidro(Vidro vidro) {
		String igTipoVidro = Util.defineSeIgnorar(vidro.getTipoVidro().getId());
		String igCorVidro = Util.defineSeIgnorar(vidro.getCorVidro().getId());
		Query query = getEntityManager().createQuery("select v from Vidro v "
				+ "left join fetch v.tipoVidro tv "
				+ "left join fetch v.corVidro cv "
				+ "where (tv.id = :idTipoVidro or 'ignora' = :igTipoVidro ) "
				+ "and ( cv.id = :idCorVidro or 'ignora' = :igCorVidro ) ");
		query.setParameter("idTipoVidro", vidro.getTipoVidro().getId());
		query.setParameter("igTipoVidro", igTipoVidro);
		query.setParameter("idCorVidro", vidro.getCorVidro().getId());
		query.setParameter("igCorVidro", igCorVidro);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Vidro> recuperarVidroCompleto(Integer id) {
		Query query = getEntityManager().createQuery("select v from Vidro v "
				+ "left join fetch v.tipoVidro tv "
				+ "left join fetch v.corVidro cv "
				+ "where v.id = :id ");
		query.setParameter("id", id);
		return query.getResultList();
	}

}
