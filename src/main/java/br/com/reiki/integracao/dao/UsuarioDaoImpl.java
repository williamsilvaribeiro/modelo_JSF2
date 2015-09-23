package br.com.reiki.integracao.dao;

import java.io.Serializable;

import br.com.reiki.infra.dao.GenericDaoImpl;
import br.com.reiki.integracao.entity.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository("usuarioDao")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao, Serializable {

	private static final long serialVersionUID = 1L;

	public Usuario consultarPorLogin(String login) throws NoResultException {
		Query query = getEntityManager().createQuery("select u from Usuario u where u.login = :login");
		query.setParameter("login", login);
		return (Usuario) query.getSingleResult();
	}

}
