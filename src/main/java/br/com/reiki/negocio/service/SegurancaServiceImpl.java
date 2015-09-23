package br.com.reiki.negocio.service;

import java.io.Serializable;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.integracao.dao.UsuarioDao;
import br.com.reiki.integracao.entity.Usuario;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService, Serializable {

	private static final long serialVersionUID = -6155500423055083078L;

	private static final Logger LOGGER = Logger.getLogger(SegurancaServiceImpl.class);

	@Autowired
	private UsuarioDao usuarioDao;

	public Usuario autenticarUsuario(String login, String senha) throws ReikiException {
		Usuario usuario = null;
		try {
			usuario = usuarioDao.consultarPorLogin(login);
		} catch (NoResultException noResultException) {
			throw new ReikiException("msg.usuario.inexistente");
		} catch (Exception exception) {
			LOGGER.error("Metodo: autenticarUsuario", exception);
			throw new ReikiException("msg.falha.autenticar.sistema");
		}
		if (!usuario.getSenha().equals(senha)) {
			throw new ReikiException("msg.login.senha.nao.confere");
		}
		return usuario;
	}

}
