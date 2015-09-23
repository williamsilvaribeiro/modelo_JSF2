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
import br.com.reiki.integracao.entity.Cliente;
import br.com.reiki.negocio.service.ClienteService;

@ViewScoped
@ManagedBean(name = "manterClienteMB")
public class ManterClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;
	
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{clienteService}")
	private ClienteService clienteService;
	
	private Cliente clienteConsulta;

	private Cliente clienteCadastro;

	private List<Cliente> listaCliente;
	
	public String inicializar() {
		getSessionBean().limparSessao();
		return "consultarCliente";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.listaCliente = new ArrayList<Cliente>();
		this.clienteConsulta = new Cliente();
		
		if(this.clienteCadastro == null){
			if(getSessionBean().recuperarObjeto("clienteCadastro") == null){
				this.clienteCadastro = new Cliente();
				this.clienteCadastro.setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
			} else {
				this.clienteCadastro = (Cliente) getSessionBean().recuperarObjeto("clienteCadastro");
				getSessionBean().removerObjeto("clienteCadastro");
			}
			
		}
	}

	public void consultar() {
		this.listaCliente = clienteService.consultarCliente(clienteConsulta);
		if (this.listaCliente.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.clienteCadastro = new Cliente();
		return "cadastrarCliente";
	}

	public String alterar(Cliente cliente) {
		cliente = clienteService.carregarClienteCompleto(cliente.getId());
		getSessionBean().guardarObjeto("clienteCadastro", cliente);
		return "cadastrarCliente";
	}

	public void excluir(Cliente cliente) {
		try {
			clienteService.deletarCliente(cliente.getId());
			this.listaCliente.remove(cliente);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
	}

	public String cadastrar() {
		String retorno = null;
		try {
			clienteService.gravarCliente(clienteCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarCliente";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.clienteConsulta = new Cliente();
		this.clienteCadastro = new Cliente();
		this.listaCliente = new ArrayList<Cliente>();
		return "consultarCliente";
	}
	
	public Boolean getRenderizaCampoFuncaoPesquisar(){
		Boolean renderiza = false;
		Boolean funcaoPesquisar = (Boolean) getSessionBean().recuperarObjeto("funcaoPesquisar");
		if(funcaoPesquisar != null && funcaoPesquisar){
			renderiza = true;
		}
		return renderiza;
	}
	
	public String selecionarRegistroPesquisa(Cliente cliente){
		getSessionBean().guardarObjeto("clienteSelecionado", cliente);
		return (String) getSessionBean().recuperarObjeto("retorno");
	}
	
	public String voltarPesquisaCliente(){
		getSessionBean().removerObjeto("funcaoPesquisar");
		getSessionBean().removerObjeto("retorno");
		return "cadastrarPedido";
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

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cliente getClienteConsulta() {
		return clienteConsulta;
	}

	public void setClienteConsulta(Cliente clienteConsulta) {
		this.clienteConsulta = clienteConsulta;
	}

	public Cliente getClienteCadastro() {
		return clienteCadastro;
	}

	public void setClienteCadastro(Cliente clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
}
