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
import br.com.reiki.integracao.entity.Acessorio;
import br.com.reiki.negocio.service.AcessorioService;

@ViewScoped
@ManagedBean(name = "manterAcessorioMB")
public class ManterAcessorioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{acessorioService}")
	private AcessorioService acessorioService;

	private Acessorio acessorioConsulta;

	private Acessorio acessorioCadastro;

	private List<Acessorio> listaAcessorio;

	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarAcessorio";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.acessorioConsulta = new Acessorio();
		this.acessorioCadastro = new Acessorio();
		this.listaAcessorio = new ArrayList<Acessorio>();
		this.message = new Message();
	}

	public void consultar() {
		this.listaAcessorio = acessorioService.consultarAcessorio(acessorioConsulta);
		if (this.listaAcessorio.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.acessorioCadastro = new Acessorio();
		return "cadastrarAcessorio";
	}

	public String alterar(Acessorio acessorio) {
		getSessionBean().guardarObjeto("acessorioCadastro", acessorio);
		return "cadastrarAcessorio";
	}

	public void excluir(Acessorio acessorio) {
		try {
			acessorioService.deletarAcessorio(acessorio.getId());
			this.listaAcessorio.remove(acessorio);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());

		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			acessorioService.gravarAcessorio(acessorioCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarAcessorio";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.acessorioConsulta = new Acessorio();
		this.acessorioCadastro = new Acessorio();
		this.listaAcessorio = new ArrayList<Acessorio>();
		return "consultarAcessorio";
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

	public AcessorioService getAcessorioService() {
		return acessorioService;
	}

	public void setAcessorioService(AcessorioService acessorioService) {
		this.acessorioService = acessorioService;
	}

	public Acessorio getAcessorioConsulta() {
		return acessorioConsulta;
	}

	public void setAcessorioConsulta(Acessorio acessorioConsulta) {
		this.acessorioConsulta = acessorioConsulta;
	}

	public Acessorio getAcessorioCadastro() {
		if (sessionBean.recuperarObjeto("acessorioCadastro") != null) {
			acessorioCadastro = (Acessorio) sessionBean.recuperarObjeto("acessorioCadastro");
			sessionBean.removerObjeto("acessorioCadastro");
		}
		return acessorioCadastro;
	}

	public void setAcessorioCadastro(Acessorio acessorioCadastro) {
		this.acessorioCadastro = acessorioCadastro;
	}

	public List<Acessorio> getListaAcessorio() {
		return listaAcessorio;
	}

	public void setListaAcessorio(List<Acessorio> listaAcessorio) {
		this.listaAcessorio = listaAcessorio;
	}

}
