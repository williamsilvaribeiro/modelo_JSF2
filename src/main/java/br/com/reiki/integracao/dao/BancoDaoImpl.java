package br.com.reiki.integracao.dao;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.Banco;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository("bancoDao")
public class BancoDaoImpl extends GenericDaoImpl<Banco> implements BancoDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Banco> recuperarPorNomeOuNumero(Banco banco) {
		Query query = getEntityManager().createQuery("select b from Banco b " + 
				"where b.nome like :nome " + 
				"and b.numero like :numero " + 
				"order by b.nome ");
		query.setParameter("nome", "%" + banco.getNome() + "%");
		query.setParameter("numero", "%" + banco.getNumero() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Banco> recuperarTodos() {
		Query query = getEntityManager().createQuery("select b from Banco b order by b.nome ");
		return query.getResultList();
	}

}
