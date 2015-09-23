package br.com.reiki.infra.exception;

public class ReikiException extends Exception {

	private static final long serialVersionUID = 4204627119454502107L;

	private String messagem;

	public ReikiException() {
		super();
	}

	public ReikiException(String message) {
		super(message);
		this.messagem = message;
	}

	public String getMessagem() {
		return messagem;
	}

	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}

}
