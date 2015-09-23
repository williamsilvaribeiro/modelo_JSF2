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
import br.com.reiki.integracao.constant.TipoPessoaEnum;
import br.com.reiki.integracao.entity.Banco;
import br.com.reiki.integracao.entity.Funcao;
import br.com.reiki.integracao.entity.Funcionario;
import br.com.reiki.negocio.service.BancoService;
import br.com.reiki.negocio.service.FuncaoService;
import br.com.reiki.negocio.service.FuncionarioService;

@ViewScoped
@ManagedBean(name = "manterFuncionarioMB")
public class ManterFuncionarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;
	
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{funcionarioService}")
	private FuncionarioService funcionarioService;
	
	@ManagedProperty(value = "#{funcaoService}")
	private FuncaoService funcaoService;
	
	@ManagedProperty(value = "#{bancoService}")
	private BancoService bancoService;

	private Funcionario funcionarioConsulta;

	private Funcionario funcionarioCadastro;

	private List<Funcionario> listaFuncionario;
	
	private List<Funcao> listaFuncao;
	
	private List<Banco> listaBanco;

	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarFuncionario";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.listaFuncionario = new ArrayList<Funcionario>();
		
		this.listaFuncao = funcaoService.recuperaTodas();
		this.listaBanco = bancoService.recuperaTodos();
		
		this.funcionarioConsulta = new Funcionario();
		if(this.funcionarioCadastro == null){
			if(getSessionBean().recuperarObjeto("funcionarioCadastro") == null){
				this.funcionarioCadastro = new Funcionario();
				this.funcionarioCadastro.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
				if(this.listaFuncao != null && !this.listaFuncao.isEmpty()){
					this.funcionarioCadastro.setFuncao(this.listaFuncao.get(0));
				}
			} else {
				this.funcionarioCadastro = (Funcionario) getSessionBean().recuperarObjeto("funcionarioCadastro");
				getSessionBean().removerObjeto("funcionarioCadastro");
			}
			
		}
		
		this.listaFuncao = funcaoService.recuperaTodas();
		this.listaBanco = bancoService.recuperaTodos();
	}

	public void consultar() {
		this.listaFuncionario = funcionarioService.consultarFuncionario(funcionarioConsulta);
		if (this.listaFuncionario.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.funcionarioCadastro = new Funcionario();
		return "cadastrarFuncionario";
	}

	public String alterar(Funcionario funcionario) {
		funcionario = funcionarioService.carregarFuncionarioCompleto(funcionario.getId());
		getSessionBean().guardarObjeto("funcionarioCadastro", funcionario);
		return "cadastrarFuncionario";
	}

	public void excluir(Funcionario funcionario) {
		try {
			funcionarioService.deletarFuncionario(funcionario.getId());
			this.listaFuncionario.remove(funcionario);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			funcionarioService.gravarFuncionario(funcionarioCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarFuncionario";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.funcionarioConsulta = new Funcionario();
		this.funcionarioCadastro = new Funcionario();
		this.listaFuncionario = new ArrayList<Funcionario>();
		return "consultarFuncionario";
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

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public FuncaoService getFuncaoService() {
		return funcaoService;
	}

	public void setFuncaoService(FuncaoService funcaoService) {
		this.funcaoService = funcaoService;
	}

	public BancoService getBancoService() {
		return bancoService;
	}

	public void setBancoService(BancoService bancoService) {
		this.bancoService = bancoService;
	}

	public Funcionario getFuncionarioConsulta() {
		return funcionarioConsulta;
	}

	public void setFuncionarioConsulta(Funcionario funcionarioConsulta) {
		this.funcionarioConsulta = funcionarioConsulta;
	}

	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public List<Funcao> getListaFuncao() {
		return listaFuncao;
	}

	public void setListaFuncao(List<Funcao> listaFuncao) {
		this.listaFuncao = listaFuncao;
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

}
