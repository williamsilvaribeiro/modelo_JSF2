package br.com.reiki.integracao.constant;

public enum FuncaoEnum {

	ARQUITETO(1), EMPREITEIRO(2), FUNCIONARIO(3), VENDEDOR(4);

	private Integer codigo;

	FuncaoEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
}
