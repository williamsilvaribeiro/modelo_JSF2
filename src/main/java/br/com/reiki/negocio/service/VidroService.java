package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.Vidro;

public interface VidroService {

	public List<Vidro> consultarVidro(Vidro vidro);

	public Vidro carregarVidro(Integer id);

	public Vidro gravarVidro(Vidro vidroCadastro) throws ReikiException;

	public void deletarVidro(Integer id) throws ReikiException;

	public Vidro carregarVidroCompleto(Integer id);

}
