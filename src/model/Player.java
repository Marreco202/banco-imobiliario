package model;
import exeptions.JogadorInexistente;
import exeptions.SaldoJogadorInsuficiente;

public class Player {

	private static int qtdDeJogadores = 0; 
	private static Player[] playerList = new Player[6];
	private static int jogadorDaVez = 0;
	private static Cor[] colorList = {Cor.vermelho,Cor.azul,Cor.laranja,Cor.amarelo,Cor.rosa,Cor.cinza};
	
	public static int getQtdDeJogadores() {
		return qtdDeJogadores;
	}
	
	public static int getJogadorDaVez() {
		return jogadorDaVez;
	}
	
	public static int proximoJogador() {
		if(Player.jogadorDaVez < (Player.qtdDeJogadores-1)) {
			return ++Player.jogadorDaVez;
		}else {
			Player.jogadorDaVez = 0;
			return Player.jogadorDaVez;
		}
	}
	
	public static Player getJogadorDaCor(Cor cor) throws JogadorInexistente {
		for(int i=0; i<Player.qtdDeJogadores; i++) {
			if(Player.playerList[i].cor == cor) {
				return Player.playerList[i];
			}
		}
		throw new JogadorInexistente("Este jogador não existe.");
	}
	

	private Cor cor;
	private int pos; 
	private int dadosIguaisSeguidos; 
	private int saldo;
	
	private boolean passeLivre;
	private boolean estaPreso;
	
	public Player(){
		
		playerList[qtdDeJogadores] = this;
		this.cor = colorList[qtdDeJogadores];
		qtdDeJogadores++;
		
		saldo = 2458; 
		pos = 0;
		passeLivre = false;
		estaPreso = false;
		dadosIguaisSeguidos = 0; 
	}
	
	public Player(int pos) { //construtor para fins de teste
		
		playerList[qtdDeJogadores] = this;
		this.cor = colorList[qtdDeJogadores];
		qtdDeJogadores++;
		
		saldo = 2458; 
		this.pos = pos; 
		passeLivre = false;
		estaPreso = false;
		dadosIguaisSeguidos = 0; 
	}
	
	public int pagarValor(int valorASerPago) throws SaldoJogadorInsuficiente {
		if(saldo < valorASerPago) {
			throw new SaldoJogadorInsuficiente(null, this);
		}
		saldo -= valorASerPago;
		return valorASerPago;
	}
	
	public void receberValor(int valorASerRecebido) {
		saldo += valorASerRecebido;
	}
	
	public void avancarNoTabuleiro(int numeroDeCasas, Board tabuleiro) {
		if(pos + numeroDeCasas < tabuleiro.getTamanhoTabuleiro()) {
			pos += numeroDeCasas;
		}else {
			pos = pos + numeroDeCasas - tabuleiro.getTamanhoTabuleiro();
		}
	}
	
	public void ganhouPasseLivre() {
		passeLivre = true;
	}
	
	public void usarPasseLivre() {
		estaPreso = false;
		passeLivre = false;
	}
	
	public void vaParaAPrisao(Board tabuleiro) {
		estaPreso = true;
		pos = tabuleiro.getPosDaPrisao();
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getSaldo() {
		return saldo;
	}
	
	public boolean getPasseLivre() {
		return passeLivre;
	}
	
	public static Player[] getLista() {
		return playerList;
	}
	
	
	private static void goFalencia(Player p) {
		
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
