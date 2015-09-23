package br.com.reiki.negocio.service;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.entity.Usuario;

public interface SegurancaService {

	public Usuario autenticarUsuario(String login, String senha) throws ReikiException;

}
