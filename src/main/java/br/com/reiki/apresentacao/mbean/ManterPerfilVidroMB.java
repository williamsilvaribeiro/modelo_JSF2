package br.com.reiki.apresentacao.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.reiki.apresentacao.session.SessionBean;
import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.infra.message.Message;
import br.com.reiki.integracao.entity.PerfilVidro;
import br.com.reiki.negocio.service.PerfilVidroService;

@ViewScoped
@ManagedBean(name = "manterPerfilVidroMB")
public class ManterPerfilVidroMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{perfilVidroService}")
	private PerfilVidroService perfilVidroService;

	private PerfilVidro perfilVidroConsulta;

	private PerfilVidro perfilVidroCadastro;

	private List<PerfilVidro> listaPerfilVidro;

	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarPerfilVidro";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.perfilVidroConsulta = new PerfilVidro();
		this.perfilVidroCadastro = new PerfilVidro();
		this.listaPerfilVidro = new ArrayList<PerfilVidro>();
		this.message = new Message();
	}

	public void consultar() {
		this.listaPerfilVidro = perfilVidroService.consultarPerfilVidro(perfilVidroConsulta);
		if (this.listaPerfilVidro.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.perfilVidroCadastro = new PerfilVidro();
		return "cadastrarPerfilVidro";
	}

	public String alterar(PerfilVidro perfilVidro) {
		getSessionBean().guardarObjeto("perfilVidroCadastro", perfilVidro);
		return "cadastrarPerfilVidro";
	}

	public void excluir(PerfilVidro perfilVidro) {
		try {
			perfilVidroService.deletarPerfilVidro(perfilVidro.getId());
			this.listaPerfilVidro.remove(perfilVidro);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());

		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			perfilVidroService.gravarPerfilVidro(perfilVidroCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarPerfilVidro";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.perfilVidroConsulta = new PerfilVidro();
		this.perfilVidroCadastro = new PerfilVidro();
		this.listaPerfilVidro = new ArrayList<PerfilVidro>();
		return "consultarPerfilVidro";
	}

	// GETTERS E SETTERS

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public PerfilVidroService getPerfilVidroService() {
		return perfilVidroService;
	}

	public void setPerfilVidroService(PerfilVidroService perfilVidroService) {
		this.perfilVidroService = perfilVidroService;
	}

	public PerfilVidro getPerfilVidroConsulta() {
		return perfilVidroConsulta;
	}

	public void setPerfilVidroConsulta(PerfilVidro perfilVidroConsulta) {
		this.perfilVidroConsulta = perfilVidroConsulta;
	}

	public PerfilVidro getPerfilVidroCadastro() {
		if (sessionBean.recuperarObjeto("perfilVidroCadastro") != null) {
			perfilVidroCadastro = (PerfilVidro) sessionBean.recuperarObjeto("perfilVidroCadastro");
			sessionBean.removerObjeto("perfilVidroCadastro");
		}
		return perfilVidroCadastro;
	}

	public void setPerfilVidroCadastro(PerfilVidro perfilVidroCadastro) {
		this.perfilVidroCadastro = perfilVidroCadastro;
	}

	public List<PerfilVidro> getListaPerfilVidro() {
		return listaPerfilVidro;
	}

	public void setListaPerfilVidro(List<PerfilVidro> listaPerfilVidro) {
		this.listaPerfilVidro = listaPerfilVidro;
	}

}
