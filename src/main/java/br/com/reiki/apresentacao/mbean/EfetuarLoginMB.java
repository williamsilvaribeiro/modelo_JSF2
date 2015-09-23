package br.com.reiki.apresentacao.mbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.reiki.apresentacao.session.SessionBean;
import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.infra.message.Message;
import br.com.reiki.integracao.entity.Usuario;
import br.com.reiki.negocio.service.SegurancaService;

@ViewScoped
@ManagedBean(name = "efetuarLoginMB")
public class EfetuarLoginMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Message message;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{segurancaService}")
	private SegurancaService segurancaService;

	public String login;

	public String senha;

	@PostConstruct
	public void init() {
		this.login = new String();
		this.senha = new String();
		this.message = new Message();
	}

	public String efetuarLogin() {
		String retorno = null;
		try {
			Usuario usuario = segurancaService.autenticarUsuario(login, senha);
			getSessionBean().setUsuario(usuario);
			retorno = "successo";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/paginas/seguranca/login.xhtml?faces-redirect=true";
	}

	// GETTERS E SETTERS
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public SegurancaService getSegurancaService() {
		return segurancaService;
	}

	public void setSegurancaService(SegurancaService segurancaService) {
		this.segurancaService = segurancaService;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
