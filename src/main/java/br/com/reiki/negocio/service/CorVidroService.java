package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.CorVidro;

public interface CorVidroService {

	public List<CorVidro> consultarCorVidro(CorVidro corVidro);

	public CorVidro carregarCorVidro(Integer id);

	public CorVidro gravarCorVidro(CorVidro corVidroCadastro) throws ReikiException;

	public void deletarCorVidro(Integer id) throws ReikiException;
	
	public List<CorVidro> recuperaTodas();

}
