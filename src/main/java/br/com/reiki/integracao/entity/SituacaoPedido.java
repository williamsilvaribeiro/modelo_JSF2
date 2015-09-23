package br.com.reiki.integracao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_situacao_pedido", schema = "dbreiki")
public class SituacaoPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final Integer SOLICITADO = new Integer("1");
	
	public static final Integer ATENDIDO = new Integer("2");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_situacao_pedido")
	private Integer id;

	@Column(name = "ds_situacao_pedido", nullable = false, length = 50)
	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
