package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.Pedido;

public interface PedidoDao extends GenericDao<Pedido> {

	public List<Pedido> consultarPedido(Pedido pedido);

	public List<Pedido> carregarPedidoCompleto(Integer id);

}
