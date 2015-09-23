package br.com.reiki.integracao.constant;

public enum IndicacaoPedidoEnum {

	SIM("S"), NAO("N");

	private String codigo;

	IndicacaoPedidoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
}
