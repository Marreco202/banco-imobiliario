package exeptions;

import java.awt.Color;

import model.Cor;

public class SaldoJogadorInsuficiente extends Exception {

	private static final long serialVersionUID = 1L;
	private Color jogador;
	
	public SaldoJogadorInsuficiente(String errorMessage, Color jogador) {
		super(errorMessage);
		this.jogador = jogador;
	}
	
	public Color getJogador() {
		return jogador;
	}
	
}
