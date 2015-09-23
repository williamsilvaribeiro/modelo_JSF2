package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.TipoVidro;

public interface TipoVidroService {

	public List<TipoVidro> consultarTipoVidro(TipoVidro tipoVidro);

	public TipoVidro carregarTipoVidro(Integer id);

	public TipoVidro gravarTipoVidro(TipoVidro tipoVidroCadastro) throws ReikiException;

	public void deletarTipoVidro(Integer id) throws ReikiException;
	
	public List<TipoVidro> recuperaTodos();

}
