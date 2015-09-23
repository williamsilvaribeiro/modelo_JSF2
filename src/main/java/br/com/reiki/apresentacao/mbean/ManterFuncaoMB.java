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
import br.com.reiki.integracao.constant.StatusPredefinidoEnum;
import br.com.reiki.integracao.entity.Funcao;
import br.com.reiki.negocio.service.FuncaoService;

@ViewScoped
@ManagedBean(name = "manterFuncaoMB")
public class ManterFuncaoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Message message;

	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{funcaoService}")
	private FuncaoService funcaoService;

	private Funcao funcaoConsulta;

	private Funcao funcaoCadastro;

	private List<Funcao> listaFuncao;

	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarFuncao";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.funcaoConsulta = new Funcao();
		this.funcaoCadastro = new Funcao();
		this.listaFuncao = new ArrayList<Funcao>();
		this.message = new Message();
	}

	public void consultar() {
		this.listaFuncao = funcaoService.consultarFuncao(funcaoConsulta);
		if (this.listaFuncao.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.funcaoCadastro = new Funcao();
		return "cadastrarFuncao";
	}

	public String alterar(Funcao funcao) {
		getSessionBean().guardarObjeto("funcaoCadastro", funcao);
		return "cadastrarFuncao";
	}

	public void excluir(Funcao funcao) {
		try {
			funcaoService.deletarFuncao(funcao.getId());
			this.listaFuncao.remove(funcao);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());

		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			funcaoService.gravarFuncao(funcaoCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarFuncao";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.funcaoConsulta = new Funcao();
		this.funcaoCadastro = new Funcao();
		this.listaFuncao = new ArrayList<Funcao>();
		return "consultarFuncao";
	}
	
	public Boolean renderizarBotoesEdicao(Funcao funcao) {
		Boolean renderiza = false;
		if(funcao != null && funcao.getStatusPredefinido() != null){
			if(funcao.getStatusPredefinido().equals(StatusPredefinidoEnum.NAO.getCodigo())){
				renderiza = true;
			}
		}
		return renderiza;
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

	public FuncaoService getFuncaoService() {
		return funcaoService;
	}

	public void setFuncaoService(FuncaoService funcaoService) {
		this.funcaoService = funcaoService;
	}

	public Funcao getFuncaoConsulta() {
		return funcaoConsulta;
	}

	public void setFuncaoConsulta(Funcao funcaoConsulta) {
		this.funcaoConsulta = funcaoConsulta;
	}

	public Funcao getFuncaoCadastro() {
		if (sessionBean.recuperarObjeto("funcaoCadastro") != null) {
			funcaoCadastro = (Funcao) sessionBean.recuperarObjeto("funcaoCadastro");
			sessionBean.removerObjeto("funcaoCadastro");
		}
		return funcaoCadastro;
	}

	public void setFuncaoCadastro(Funcao funcaoCadastro) {
		this.funcaoCadastro = funcaoCadastro;
	}

	public List<Funcao> getListaFuncao() {
		return listaFuncao;
	}

	public void setListaFuncao(List<Funcao> listaFuncao) {
		this.listaFuncao = listaFuncao;
	}
	
}
