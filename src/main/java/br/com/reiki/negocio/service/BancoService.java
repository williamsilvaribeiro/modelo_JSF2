package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.Banco;

public interface BancoService {

	public List<Banco> consultarBanco(Banco banco);

	public Banco carregarBanco(Integer id);

	public Banco gravarBanco(Banco banco) throws ReikiException;

	public void deletarBanco(Integer id) throws ReikiException;

	public List<Banco> recuperaTodos();

}
