package br.com.reiki.integracao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.infra.util.Util;
import br.com.reiki.integracao.entity.Pedido;

@Repository("pedidoDao")
public class PedidoDaoImpl extends GenericDaoImpl<Pedido> implements PedidoDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Pedido> consultarPedido(Pedido pedido) {
		
		String igNumero = Util.defineSeIgnorar(pedido.getNumero());
		String igVendedor = Util.defineSeIgnorar(pedido.getVendedor().getId());
		String igSituacaoPedido = Util.defineSeIgnorar(pedido.getSituacaoPedido().getId());
		String igDataEmissao = Util.defineSeIgnorar(pedido.getDataEmissao());
		
		Query query = getEntityManager().createQuery("select p from Pedido p "
				+ "left join fetch p.enderecoObra eo "
				+ "left join fetch p.cliente c "
				+ "left join fetch p.vendedor v "
				+ "left join fetch p.situacaoPedido sp "
				+ "where ( p.numero = :numero or 'ignora' = :igNumero ) "
				+ "and ( eo.endereco like :endereco ) "
				+ "and ( c.nome like :nome ) "
				+ "and ( v.id = :idVendedor or 'ignora' = :igVendedor ) "
				+ "and ( sp.id = :idSituacaoPedido or 'ignora' = :igSituacaoPedido ) "
				+ "and ( p.dataEmissao = :dataEmissao or 'ignora' = :igDataEmissao ) "
				+ "order by p.numero desc ");
		
		query.setParameter("numero", pedido.getNumero());
		query.setParameter("igNumero", igNumero);
		
		query.setParameter("endereco", "%" + pedido.getEnderecoObra().getEndereco() + "%");
		
		query.setParameter("nome", "%" + pedido.getCliente().getNome() + "%");
		
		query.setParameter("idVendedor", pedido.getVendedor().getId());
		query.setParameter("igVendedor", igVendedor);
		
		query.setParameter("idSituacaoPedido", pedido.getSituacaoPedido().getId());
		query.setParameter("igSituacaoPedido", igSituacaoPedido);
		
		query.setParameter("dataEmissao", pedido.getDataEmissao());
		query.setParameter("igDataEmissao", igDataEmissao);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> carregarPedidoCompleto(Integer id) {
		Query query = getEntityManager().createQuery("select p from Pedido p "
				+ "left join fetch p.enderecoObra eo "
				+ "left join fetch p.cliente c "
				+ "left join fetch p.vendedor v "
				+ "left join fetch p.situacaoPedido sp "
				+ "left join fetch eo.uf uf "
				+ "left join fetch v.funcao f "
				+ "where p.id = :id ");
		query.setParameter("id", id);
		return query.getResultList();
	}

}

