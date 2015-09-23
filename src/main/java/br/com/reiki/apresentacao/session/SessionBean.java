package br.com.reiki.apresentacao.session;

import br.com.reiki.integracao.entity.Usuario;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "sessionBean")
public class SessionBean implements Serializable {

	private static final long serialVersionUID = -8486049960187571629L;

	private Usuario usuario;

	@SuppressWarnings("rawtypes")
	private final Map mapa = new HashMap();

	public SessionBean() {
	}

	// GETTERS E SETTERS

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Object recuperarObjeto(String key) {
		return mapa.get(key);
	}

	@SuppressWarnings("unchecked")
	public void guardarObjeto(String key, Object obj) {
		this.mapa.put(key, obj);
	}

	public void removerObjeto(String key) {
		this.mapa.remove(key);
	}

	public void limparSessao() {
		this.mapa.clear();
	}

}
