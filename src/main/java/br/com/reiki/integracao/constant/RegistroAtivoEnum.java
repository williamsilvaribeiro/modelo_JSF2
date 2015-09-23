package br.com.reiki.integracao.constant;

public enum RegistroAtivoEnum {

	SIM("S"), NAO("N");

	private String codigo;

	RegistroAtivoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
}
