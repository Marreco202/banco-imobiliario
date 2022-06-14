package model;
import java.util.concurrent.ThreadLocalRandom;

import exeptions.JogadorInexistente;
import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;

class Player {

	private static int qtdDeJogadores = 0; 
	private static Player[] playerList = new Player[6];
	private static int idJogadorDaVez = 0;
	private static Cor[] colorList = {Cor.vermelho,Cor.azul,Cor.laranja,Cor.amarelo,Cor.rosa,Cor.cinza};
	
	public static int getQtdDeJogadores() {
		return qtdDeJogadores;
	}
	
	public static int getIdJogadorDaVez() {
		return idJogadorDaVez;
	}
	
	public static Player getJogadorDaVez() {
		if(idJogadorDaVez == -1) {
			return playerList[idJogadorDaVez+1];
		}
		return playerList[idJogadorDaVez];
	}
	
	public static Player proximoJogador() {
		getJogadorDaVez().setDadosDaVez(0, 0);
		if(Player.getJogadorDaVez().dadosIguaisSeguidos > 0){
			return playerList[Player.idJogadorDaVez];
		}
		else if(Player.idJogadorDaVez < (Player.qtdDeJogadores-1)) {
			return playerList[++Player.idJogadorDaVez];
		}else {
			Player.idJogadorDaVez = 0;
			return playerList[Player.idJogadorDaVez];
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
	
	public static Player[] getPlayerList() {
		return playerList;
	}
	

	private Cor cor;
	private int pos; 
	private int dadosIguaisSeguidos; 
	private int[] dadosDaVez = {0, 0};
	private int saldo;
	private int idJogador;
	private boolean acabouDeComprar;
	
	private boolean passeLivre;
	private boolean estaPreso;
	
	
	public Player(){
		
		playerList[qtdDeJogadores] = this;
		this.cor = colorList[qtdDeJogadores];
		this.idJogador = qtdDeJogadores;
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
	
	public int getIdJogador() {
		return idJogador;
	}
	
	public void sortearDados() {
		int dado1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		int dado2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		dadosDaVez[0]=dado1; dadosDaVez[1]=dado2;
	}
	
	public void rolarDados() {
		sortearDados();
		System.out.println("Jogador está rolando os dados");
		if(dadosDaVez[0] == dadosDaVez[1]) {
			dadosIguaisSeguidos++;
			if(dadosIguaisSeguidos == 2) {
				this.vaParaAPrisao();
				dadosDaVez[0]=0; dadosDaVez[1]=0;
			}
		}else {
			dadosIguaisSeguidos = 0;
		}
	}
	
	public void setDadosDaVez(int i, int j) {
		dadosDaVez[0] = i;
		dadosDaVez[1] = j;
	}

	public int pagarValor(int valorASerPago) throws SaldoJogadorInsuficiente {
		if(saldo < valorASerPago) {
			throw new SaldoJogadorInsuficiente(null, this.cor);
		}
		saldo -= valorASerPago;
		return valorASerPago;
	}
	
	public void receberValor(int valorASerRecebido) {
		saldo += valorASerRecebido;
	}
	
	public void avancarNoTabuleiro(){
		if(estaPreso) {
			tentarSairDaPrisao();
			return;
		}
		
		boolean passouPeloPontoDePartida = false;
		int somaDados = dadosDaVez[0] + dadosDaVez[1];
		if(pos + somaDados < Board.getBoard().getTamanhoTabuleiro()) {
			pos += somaDados;
		}else {
			passouPeloPontoDePartida = true;
			pos = pos + somaDados - Board.getBoard().getTamanhoTabuleiro();
		}
		
		verificaNovaPos(passouPeloPontoDePartida);
	}
	
	private void verificaNovaPos(boolean passouPeloPontoDePartida) {
		if(passouPeloPontoDePartida) {
			try {
				Bank.getBank().saque(this, 200);
			}catch (Exception e) {
				System.out.println("Banco com saldo insuficiente");
			}
		}
		
		if(pos == Board.getBoard().getPosVaParaPrisao()) {
			vaParaAPrisao();
		}else {
			try {
				Player proprietario = Board.getBoard().getDonoDePosicao(pos);
				Compravel com = (Compravel) Board.getBoard().tabuleiro[pos];
				if(proprietario != this) {
					Bank.getBank().realizarPagamentoDeAluguel(this, com);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getSaldo() {
		return saldo;
	}
	
	public Cor getCor() {
		return this.cor;
	}
	
	public boolean getPasseLivre() {
		return passeLivre;
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
	
	private int tentativasDeSair = 0;
	public void tentarSairDaPrisao() {
		if(dadosDaVez[0] == dadosDaVez[1] || tentativasDeSair == 2 ) {
			estaPreso = false;
			tentativasDeSair = 0;
		}else {
			tentativasDeSair++;
		}
	}
	
	public void vaParaAPrisao() {
		pos = Board.getBoard().getPosPrisao();
		estaPreso = true;
	}
	
	public void setAcabouDeComprar(boolean acabouDeComprar) {
		this.acabouDeComprar = acabouDeComprar;
	}
	
	public boolean getAcabouDeComprar() {
		return this.acabouDeComprar;
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

	public int[] getDadosDaVez() {
		return dadosDaVez;
	}

	public int getSomaDadosDaVez() {
		return dadosDaVez[0] + dadosDaVez[1];
	}
	
}
