package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.infra.util.Util;
import br.com.reiki.integracao.entity.Cliente;

@Repository("clienteDao")
public class ClienteDaoImpl extends GenericDaoImpl<Cliente> implements ClienteDao, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Cliente> consultarCliente(Cliente cliente) {
		String igRegistroAtivo = Util.defineSeIgnorar(cliente.getRegistroAtivo());
		String igTipoPessoa = Util.defineSeIgnorar(cliente.getTipoPessoa());
		Query query = getEntityManager().createQuery("select c from Cliente c "
				+ "where c.nome like :nome "
				+ "and ( c.registroAtivo = :registroAtivo or 'ignora' = :igRegistroAtivo ) "
				+ "and ( c.tipoPessoa = :tipoPessoa or 'ignora' = :igTipoPessoa ) "
				+ "order by c.nome ");
		query.setParameter("nome", "%" + cliente.getNome() + "%");
		query.setParameter("registroAtivo", cliente.getRegistroAtivo());
		query.setParameter("igRegistroAtivo", igRegistroAtivo);
		query.setParameter("tipoPessoa", cliente.getTipoPessoa());
		query.setParameter("igTipoPessoa", igTipoPessoa);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> recuperarClienteCompleto(Integer id) {
		Query query = getEntityManager().createQuery("select c from Cliente c "
				+ "where c.id = :id ");
		query.setParameter("id", id);
		return query.getResultList();
	}

}
