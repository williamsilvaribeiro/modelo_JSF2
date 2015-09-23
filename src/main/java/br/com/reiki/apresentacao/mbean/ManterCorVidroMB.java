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
import br.com.reiki.integracao.entity.CorVidro;
import br.com.reiki.negocio.service.CorVidroService;

@ViewScoped
@ManagedBean(name = "manterCorVidroMB")
public class ManterCorVidroMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{corVidroService}")
	private CorVidroService corVidroService;

	private CorVidro corVidroConsulta;

	private CorVidro corVidroCadastro;

	private List<CorVidro> listaCorVidro;

	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarCorVidro";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.corVidroConsulta = new CorVidro();
		this.corVidroCadastro = new CorVidro();
		this.listaCorVidro = new ArrayList<CorVidro>();
		this.message = new Message();
	}

	public void consultar() {
		this.listaCorVidro = corVidroService.consultarCorVidro(corVidroConsulta);
		if (this.listaCorVidro.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.corVidroCadastro = new CorVidro();
		return "cadastrarCorVidro";
	}

	public String alterar(CorVidro corVidro) {
		getSessionBean().guardarObjeto("corVidroCadastro", corVidro);
		return "cadastrarCorVidro";
	}

	public void excluir(CorVidro corVidro) {
		try {
			corVidroService.deletarCorVidro(corVidro.getId());
			this.listaCorVidro.remove(corVidro);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());

		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			corVidroService.gravarCorVidro(corVidroCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarCorVidro";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.corVidroConsulta = new CorVidro();
		this.corVidroCadastro = new CorVidro();
		this.listaCorVidro = new ArrayList<CorVidro>();
		return "consultarCorVidro";
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

	public CorVidroService getCorVidroService() {
		return corVidroService;
	}

	public void setCorVidroService(CorVidroService corVidroService) {
		this.corVidroService = corVidroService;
	}

	public CorVidro getCorVidroConsulta() {
		return corVidroConsulta;
	}

	public void setCorVidroConsulta(CorVidro corVidroConsulta) {
		this.corVidroConsulta = corVidroConsulta;
	}

	public CorVidro getCorVidroCadastro() {
		if (sessionBean.recuperarObjeto("corVidroCadastro") != null) {
			corVidroCadastro = (CorVidro) sessionBean.recuperarObjeto("corVidroCadastro");
			sessionBean.removerObjeto("corVidroCadastro");
		}
		return corVidroCadastro;
	}

	public void setCorVidroCadastro(CorVidro corVidroCadastro) {
		this.corVidroCadastro = corVidroCadastro;
	}

	public List<CorVidro> getListaCorVidro() {
		return listaCorVidro;
	}

	public void setListaCorVidro(List<CorVidro> listaCorVidro) {
		this.listaCorVidro = listaCorVidro;
	}

}
