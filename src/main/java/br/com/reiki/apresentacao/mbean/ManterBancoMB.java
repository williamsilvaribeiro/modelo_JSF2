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
import br.com.reiki.integracao.entity.Banco;
import br.com.reiki.negocio.service.BancoService;

@ViewScoped
@ManagedBean(name = "manterBancoMB")
public class ManterBancoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{bancoService}")
	private BancoService bancoService;

	private Banco bancoConsulta;

	private Banco bancoCadastro;

	private List<Banco> listaBanco;

	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarBanco";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.bancoConsulta = new Banco();
		this.bancoCadastro = new Banco();
		this.listaBanco = new ArrayList<Banco>();
		this.message = new Message();
	}

	public void consultar() {
		this.listaBanco = bancoService.consultarBanco(bancoConsulta);
		if (this.listaBanco.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.bancoCadastro = new Banco();
		return "cadastrarBanco";
	}

	public String alterar(Banco banco) {
		getSessionBean().guardarObjeto("bancoCadastro", banco);
		return "cadastrarBanco";
	}

	public void excluir(Banco banco) {
		try {
			bancoService.deletarBanco(banco.getId());
			this.listaBanco.remove(banco);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());

		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			bancoService.gravarBanco(bancoCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarBanco";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.bancoConsulta = new Banco();
		this.bancoCadastro = new Banco();
		this.listaBanco = new ArrayList<Banco>();
		return "consultarBanco";
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

	public BancoService getBancoService() {
		return bancoService;
	}

	public void setBancoService(BancoService bancoService) {
		this.bancoService = bancoService;
	}

	public Banco getBancoConsulta() {
		return bancoConsulta;
	}

	public void setBancoConsulta(Banco bancoConsulta) {
		this.bancoConsulta = bancoConsulta;
	}

	public Banco getBancoCadastro() {
		if (sessionBean.recuperarObjeto("bancoCadastro") != null) {
			bancoCadastro = (Banco) sessionBean.recuperarObjeto("bancoCadastro");
			sessionBean.removerObjeto("bancoCadastro");
		}
		return bancoCadastro;
	}

	public void setBancoCadastro(Banco bancoCadastro) {
		this.bancoCadastro = bancoCadastro;
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

}
