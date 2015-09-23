package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.Pedido;

public interface PedidoService {

	public List<Pedido> consultarPedido(Pedido pedido);

	public Pedido carregarPedidoCompleto(Integer id);

	public void deletarPedido(Integer id) throws ReikiException;

	public Pedido gravarPedido(Pedido pedido) throws ReikiException;

}
