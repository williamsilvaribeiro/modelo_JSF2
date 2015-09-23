package br.com.reiki.integracao.dao;

import java.util.List;

import br.com.reiki.integracao.entity.TipoPagamento;
import br.com.reiki.integracao.entity.Uf;

public interface TipoPagamentoDao {

	public List<TipoPagamento> recuperaTodosTipoPagamento();

	public List<Uf> recuperaTodasUf();

}
