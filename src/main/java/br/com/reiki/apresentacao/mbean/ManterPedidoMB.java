package br.com.reiki.apresentacao.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.reiki.apresentacao.session.SessionBean;
import br.com.reiki.infra.exception.ReikiException;
import br.com.reiki.infra.message.Message;
import br.com.reiki.integracao.constant.TipoPessoaEnum;
import br.com.reiki.integracao.entity.Cliente;
import br.com.reiki.integracao.entity.Funcionario;
import br.com.reiki.integracao.entity.ItemPedido;
import br.com.reiki.integracao.entity.PagamentoPedido;
import br.com.reiki.integracao.entity.Pedido;
import br.com.reiki.integracao.entity.SituacaoPedido;
import br.com.reiki.integracao.entity.TipoPagamento;
import br.com.reiki.integracao.entity.Uf;
import br.com.reiki.negocio.service.DominioService;
import br.com.reiki.negocio.service.FuncionarioService;
import br.com.reiki.negocio.service.PedidoService;

@ViewScoped
@ManagedBean(name = "manterPedidoMB")
public class ManterPedidoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message;
	
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{pedidoService}")
	private PedidoService pedidoService;
	
	@ManagedProperty(value = "#{funcionarioService}")
	private FuncionarioService funcionarioService;
	
	@ManagedProperty(value = "#{dominioService}")
	private DominioService dominioService;
	
	private Pedido pedidoConsulta;

	private Pedido pedidoCadastro;
	
	private ItemPedido itemPedidoCadastro;
	
	private String qtdeParcela;

	private List<Pedido> listaPedido;
	
	private List<Funcionario> listaVendedor;
	
	private List<SituacaoPedido> listaSituacaoPedido;
	
	private List<Uf> listaUf;
	
	private List<TipoPagamento> listaTipoPagamento;

	public String inicializar() {
		sessionBean.limparSessao();
		return "consultarPedido";
	}

	@PostConstruct
	public void init() {
		this.message = new Message();
		this.listaPedido = new ArrayList<Pedido>();
		
		this.listaVendedor = funcionarioService.recuperaTodosVendedoresAtivos();
		this.listaSituacaoPedido = dominioService.recuperaTodasSituacaoPedido();
		this.listaTipoPagamento = dominioService.recuperaTodosTipoPagamento();
		this.listaUf = dominioService.recuperaTodasUf();
		
		this.pedidoConsulta = new Pedido();
		if(this.pedidoCadastro == null){
			if(getSessionBean().recuperarObjeto("pedidoCadastro") == null){
				this.pedidoCadastro = new Pedido();
				this.pedidoCadastro.getCliente().setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
				this.pedidoCadastro.setIndicacao("N");
				if(this.listaVendedor != null && !this.listaVendedor.isEmpty()){
					Funcionario vendedor = this.listaVendedor.get(0);
					this.pedidoCadastro.setVendedor(vendedor);
					this.pedidoCadastro.setPercentualComissaoVenda(vendedor.getPercentualComissaoVenda());
				}
				if(this.listaSituacaoPedido != null && !this.listaSituacaoPedido.isEmpty()){
					this.pedidoCadastro.setSituacaoPedido(this.listaSituacaoPedido.get(0));
				}
			} else {
				this.pedidoCadastro = (Pedido) getSessionBean().recuperarObjeto("pedidoCadastro");
				
				if(getSessionBean().recuperarObjeto("clienteSelecionado") != null){
					Cliente cliente = (Cliente) getSessionBean().recuperarObjeto("clienteSelecionado");
					this.pedidoCadastro.setCliente(cliente);
					getSessionBean().removerObjeto("clienteSelecionado");
				}
				
				getSessionBean().removerObjeto("pedidoCadastro");
			}
		}
		
		this.itemPedidoCadastro = new ItemPedido();
	}

	public void consultar() {
		this.listaPedido = pedidoService.consultarPedido(pedidoConsulta);
		if (this.listaPedido.isEmpty()) {
			message.addInfo("msg.geral.nenhum.registro.encontrado");
		}
	}

	public String iniciarNovoCadastro() {
		this.pedidoCadastro = new Pedido();
		return "cadastrarPedido";
	}

	public String alterar(Pedido pedido) {
		pedido = pedidoService.carregarPedidoCompleto(pedido.getId());
		getSessionBean().guardarObjeto("pedidoCadastro", pedido);
		return "cadastrarPedido";
	}

	public void excluir(Pedido pedido) {
		try {
			pedidoService.deletarPedido(pedido.getId());
			this.listaPedido.remove(pedido);
			message.addInfo("msg.geral.registro.excluido.sucesso");
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
	}

	public String salvar() {
		String retorno = null;
		try {
			pedidoService.gravarPedido(pedidoCadastro);
			message.addInfo("msg.geral.registro.cadastrado.sucesso");
			retorno = "consultarPedido";
		} catch (ReikiException reikiException) {
			message.addError(reikiException.getMessage());
		}
		return retorno;
	}

	public String voltar() {
		this.pedidoConsulta = new Pedido();
		this.pedidoCadastro = new Pedido();
		this.listaPedido = new ArrayList<Pedido>();
		return "consultarPedido";
	}
	
	public String consultarCliente() {
		getSessionBean().guardarObjeto("pedidoCadastro", pedidoCadastro);
		getSessionBean().guardarObjeto("funcaoPesquisar", true);
		getSessionBean().guardarObjeto("retorno", "cadastrarPedido");
		return "consultarCliente";
	}
	
	public void limparCliente() {
		this.pedidoCadastro.setCliente(new Cliente());
		this.pedidoCadastro.getCliente().setTipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());
		getSessionBean().guardarObjeto("pedidoCadastro", this.pedidoCadastro);
	}
	
	// -----------
	
	public void adicionarItemPedido() {
		this.itemPedidoCadastro = new ItemPedido();
		
		Integer numeroItem = getPedidoCadastro().getListaItemPedido().size() + 1;
		this.itemPedidoCadastro.setNumeroItem(numeroItem);
	}
	
	public void concluirCadastroItemPedido() {
		
		if(!getPedidoCadastro().getListaItemPedido().contains(getItemPedidoCadastro())){
			getPedidoCadastro().getListaItemPedido().add(getItemPedidoCadastro());
		}
		RequestContext.getCurrentInstance().execute("PF('modalItemPedido').hide()");
	}
	
	public void editarItemPedido(ItemPedido itemPedido) {
		this.itemPedidoCadastro = itemPedido;
	}
	
	public void excluirItemPedido(ItemPedido itemPedido) {
		if(getPedidoCadastro() != null){
			Iterator<ItemPedido> i = getPedidoCadastro().getListaItemPedido().iterator();
			while (i.hasNext()){
				ItemPedido itemPedidoCorrente = i.next();
				if(itemPedidoCorrente.equals(itemPedido)){
					i.remove();
				}
		    }
		}
	}
	
	// -----------

	public void adicionarPagamento() {
		PagamentoPedido pagamentoPedido = new PagamentoPedido();
		Integer numeroParcela = getPedidoCadastro().getListaPagamentoPedido().size() + 1;
		pagamentoPedido.setNumeroParcela(numeroParcela);
		getPedidoCadastro().getListaPagamentoPedido().add(pagamentoPedido);
	}
	
	public void excluirPagamento(PagamentoPedido pagamentoPedido) {
		if(getPedidoCadastro() != null){
			Iterator<PagamentoPedido> i = getPedidoCadastro().getListaPagamentoPedido().iterator();
			while (i.hasNext()){
				PagamentoPedido pagamentoPedidoCorrente = i.next();
				if(pagamentoPedidoCorrente.equals(pagamentoPedido)){
					i.remove();
				}
		    }
			int numeroParcela = 1;
			for (PagamentoPedido pgtoPedido : getPedidoCadastro().getListaPagamentoPedido()){
				pgtoPedido.setNumeroParcela(numeroParcela);
				numeroParcela = numeroParcela + 1;
			}
		}
	}
	
	// ----------
	
	public void atualizarPercentualComissaoVenda(){
		Integer idVendedor = null;
		if(this.pedidoCadastro.getVendedor() != null && this.pedidoCadastro.getVendedor().getId() != null){
			idVendedor = this.pedidoCadastro.getVendedor().getId();
			for (Funcionario vendedor : this.listaVendedor) {
				if(vendedor.getId().equals(idVendedor)){
					this.pedidoCadastro.setVendedor(vendedor);
					this.pedidoCadastro.setPercentualComissaoVenda(vendedor.getPercentualComissaoVenda());
				}
			}
		}
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

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public DominioService getDominioService() {
		return dominioService;
	}

	public void setDominioService(DominioService dominioService) {
		this.dominioService = dominioService;
	}

	public Pedido getPedidoConsulta() {
		return pedidoConsulta;
	}

	public void setPedidoConsulta(Pedido pedidoConsulta) {
		this.pedidoConsulta = pedidoConsulta;
	}

	public Pedido getPedidoCadastro() {
		return pedidoCadastro;
	}

	public void setPedidoCadastro(Pedido pedidoCadastro) {
		this.pedidoCadastro = pedidoCadastro;
	}

	public ItemPedido getItemPedidoCadastro() {
		return itemPedidoCadastro;
	}

	public void setItemPedidoCadastro(ItemPedido itemPedidoCadastro) {
		this.itemPedidoCadastro = itemPedidoCadastro;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public String getQtdeParcela() {
		return qtdeParcela;
	}

	public void setQtdeParcela(String qtdeParcela) {
		this.qtdeParcela = qtdeParcela;
	}

	public List<Funcionario> getListaVendedor() {
		return listaVendedor;
	}

	public void setListaVendedor(List<Funcionario> listaVendedor) {
		this.listaVendedor = listaVendedor;
	}

	public List<SituacaoPedido> getListaSituacaoPedido() {
		return listaSituacaoPedido;
	}

	public void setListaSituacaoPedido(List<SituacaoPedido> listaSituacaoPedido) {
		this.listaSituacaoPedido = listaSituacaoPedido;
	}

	public List<Uf> getListaUf() {
		return listaUf;
	}

	public void setListaUf(List<Uf> listaUf) {
		this.listaUf = listaUf;
	}

	public List<TipoPagamento> getListaTipoPagamento() {
		return listaTipoPagamento;
	}

	public void setListaTipoPagamento(List<TipoPagamento> listaTipoPagamento) {
		this.listaTipoPagamento = listaTipoPagamento;
	}
	
}
