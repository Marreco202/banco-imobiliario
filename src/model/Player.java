package model;
import java.util.random.*;

import exeptions.SaldoJogadorInsuficiente;

public class Player {

	private static int qtdDeJogadores = 0; 
	private static Player[] playerList = new Player[6];
	private static Cor[] colorList = {Cor.vermelho,Cor.azul,Cor.laranja,Cor.amarelo,Cor.rosa,Cor.cinza};

	private Cor playerColor;
	private int pos; 
	private int dadosIguaisSeguidos; 
	private int saldo;
	
	private boolean passeLivre;
	private boolean estaPreso;
	
	
	public Player(){
		
		playerList[qtdDeJogadores] = this;
		this.playerColor = colorList[qtdDeJogadores];
		qtdDeJogadores++;
		
		saldo = 2458; //nota1 = 8;nota5 = 10;nota10 = 10;nota50 = 10;nota100 = 8;nota500 = 2;
		pos = 10; //posicao no array onde fica o ponto de partida
		passeLivre = false;
		estaPreso = false;
		dadosIguaisSeguidos = 0; 
	}
	
	public int pagarValor(int valorASerPago) throws SaldoJogadorInsuficiente {
		if(saldo < valorASerPago) {
			throw new SaldoJogadorInsuficiente(null);
		}
		saldo -= valorASerPago;
		return valorASerPago;
	}
	
	public void receberValor(int valorASerRecebido) {
		saldo += valorASerRecebido;
	}
	
	public void avancarNoTabuleiro(int numeroDeCasas) {
		if(pos + numeroDeCasas < Board.getTamanhoTabuleiro()) {
			pos += numeroDeCasas;
		}else {
			pos = pos + numeroDeCasas - Board.getTamanhoTabuleiro();
		}
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getSaldo() {
		return saldo;
	}
	public static Player[] getLista() {
		return playerList;
	}
	
	public void ganhouPasseLivre() {
		passeLivre = true;
	}
	
	public void usarPasseLivre() {
		passeLivre = false;
	}
	
	private static void goFalencia(Player p) {
		
	}
	
	public void goPrison() {
		
	}
	
	
	public static void checkFalencia(Player p, int valorCobrado) {
		
		if(p.saldo < valorCobrado) {
			goFalencia(p);
		}
		
		
	/*
	 * Falencia: "Se mesmo após vender suas casas e hotéis, ou suas propriedades o jogador não conseguir
	 * pagar suas dívidas ele irá a falência, e irá sair do jogo"
	 * 
	 * O dinheiro obtido será entregue ao jogador credor
	 * 
	 * Caso haja propriedades hipotecadas o Banco deverá resgatá-las e o dinheiro conseguido irá para oc redor
	 * 
	 * As propriedades deverão ser colocadas em leilão
	 * 
	 */
	}
	
}
