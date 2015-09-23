package br.com.reiki.negocio.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reiki.integracao.dao.SituacaoPedidoDao;
import br.com.reiki.integracao.dao.TipoPagamentoDao;
import br.com.reiki.integracao.entity.SituacaoPedido;
import br.com.reiki.integracao.entity.TipoPagamento;
import br.com.reiki.integracao.entity.Uf;

@Service("dominioService")
public class DominioServiceImpl implements DominioService, Serializable {

	private static final long serialVersionUID = 6561295681179217783L;

	@Autowired
	private SituacaoPedidoDao situacaoPedidoDao;
	
	@Autowired
	private TipoPagamentoDao tipoPagamentoDao;
	

	public List<SituacaoPedido> recuperaTodasSituacaoPedido() {
		return situacaoPedidoDao.recuperaTodasSituacaoPedido();
	}

	public List<TipoPagamento> recuperaTodosTipoPagamento() {
		return tipoPagamentoDao.recuperaTodosTipoPagamento();
	}

	public List<Uf> recuperaTodasUf() {
		return tipoPagamentoDao.recuperaTodasUf();
	}

}
