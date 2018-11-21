package br.com.apirest.estudando.util;

public enum TipoConta {
	CORRENTE(1), POUPANÇA(2);
	
	private int tipoConta;
	
	private TipoConta(int tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public int getTipoConta() {
		return tipoConta;
	}
}
