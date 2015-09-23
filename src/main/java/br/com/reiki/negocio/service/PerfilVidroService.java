package br.com.reiki.negocio.service;

import java.util.List;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.PerfilVidro;

public interface PerfilVidroService {

	public List<PerfilVidro> consultarPerfilVidro(PerfilVidro perfilVidro);

	public PerfilVidro carregarPerfilVidro(Integer id);

	public PerfilVidro gravarPerfilVidro(PerfilVidro perfilVidroCadastro) throws ReikiException;

	public void deletarPerfilVidro(Integer id) throws ReikiException;
	
	public List<PerfilVidro> recuperaTodos();

}
