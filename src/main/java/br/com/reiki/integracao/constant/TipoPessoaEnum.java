package br.com.reiki.integracao.constant;

public enum TipoPessoaEnum {

	PESSOA_FISICA("PF"), PESSOA_JURIDICA("PJ");

	private String codigo;

	TipoPessoaEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
