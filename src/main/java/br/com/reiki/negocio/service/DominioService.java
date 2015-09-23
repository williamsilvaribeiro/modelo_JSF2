package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.integracao.entity.SituacaoPedido;
import br.com.reiki.integracao.entity.TipoPagamento;
import br.com.reiki.integracao.entity.Uf;

public interface DominioService {

	public List<SituacaoPedido> recuperaTodasSituacaoPedido();
	
	public List<TipoPagamento> recuperaTodosTipoPagamento();

	public List<Uf> recuperaTodasUf();
}
