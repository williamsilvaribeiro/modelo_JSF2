package br.com.reiki.integracao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.reiki.integracao.constant.FuncaoEnum;

@Entity
@Table(name = "tb_pedido", schema = "dbreiki")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endereco_obra", nullable = false)
	private EnderecoObra enderecoObra;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vendedor", nullable = false)
	private Funcionario vendedor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situacao_pedido", nullable = false)
	private SituacaoPedido situacaoPedido;

	@Column(name = "nu_pedido", nullable = false)
	private Integer numero;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_emissao", nullable = false)
	private Date dataEmissao;

	@Column(name = "vl_sub_total", nullable = false)
	private Double valorSubTotal;

	@Column(name = "vl_desconto", nullable = false)
	private Double valorDesconto;

	@Column(name = "vl_total", nullable = false)
	private Double valorTotal;

	@Column(name = "tx_observacao", nullable = false, length = 200)
	private String observacao;

	@Column(name = "st_indicacao", nullable = false, length = 1)
	private String indicacao;
	
	@Column(name = "tx_nome_indicacao", nullable = true, length = 70)
	private String nomeIndicacao;
	
	@Column(name = "pc_comissao_indicacao", nullable = true)
	private Double percentualComissaoIndicacao;
	
	@Column(name = "vl_comissao_indicacao", nullable = true)
	private Double valorComissaoIndicacao;

	@Column(name = "pc_comissao_venda", nullable = false)
	private Double percentualComissaoVenda;

	@Column(name = "vl_comissao_venda", nullable = false)
	private Double valorComissaoVenda;
	
	@OneToMany(mappedBy = "pedido", targetEntity = ItemPedido.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemPedido> listaItemPedido;
	
	@OneToMany(mappedBy = "pedido", targetEntity = PagamentoPedido.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PagamentoPedido> listaPagamentoPedido;

	public Pedido() {
		super();
		this.enderecoObra = new EnderecoObra();
		this.cliente = new Cliente();
		this.vendedor = new Funcionario();
		this.vendedor.getFuncao().setId(FuncaoEnum.VENDEDOR.getCodigo());
		this.situacaoPedido = new SituacaoPedido();
		this.listaItemPedido = new ArrayList<ItemPedido>();
		this.listaPagamentoPedido = new ArrayList<PagamentoPedido>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnderecoObra getEnderecoObra() {
		return enderecoObra;
	}

	public void setEnderecoObra(EnderecoObra enderecoObra) {
		this.enderecoObra = enderecoObra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Funcionario vendedor) {
		this.vendedor = vendedor;
	}

	public SituacaoPedido getSituacaoPedido() {
		return situacaoPedido;
	}

	public void setSituacaoPedido(SituacaoPedido situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Double getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(Double valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}

	public String getNomeIndicacao() {
		return nomeIndicacao;
	}

	public void setNomeIndicacao(String nomeIndicacao) {
		this.nomeIndicacao = nomeIndicacao;
	}

	public Double getPercentualComissaoIndicacao() {
		return percentualComissaoIndicacao;
	}

	public void setPercentualComissaoIndicacao(Double percentualComissaoIndicacao) {
		this.percentualComissaoIndicacao = percentualComissaoIndicacao;
	}

	public Double getValorComissaoIndicacao() {
		return valorComissaoIndicacao;
	}

	public void setValorComissaoIndicacao(Double valorComissaoIndicacao) {
		this.valorComissaoIndicacao = valorComissaoIndicacao;
	}

	public Double getPercentualComissaoVenda() {
		return percentualComissaoVenda;
	}

	public void setPercentualComissaoVenda(Double percentualComissaoVenda) {
		this.percentualComissaoVenda = percentualComissaoVenda;
	}

	public Double getValorComissaoVenda() {
		return valorComissaoVenda;
	}

	public void setValorComissaoVenda(Double valorComissaoVenda) {
		this.valorComissaoVenda = valorComissaoVenda;
	}

	public List<ItemPedido> getListaItemPedido() {
		return listaItemPedido;
	}

	public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}

	public List<PagamentoPedido> getListaPagamentoPedido() {
		return listaPagamentoPedido;
	}

	public void setListaPagamentoPedido(List<PagamentoPedido> listaPagamentoPedido) {
		this.listaPagamentoPedido = listaPagamentoPedido;
	}

}
