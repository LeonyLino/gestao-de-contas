package br.com.apirest.estudando.util;

public enum TipoTransacao {
	DEPOSITO("deposito"), SAQUE("saque");
	
	private String tipo;
	
	private TipoTransacao(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipoTransacao() {
		return tipo;
	}
}
