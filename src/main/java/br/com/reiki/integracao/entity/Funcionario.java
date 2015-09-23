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
@Table(name = "tb_funcionario", schema = "dbreiki")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcionario")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcao", nullable = false)
	private Funcao funcao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta_bancaria", nullable = false)
	private ContaBancaria contaBancaria;

	@Column(name = "st_ativo", nullable = false, length = 1)
	private String statusAtivo;

	@Column(name = "tx_nome", nullable = false, length = 70)
	private String nome;

	@Column(name = "tp_pessoa", nullable = false, length = 2)
	private String tipoPessoa;

	@Column(name = "tx_cpf", nullable = true, length = 15)
	private String cpf;
	
	@Column(name = "tx_rg", nullable = true, length = 20)
	private String rg;

	@Column(name = "tx_cnpj", nullable = true, length = 20)
	private String cnpj;

	@Column(name = "tx_inscricao_estadual", nullable = true, length = 20)
	private String inscricaoEstadual;

	@Column(name = "pc_comissao_venda", nullable = true)
	private Double percentualComissaoVenda;

	public Funcionario() {
		this.funcao = new Funcao();
		this.contaBancaria = new ContaBancaria();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public String getStatusAtivo() {
		return statusAtivo;
	}

	public void setStatusAtivo(String statusAtivo) {
		this.statusAtivo = statusAtivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Double getPercentualComissaoVenda() {
		return percentualComissaoVenda;
	}

	public void setPercentualComissaoVenda(Double percentualComissaoVenda) {
		this.percentualComissaoVenda = percentualComissaoVenda;
	}

}
