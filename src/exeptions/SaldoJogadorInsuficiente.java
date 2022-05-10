package exeptions;

import model.Player;

public class SaldoJogadorInsuficiente extends Exception {

	private static final long serialVersionUID = 1L;
	private Player jogador;
	
	public SaldoJogadorInsuficiente(String errorMessage, Player jogador) {
		super(errorMessage);
		this.jogador = jogador;
	}
	
	public Player getJogador() {
		return jogador;
	}
	
}
