package br.com.reiki.integracao.constant;

public enum StatusPredefinidoEnum {

	SIM("S"), NAO("N");

	private String codigo;

	StatusPredefinidoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
}
