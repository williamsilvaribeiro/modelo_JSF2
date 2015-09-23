package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.Acessorio;

public interface AcessorioService {

	public List<Acessorio> consultarAcessorio(Acessorio acessorio);

	public Acessorio carregarAcessorio(Integer id);

	public Acessorio gravarAcessorio(Acessorio acessorioCadastro) throws ReikiException;

	public void deletarAcessorio(Integer id) throws ReikiException;
	
	public List<Acessorio> recuperaTodos();

}
