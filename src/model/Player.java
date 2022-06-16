package model;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import exeptions.JogadorInexistente;
import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;

class Player {

	private static int qtdDeJogadores = 0; 
	private static Player[] playerList = new Player[6];
	private static int idJogadorDaVez = 0;
	private static Color[] colorList = {Color.red,Color.blue,Color.orange,Color.yellow,Color.pink,Color.gray};
	
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
		getJogadorDaVez().avancouNoTabuleiro = false;
		getJogadorDaVez().jaConstruiu = false;
		getJogadorDaVez().tirouCarta = false;
		
		do {
			calculaEDefineProximoJogador();
		}while(getJogadorDaVez().estaFalido);
		
		return getJogadorDaVez();
	}
	
	private static Player calculaEDefineProximoJogador() {
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
	
	public static Player[] getPlayerList() {
		return playerList;
	}
	
	public static int[] ordenaListaJogadoresPorFortuna() {
		int size = qtdDeJogadores -1;
		for(int i=0; i<size; i++) {
		    for(int j=0;j<size-i;j++) {
		    	System.out.println("qtd: "+qtdDeJogadores+"i: " + i + "j: " + j);
				if(playerList[j].getFortuna() > playerList[j + 1].getFortuna()) {
				    Player temp = playerList[j];
		    		playerList[j] = playerList[j+1];
    				playerList[j+1] = temp;
				}
		    }
		}
		
		int[] retorno = new int[qtdDeJogadores];
		for(int i=0; i<qtdDeJogadores; i++) {
			retorno[i] = playerList[i].idJogador;
		}
		
		return retorno;
	}
	

	public static int[] getFortunaOrdenadaDosJogadores() {
		int[] retorno = new int[qtdDeJogadores];
		for(int i=0; i<qtdDeJogadores;i++) {
			retorno[i] = playerList[i].getFortuna();
		}
		return retorno;
	}
	
	public static Color[] getJogadoresOrdenadosPorFortuna() {
		Color[] retorno = new Color[qtdDeJogadores];
		for(int i=0; i<qtdDeJogadores;i++) {
			retorno[i] = playerList[i].cor;
		}
		return retorno;
	}
	
	public static boolean acabouOJogo() {
		int jogadoresAtivos = 0;
		for(int i=0;i<qtdDeJogadores;i++) {
			if(!playerList[i].getEstaFalido()) {
				jogadoresAtivos++;
			}
		}
		if(jogadoresAtivos==1) {
			return true;
		}
		return false;
	}
	

	private Color cor;
	private int pos; 
	private int dadosIguaisSeguidos; 
	private int[] dadosDaVez = {0, 0};
	private boolean avancouNoTabuleiro = false, jaConstruiu = false;
	private int saldo;
	private int idJogador;
	private boolean tirouCarta = false;
	
	private boolean estaFalido = false;
	
	private boolean estaDevendoCarta = false, estaDevendoAluguel = false, estaDevendoImpostoDeRenda = false;
	
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
	
	public void rolarDados() {
		int dado1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		int dado2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		dadosDaVez[0]=dado1; dadosDaVez[1]=dado2;
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
		if(estaDevendoAluguel) {
			try {
				verificaEPagaAluguel((Compravel) Board.getBoard().tabuleiro[pos]);
			}catch (Exception e) {
				System.out.println("Erro: Cast para compravel em receber valor falhou!");
			}
		}else if(estaDevendoCarta) {
			verificaEUsaCarta();
		}else if(estaDevendoImpostoDeRenda) {
			pagarImpostoDeRenda();
		}
	}
	
	public void verificaSeJogaDeNovo() {
		if(dadosDaVez[0] == dadosDaVez[1] && dadosDaVez[0]!=0 && dadosDaVez[1]!=0) {
			dadosIguaisSeguidos++;
			if(dadosIguaisSeguidos == 3) {
				this.vaParaAPrisao();
				dadosDaVez[0]=0; dadosDaVez[1]=0;
				dadosIguaisSeguidos = 0;
			}
		}else {
			dadosIguaisSeguidos = 0;
		}		
	}
	
	public void avancarNoTabuleiro() throws SaldoJogadorInsuficiente{
		if(estaPreso) {
			tentarSairDaPrisao();
			return;
		}
		
		
		avancouNoTabuleiro = true;
		boolean passouPeloPontoDePartida = false;
		int somaDados = dadosDaVez[0] + dadosDaVez[1];
		if(pos + somaDados < Board.getBoard().getTamanhoTabuleiro()) {
			pos += somaDados;
		}else {
			passouPeloPontoDePartida = true;
			pos = pos + somaDados - Board.getBoard().getTamanhoTabuleiro();
		}
		
		verificaSeJogaDeNovo();
		verificaNovaPos(passouPeloPontoDePartida);
	}
	
	private void passouPeloPontoDePartida() {
		try {
			int saldoAnterior = saldo;
			Bank.getBank().saque(this, 200);
			Model.getModel().addMensagemAoPlayer("Recebeu 200 ao passar pelo ponto de partida. Saldo anterior: "+saldoAnterior);
		}catch (SaldoBancoInsuficiente e) {
			Model.getModel().addMensagemAoPlayer("Saldo do Banco insuficiente para pagamento do ponto de partida");
		}catch (Exception e) {
		}
	}
	
	private void verificaEPagaAluguel(Compravel casa){
		try {
			Player proprietario = Board.getBoard().getDonoDePosicao(pos);
			if(proprietario != this) {
				int saldoAnterior = saldo;
				Bank.getBank().realizarPagamentoDeAluguel(this, casa);
				estaDevendoAluguel = false;
				int valorAluguel = Bank.getBank().descobreAluguelASerPago(casa);
				Model.getModel().addMensagemAoPlayer("Aluguel pago! Valor do aluguel: "+valorAluguel+". Saldo anterior: "+saldoAnterior);
			}
		}catch (SaldoJogadorInsuficiente e) {
			estaDevendoAluguel = true;
			int valorAluguel = Bank.getBank().descobreAluguelASerPago(casa);
			checkFalencia(valorAluguel);
			if(estaFalido) {
				Model.getModel().addMensagemAoPlayer("Voce está falido e não pode pagar o aluguel! Finalize a rodada.");
				return;
			}
			Model.getModel().addMensagemAoPlayer("Saldo insuficiente! Venda propriedades para pagar o aluguel!");
		}catch (Exception e) {
			System.out.println("ERRO: na funcao verificaEPagaAluguel, posições conflitantes ou jogador ja é dono da propriedade");
		}
	}
	
	private void verificaEUsaCarta() {
		tirouCarta = true;
		try {
			int saldoAnterior = saldo;
			if(!estaDevendoCarta) {
				DequeDeCartas.getDequeDeCartas().pegarCarta();
			}
			DequeDeCartas.getDequeDeCartas().usarCarta(this);
			estaDevendoCarta = false;
			Model.getModel().addMensagemAoPlayer("Carta retirada com sucesso! Saldo anterior: "+saldoAnterior);
		}catch (SaldoJogadorInsuficiente e) {
			estaDevendoCarta = true;
			if(estaFalido) {
				Model.getModel().addMensagemAoPlayer("Voce está falido e não pode pagar a carta! Finalize a rodada.");
				return;
			}
			Model.getModel().addMensagemAoPlayer("Saldo insuficiente! Venda propriedades para pagar a carta!");
		}catch (Exception e) {
			Model.getModel().addMensagemAoPlayer("Saldo do banco insuficiente para quitar a carta");
		}		
	}
	
	private void recebeLucrosEDividendos() {
		try {
			int saldoAnterior = saldo;
			Bank.getBank().saque(this, 200);
			Model.getModel().addMensagemAoPlayer("Voce recebeu 200 de lucros e dividendos. Saldo anterior: "+saldoAnterior);
		}catch(SaldoBancoInsuficiente e) {
			Model.getModel().addMensagemAoPlayer("Saldo do banco insuficiente para pagar lucros e dividendos.");
		}
	}
	
	private void pagarImpostoDeRenda() {
		try {
			int saldoAnterior = saldo;
			Bank.getBank().deposito(this, 200);
			estaDevendoImpostoDeRenda = false;
			Model.getModel().addMensagemAoPlayer("Voce pagou 200 de imposto de renda. Saldo anterior: "+saldoAnterior);
		}catch(SaldoJogadorInsuficiente e) {
			checkFalencia(200);
			if(estaFalido) {
				Model.getModel().addMensagemAoPlayer("Voce está falido e não pode pagar o imposto! Finalize a rodada.");
				return;
			}
			estaDevendoImpostoDeRenda = true;
		}
	}
	
	private void verificaNovaPos(boolean passouPeloPontoDePartida) throws SaldoJogadorInsuficiente{
		if(passouPeloPontoDePartida) {
			passouPeloPontoDePartida();
		}
		
		Tile casa = Board.getBoard().tabuleiro[pos];
		
		if(pos == Board.getBoard().getPosVaParaPrisao()) {
			vaParaAPrisao();
		}else if(casa instanceof Compravel){
			verificaEPagaAluguel((Compravel) casa);
		}else if(casa instanceof SpecialTile && ((SpecialTile) casa).getTipoTileEspecial() == TilesEspeciais.sorteOuReves) {
			verificaEUsaCarta();
		}else if(casa instanceof SpecialTile && ((SpecialTile) casa).getTipoTileEspecial() == TilesEspeciais.lucrosEDividendos) {
			recebeLucrosEDividendos();
		}else if(casa instanceof SpecialTile && ((SpecialTile) casa).getTipoTileEspecial() == TilesEspeciais.impostoDeRenda) {
			pagarImpostoDeRenda();
		}
	}
	
	public boolean getTirouCarta() {
		return tirouCarta;
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getSaldo() {
		return saldo;
	}
	
	public Color getCor() {
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
	
	private static void goFalencia(Player p) {
		
	}
	
	private int tentativasDeSair = 0;
	public void tentarSairDaPrisao() {
		if(dadosDaVez[0] == dadosDaVez[1]) {
			estaPreso = false;
			tentativasDeSair = 0;
		}else if(tentativasDeSair == 3) {
			estaPreso = false;
			try {
				Bank.getBank().deposito(this, 50);
				tentativasDeSair = 0;
			}catch (SaldoJogadorInsuficiente e) {
				Model.getModel().addMensagemAoPlayer("Saldo do jogador insuficiente para sair da prisao");
			}
		}else {
			tentativasDeSair++;
		}
	}
	
	public void vaParaAPrisao() {
		pos = Board.getBoard().getPosPrisao();
		if(!passeLivre) {			
			estaPreso = true;
		}else {
			estaPreso = false;
			passeLivre = false;
			DequeDeCartas.getDequeDeCartas().retornarPasseLivre();
		}
	}
	
	public void setJaConstruiu(boolean jaConstruiu) {
		this.jaConstruiu = jaConstruiu;
	}
	
	public boolean podeConstruir() {
		Territorio casa;
		if(Board.getBoard().tabuleiro[pos] instanceof Territorio) {
			casa = (Territorio)Board.getBoard().tabuleiro[pos];
		}else {
			return false;
		}
		
		if(!jaConstruiu && avancouNoTabuleiro && casa.getProprietario() == this && (casa.podeConstruirCasa() || casa.podeConstruirHotel())) {
			return true;
		}
		return false;
	}
	
	private int getFortuna() {
		return saldo + Board.getBoard().fortunaEmPropriedadesDePlayer(this);
	}
	
	public void checkFalencia(int valorCobrado) {
		
		int fortuna = getFortuna();
		
		if(fortuna < valorCobrado) {
			estaFalido = true;
		}
		
	}
	
	public boolean getEstaFalido() {
		return estaFalido;
	}

	public int[] getDadosDaVez() {
		return dadosDaVez;
	}

	public int getSomaDadosDaVez() {
		return dadosDaVez[0] + dadosDaVez[1];
	}

	public boolean podeComprar() {
		return avancouNoTabuleiro;
	}

	public boolean getEstaDevendoCarta() {
		return estaDevendoCarta;
	}

	public boolean getEstaDevendoAluguel() {
		return estaDevendoAluguel;
	}
	
	public boolean getEstaDevendoImpostoDeRenda() {
		return estaDevendoImpostoDeRenda;
	}
	
	public boolean getPlayerEstaDevendo() {
		return getEstaDevendoCarta() && getEstaDevendoAluguel() && getEstaDevendoImpostoDeRenda();
	}



}
