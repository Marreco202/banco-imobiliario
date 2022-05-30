package exeptions;

import model.Cor;

public class SaldoJogadorInsuficiente extends Exception {

	private static final long serialVersionUID = 1L;
	private Cor jogador;
	
	public SaldoJogadorInsuficiente(String errorMessage, Cor jogador) {
		super(errorMessage);
		this.jogador = jogador;
	}
	
	public Cor getJogador() {
		return jogador;
	}
	
}
