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
import br.com.reiki.integracao.entity.TipoVidro;
import br.com.reiki.integracao.entity.Vidro;
import br.com.reiki.negocio.service.CorVidroService;
import br.com.reiki.negocio.service.TipoVidroService;
import br.com.reiki.negocio.service.VidroService;

@ViewScoped
@ManagedBean(name = "manterVidroMB")
public class ManterVidroMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;
	
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{vidroService}")
	private VidroService vidroService;
	
	@ManagedProperty(value = "#{tipoVidroService}")
	private TipoVidroService tipoVidroService;
	
	@ManagedProperty(value = "#{corVidroService}")
	private CorVidroService corVidroService;
	
	private Vidro vidroConsulta;

	private Vidro vidroCadastro;

	private List<Vidro> listaVidro;
	
	private List<TipoVidro> listaTipoVidro;
	
	private List<CorVidro> listaCorVidro;
	
	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarVidro";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.listaVidro = new ArrayList<Vidro>();
		
		this.listaTipoVidro = tipoVidroService.recuperaTodos();
		this.listaCorVidro = corVidroService.recuperaTodas();
		
		this.vidroConsulta = new Vidro();
		
		if(this.vidroCadastro == null){
			if(getSessionBean().recuperarObjeto("vidroCadastro") == null){
				this.vidroCadastro = new Vidro();
			} else {
				this.vidroCadastro = (Vidro) getSessionBean().recuperarObjeto("vidroCadastro");
				getSessionBean().removerObjeto("vidroCadastro");
			}
			
		}
	}

	public void consultar() {
		this.listaVidro = vidroService.consultarVidro(vidroConsulta);
		if (this.listaVidro.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.vidroCadastro = new Vidro();
		return "cadastrarVidro";
	}

	public String alterar(Vidro vidro) {
		vidro = vidroService.carregarVidroCompleto(vidro.getId());
		getSessionBean().guardarObjeto("vidroCadastro", vidro);
		return "cadastrarVidro";
	}

	public void excluir(Vidro vidro) {
		try {
			vidroService.deletarVidro(vidro.getId());
			this.listaVidro.remove(vidro);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			vidroService.gravarVidro(vidroCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarVidro";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.vidroConsulta = new Vidro();
		this.vidroCadastro = new Vidro();
		this.listaVidro = new ArrayList<Vidro>();
		return "consultarVidro";
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

	public VidroService getVidroService() {
		return vidroService;
	}

	public void setVidroService(VidroService vidroService) {
		this.vidroService = vidroService;
	}

	public TipoVidroService getTipoVidroService() {
		return tipoVidroService;
	}

	public void setTipoVidroService(TipoVidroService tipoVidroService) {
		this.tipoVidroService = tipoVidroService;
	}

	public CorVidroService getCorVidroService() {
		return corVidroService;
	}

	public void setCorVidroService(CorVidroService corVidroService) {
		this.corVidroService = corVidroService;
	}

	public Vidro getVidroConsulta() {
		return vidroConsulta;
	}

	public void setVidroConsulta(Vidro vidroConsulta) {
		this.vidroConsulta = vidroConsulta;
	}

	public Vidro getVidroCadastro() {
		return vidroCadastro;
	}

	public void setVidroCadastro(Vidro vidroCadastro) {
		this.vidroCadastro = vidroCadastro;
	}

	public List<Vidro> getListaVidro() {
		return listaVidro;
	}

	public void setListaVidro(List<Vidro> listaVidro) {
		this.listaVidro = listaVidro;
	}

	public List<TipoVidro> getListaTipoVidro() {
		return listaTipoVidro;
	}

	public void setListaTipoVidro(List<TipoVidro> listaTipoVidro) {
		this.listaTipoVidro = listaTipoVidro;
	}

	public List<CorVidro> getListaCorVidro() {
		return listaCorVidro;
	}

	public void setListaCorVidro(List<CorVidro> listaCorVidro) {
		this.listaCorVidro = listaCorVidro;
	}
	
}
