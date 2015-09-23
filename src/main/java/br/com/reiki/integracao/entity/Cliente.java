package br.com.reiki.integracao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cliente", schema = "dbreiki")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer id;

	@Column(name = "tx_nome", nullable = false, length = 50)
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

	@Column(name = "tx_telefone_fixo", nullable = false, length = 20)
	private String telefoneFixo;

	@Column(name = "tx_telefone_celular", nullable = false, length = 20)
	private String telefoneCelular;

	@Column(name = "tx_email", nullable = true, length = 50)
	private String email;

	@Column(name = "st_registro_ativo", nullable = false, length = 1)
	private String registroAtivo;
	
	@Column(name = "tx_observacao", nullable = true, length = 200)
	private String observacao;

	public Cliente() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistroAtivo() {
		return registroAtivo;
	}

	public void setRegistroAtivo(String registroAtivo) {
		this.registroAtivo = registroAtivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
