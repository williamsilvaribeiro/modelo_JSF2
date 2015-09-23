package br.com.reiki.integracao.dao;

import br.com.reiki.infra.dao.GenericDao;
import br.com.reiki.integracao.entity.Usuario;

import javax.persistence.NoResultException;

public interface UsuarioDao extends GenericDao<Usuario> {

	public Usuario consultarPorLogin(String login) throws NoResultException;

}
