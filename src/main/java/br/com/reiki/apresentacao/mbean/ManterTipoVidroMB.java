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
import br.com.reiki.integracao.entity.TipoVidro;
import br.com.reiki.negocio.service.TipoVidroService;

@ViewScoped
@ManagedBean(name = "manterTipoVidroMB")
public class ManterTipoVidroMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{tipoVidroService}")
	private TipoVidroService tipoVidroService;

	private TipoVidro tipoVidroConsulta;

	private TipoVidro tipoVidroCadastro;

	private List<TipoVidro> listaTipoVidro;

	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarTipoVidro";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.tipoVidroConsulta = new TipoVidro();
		this.tipoVidroCadastro = new TipoVidro();
		this.listaTipoVidro = new ArrayList<TipoVidro>();
		this.message = new Message();
	}

	public void consultar() {
		this.listaTipoVidro = tipoVidroService.consultarTipoVidro(tipoVidroConsulta);
		if (this.listaTipoVidro.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.tipoVidroCadastro = new TipoVidro();
		return "cadastrarTipoVidro";
	}

	public String alterar(TipoVidro tipoVidro) {
		getSessionBean().guardarObjeto("tipoVidroCadastro", tipoVidro);
		return "cadastrarTipoVidro";
	}

	public void excluir(TipoVidro tipoVidro) {
		try {
			tipoVidroService.deletarTipoVidro(tipoVidro.getId());
			this.listaTipoVidro.remove(tipoVidro);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());

		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			tipoVidroService.gravarTipoVidro(tipoVidroCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarTipoVidro";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.tipoVidroConsulta = new TipoVidro();
		this.tipoVidroCadastro = new TipoVidro();
		this.listaTipoVidro = new ArrayList<TipoVidro>();
		return "consultarTipoVidro";
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

	public TipoVidroService getTipoVidroService() {
		return tipoVidroService;
	}

	public void setTipoVidroService(TipoVidroService tipoVidroService) {
		this.tipoVidroService = tipoVidroService;
	}

	public TipoVidro getTipoVidroConsulta() {
		return tipoVidroConsulta;
	}

	public void setTipoVidroConsulta(TipoVidro tipoVidroConsulta) {
		this.tipoVidroConsulta = tipoVidroConsulta;
	}

	public TipoVidro getTipoVidroCadastro() {
		if (sessionBean.recuperarObjeto("tipoVidroCadastro") != null) {
			tipoVidroCadastro = (TipoVidro) sessionBean.recuperarObjeto("tipoVidroCadastro");
			sessionBean.removerObjeto("tipoVidroCadastro");
		}
		return tipoVidroCadastro;
	}

	public void setTipoVidroCadastro(TipoVidro tipoVidroCadastro) {
		this.tipoVidroCadastro = tipoVidroCadastro;
	}

	public List<TipoVidro> getListaTipoVidro() {
		return listaTipoVidro;
	}

	public void setListaTipoVidro(List<TipoVidro> listaTipoVidro) {
		this.listaTipoVidro = listaTipoVidro;
	}

}
