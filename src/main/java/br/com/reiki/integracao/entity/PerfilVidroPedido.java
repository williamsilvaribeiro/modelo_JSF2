package br.com.reiki.integracao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_perfil_vidro_pedido", schema = "dbreiki")
public class PerfilVidroPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil_vidro_pedido")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_item_pedido", nullable = false, insertable = false, updatable = false)
	private ItemPedido itemPedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil_pedido", nullable = false)
	private PerfilVidro perfilVidro;

	@Column(name = "vl_unitario", nullable = false)
	private Double valorUnitario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

	public PerfilVidro getPerfilVidro() {
		return perfilVidro;
	}

	public void setPerfilVidro(PerfilVidro perfilVidro) {
		this.perfilVidro = perfilVidro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
