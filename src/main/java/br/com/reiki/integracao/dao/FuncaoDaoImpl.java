package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.Funcao;

@Repository("funcaoDao")
public class FuncaoDaoImpl extends GenericDaoImpl<Funcao> implements FuncaoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Funcao> recuperarPorDescricao(Funcao funcao) {
		Query query = getEntityManager().createQuery("select f from Funcao f " + 
				"where f.descricao like :descricao " + 
				"order by f.descricao ");
		query.setParameter("descricao", "%" + funcao.getDescricao() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Funcao> recuperarTodas() {
		Query query = getEntityManager().createQuery("select f from Funcao f order by f.descricao ");
		return query.getResultList();
	}
	
}
