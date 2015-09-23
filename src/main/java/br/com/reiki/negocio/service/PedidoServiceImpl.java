package br.com.reiki.negocio.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.infra.util.Util;
import br.com.reiki.integracao.constant.RegistroAtivoEnum;
import br.com.reiki.integracao.dao.PedidoDao;
import br.com.reiki.integracao.entity.Pedido;

@Service("pedidoService")
public class PedidoServiceImpl implements PedidoService, Serializable {

	private static final long serialVersionUID = 6561295681179217783L;
	
	private static final Logger LOGGER = Logger.getLogger(PedidoServiceImpl.class);
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoDao pedidoDao;

	public List<Pedido> consultarPedido(Pedido pedido) {
		return pedidoDao.consultarPedido(pedido);
	}

	public Pedido carregarPedidoCompleto(Integer id) {
		Pedido pedido = null;
		List<Pedido> resultado = pedidoDao.carregarPedidoCompleto(id);
		if(!resultado.isEmpty()){
			pedido = resultado.get(0);
		}
		return pedido;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public void deletarPedido(Integer id) throws ReikiException {
		try {
			pedidoDao.deletar(id);
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.registro.utilizado");
			} else {
				LOGGER.error("Metodo: deletarBanco", e);
				throw new ReikiException("msg.geral.falha.excluir.registro");
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = ReikiException.class)
	public Pedido gravarPedido(Pedido pedido) throws ReikiException {
		try {
			pedido.getCliente().setRegistroAtivo(RegistroAtivoEnum.SIM.getCodigo());
			clienteService.gravarCliente(pedido.getCliente());
			if (pedido.getId() == null) {
				pedido = (Pedido) pedidoDao.persistir(pedido);
			} else {
				pedido = (Pedido) pedidoDao.atualizar(pedido);
			}
		} catch (Exception e) {
			if (Util.verificarErroIntegrityConstraint(e)) {
				throw new ReikiException("msg.geral.existe.registro.duplicado");
			} else {
				LOGGER.error("Metodo: gravarBanco", e);
				throw new ReikiException("msg.geral.falha.cadastrar.registro");
			}
		}
		return pedido;
	}
	
	
}
