package br.com.reiki.integracao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

@Entity
@Table(name = "tb_item_pedido", schema = "dbreiki")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido", nullable = false)
	private Pedido pedido;

	@Column(name = "nu_item", nullable = false)
	private Integer numeroItem;

	@Column(name = "nu_dias_entrega", nullable = false)
	private Integer numeroDiasEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_entrega", nullable = false)
	private Date dataEntrega;

	@Column(name = "tx_grupo", nullable = false, length = 1)
	private String grupo;

	@Column(name = "tx_ambiente", nullable = false, length = 50)
	private String ambiente;

	@Column(name = "nu_quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "nu_comprimento", nullable = false)
	private Integer comprimento;

	@Column(name = "nu_altura", nullable = false)
	private Integer altura;

	@Column(name = "vl_total_item", nullable = false)
	private Double valorTotalItem;

	@Column(name = "tx_observacao", nullable = true, length = 200)
	private String observacao;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_item_pedido", nullable = false)
	private List<VidroPedido> listaVidroPedido;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_item_pedido", nullable = false)
	private List<PerfilVidroPedido> listaPerfilVidroPedido;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_item_pedido", nullable = false)
	private List<AcessorioPedido> listaAcessorioPedido;
	
	public String getDescricaoVidro(){
		String descricao = "";
		return descricao;
	}
	
	public String getDescricaoAcabamento(){
		String descricao = "";
		return descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = numeroItem;
	}

	public Integer getNumeroDiasEntrega() {
		return numeroDiasEntrega;
	}

	public void setNumeroDiasEntrega(Integer numeroDiasEntrega) {
		this.numeroDiasEntrega = numeroDiasEntrega;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getComprimento() {
		return comprimento;
	}

	public void setComprimento(Integer comprimento) {
		this.comprimento = comprimento;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Double getValorTotalItem() {
		return valorTotalItem;
	}

	public void setValorTotalItem(Double valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<VidroPedido> getListaVidroPedido() {
		return listaVidroPedido;
	}

	public void setListaVidroPedido(List<VidroPedido> listaVidroPedido) {
		this.listaVidroPedido = listaVidroPedido;
	}

	public List<PerfilVidroPedido> getListaPerfilVidroPedido() {
		return listaPerfilVidroPedido;
	}

	public void setListaPerfilVidroPedido(List<PerfilVidroPedido> listaPerfilVidroPedido) {
		this.listaPerfilVidroPedido = listaPerfilVidroPedido;
	}

	public List<AcessorioPedido> getListaAcessorioPedido() {
		return listaAcessorioPedido;
	}

	public void setListaAcessorioPedido(List<AcessorioPedido> listaAcessorioPedido) {
		this.listaAcessorioPedido = listaAcessorioPedido;
	}

}
