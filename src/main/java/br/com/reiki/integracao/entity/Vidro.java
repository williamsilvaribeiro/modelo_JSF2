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
@Table(name = "tb_vidro", schema = "dbreiki")
public class Vidro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vidro")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_vidro", nullable = false)
	private TipoVidro tipoVidro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cor_vidro", nullable = false)
	private CorVidro corVidro;

	@Column(name = "nu_milimetro", nullable = false)
	private Integer milimetro;

	@Column(name = "vl_unitario", nullable = false)
	private Double valorUnitario;

	public Vidro() {
		this.tipoVidro = new TipoVidro();
		this.corVidro = new CorVidro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoVidro getTipoVidro() {
		return tipoVidro;
	}

	public void setTipoVidro(TipoVidro tipoVidro) {
		this.tipoVidro = tipoVidro;
	}

	public CorVidro getCorVidro() {
		return corVidro;
	}

	public void setCorVidro(CorVidro corVidro) {
		this.corVidro = corVidro;
	}

	public Integer getMilimetro() {
		return milimetro;
	}

	public void setMilimetro(Integer milimetro) {
		this.milimetro = milimetro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
