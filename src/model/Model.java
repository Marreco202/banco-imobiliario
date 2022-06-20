package model;
import java.awt.Color;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exeptions.PosicoesConflitantes;
import exeptions.ProibidoConstruir;
import exeptions.PropriedadeJaPossuiDono;
import exeptions.PropriedadeNaoPossuiDono;
import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;
import exeptions.casaAtualNaoECompravel;

public class Model {
	
	private boolean devMode = false;
	private static Model model = null;
	private String mensagemAoPlayer = "";
	private Color[] colorList = {Color.red,Color.blue,Color.ORANGE,Color.yellow,Color.pink,Color.gray};
	
	private Model(){
	}
	
	public static Model getModel() {
		if(model == null) {
			model = new Model();
		}
		return model;
	}
	
	public void iniciarJogo(int numeroDeJogadores, boolean devMode) {
		this.devMode = devMode;
		instanciaJogadores(numeroDeJogadores);		
	}
	
	private void instanciaJogadores(int numeroDeJogadores) {
		for(int i = 0; i< numeroDeJogadores; i++) {
			new Player();
		}
	}
	
	public boolean podeFinalizarRodada() {
		Player p = Player.getJogadorDaVez();
		if(!p.getPlayerEstaDevendo() || p.getEstaFalido()) {
			return true;
		}
		return false;
	}

	public void finalizarRodada() {
		if(podeFinalizarRodada()) {
			Player.proximoJogador();
		}
	}
	
	public void rolarDados(){
		Player p = Player.getJogadorDaVez();
		if(this.devMode == false) {
			p.rolarDados();
		}
		try {
			p.avancarNoTabuleiro();
		}catch(Exception e) {}
	}
	
	public void setDadosDaVez(int i, int j) {
		Player p = Player.getJogadorDaVez();
		p.setDadosDaVez(i, j);
	}
	
	public int getNumeroDeJogadores() {
		return Player.getQtdDeJogadores();
	}
	
	public Color getCorJogadorDaVez() {
		if(Player.getIdJogadorDaVez() == -1) {
			return colorList[Player.getIdJogadorDaVez()+1];
		}
		return colorList[Player.getIdJogadorDaVez()];
	}
	
	public int posDoJogador(int jogador) {
		Player[] playerList = Player.getPlayerList();
		return playerList[jogador].getPos();
	}
	
	public int[] getDadosDaVez() {
		return Player.getJogadorDaVez().getDadosDaVez();
	}

	public String getNomeDaCasa(int pos) {
		String nomeDaCasa = Board.getBoard().tabuleiro[pos].getNome();
		if(nomeDaCasa != null) {
			return nomeDaCasa;
		}
		return "";
	}
	
	public String getImagePath(int pos) {
		String imagePath = Board.getBoard().tabuleiro[pos].getImagePath();
		return imagePath;
	}
	
	public Tile getCasa(int pos) {
		return (Tile) Board.getBoard().tabuleiro[pos];
	}
	
	public int getValorDeCompra(int pos) throws casaAtualNaoECompravel {
		if(getCasa(pos) instanceof Compravel) {
			return ((Compravel) getCasa(pos)).getValor();
		}
		throw new casaAtualNaoECompravel();
	}

	public int getPosJogadorDaVez() {
		return Player.getJogadorDaVez().getPos();
	}
	
	public int getValorDeVenda(int pos) throws casaAtualNaoECompravel {
		if(getCasa(pos) instanceof Compravel) {
			return ((Compravel) getCasa(pos)).getValorDeVenda();
		}
		throw new casaAtualNaoECompravel();
	}

	public Color getCorProprietario(int pos) throws PropriedadeNaoPossuiDono, casaAtualNaoECompravel {
		Player proprietario = Board.getBoard().getDonoDePosicao(pos);
		if(getCasa(pos) instanceof Compravel) {
			if(proprietario == null) {
				throw new PropriedadeNaoPossuiDono();
			}
			return colorList[proprietario.getIdJogador()];
			
		}
		throw new casaAtualNaoECompravel();
	}

	public int getSaldoJogadorDaVez() {
		return Player.getJogadorDaVez().getSaldo();
	}

	public void comprarPropriedade() throws casaAtualNaoECompravel, SaldoJogadorInsuficiente, PropriedadeJaPossuiDono, PosicoesConflitantes {
		Player p = Player.getJogadorDaVez();
		int pos = getPosJogadorDaVez();
		Compravel propriedade;
		if(getCasa(pos) instanceof Compravel) {
			propriedade = (Compravel) getCasa(pos);	
		}else {
			throw new casaAtualNaoECompravel();
		}
		int saldoAnterior = p.getSaldo();
		Bank.getBank().realizarCompraDePropriedade(p, propriedade);
		addMensagemAoPlayer("Compra de propriedade realizada com sucesso!Saldo anterior: "+saldoAnterior);
	}

	public ArrayList<Integer> getTodasPropriedadesJogadorAtual() {
		return Board.getBoard().getTodasPropriedadesDeJogador(Player.getJogadorDaVez());
	}
	
	public boolean getDevMode() {
		return devMode;
	}

	public int getNumeroDeCasasEmPropriedade(int pos) throws ProibidoConstruir {
		return Board.getBoard().getQtdDeCasas(pos);
	}

	public boolean getTemHotel(int pos) throws ProibidoConstruir {
		return Board.getBoard().getTemHotel(pos);
	}

	public boolean podeComprarPropriedade(int pos) {
		return Board.getBoard().podeComprarPropriedade(pos) && Player.getJogadorDaVez().podeComprar();
	}

	public boolean podeConstruir(int pos) {
		Player p = Player.getJogadorDaVez();
		if(p.podeConstruir()) {
			return true;
		}
		return false;
	}

	public void construir() {
		if(!podeConstruir(getPosJogadorDaVez())){
			return;
		}
		Territorio casa = ((Territorio) Board.getBoard().tabuleiro[getPosJogadorDaVez()]);
		try {
			int saldoAnterior = Player.getJogadorDaVez().getSaldo();
			Bank.getBank().construirPropriedade(Player.getJogadorDaVez(), casa);
			addMensagemAoPlayer("Edifício construido com sucesso.Saldo anterior: "+saldoAnterior);
		}catch (SaldoJogadorInsuficiente e) {
			addMensagemAoPlayer("Saldo insuficiente para construir na propriedade");
		}catch (Exception e) {
			System.out.println("ERRO: posições conflitantes ou Proibido Construir");
		}
	}

	public boolean tirouCarta() {
		return Player.getJogadorDaVez().getTirouCarta();
	}

	public String getNumeroDaCarta() {
		return Integer.toString(DequeDeCartas.getDequeDeCartas().getCartaDaVez());
	}

	public void venderPropriedade(int posQueVaiVender) {
		Player p = Player.getJogadorDaVez();
		Tile casa = Board.getBoard().tabuleiro[posQueVaiVender];
		if(casa instanceof Compravel) {
			try {
				int saldoAnterior = p.getSaldo();
				Bank.getBank().realizarVendaDePropriedade(p, (Compravel) casa);
				addMensagemAoPlayer("Propriedade vendida com sucesso! Saldo anterior: "+saldoAnterior);
			}catch (SaldoBancoInsuficiente e) {
				addMensagemAoPlayer("Saldo do banco insuficiente para comprar a propriedade");
			}catch (Exception e) {
				System.out.println("ERRO: jogador não é dono da propriedade então não pode compra-la");
			}
		}
	}
	
	public void addMensagemAoPlayer(String m) {
		mensagemAoPlayer = mensagemAoPlayer + "\n" + m;
	}
	
	public String getMensagemAoPlayer() {
		String retorno = mensagemAoPlayer;
		mensagemAoPlayer = "";
		return retorno;
	}

	public boolean acabouOJogo() {
		return Player.acabouOJogo();
	}

	public Color[] getJogadoresOrdenadosPorFortuna() {
		int[] jogadores = Player.idJogadoresOrdenadoPorFortuna();
		Color[] r = new Color[Player.getQtdDeJogadores()];
		for(int i=0; i<r.length; i++) {
			r[i] = Player.getCorJogador(jogadores[i]);
		}
		return r;
	}

	public int[] getFortunaOrdenadaDosJogadores() {
		int[] jogadores = Player.idJogadoresOrdenadoPorFortuna();
		int[] r = new int[Player.getQtdDeJogadores()];
		for(int i=0; i<r.length; i++) {
			r[i] = Player.getFortunaJogador(jogadores[i]);
		}
		return r;
	}

	private PrintWriter writer;
	public void salvarJogo(File file) {
		try {
			writer = new PrintWriter(file);
			escreverEmArquivo(file, "//Dev mode:");
			escreverEmArquivo(file, Boolean.toString(devMode));
			escreverEmArquivo(file, "//Jogador da vez:");
			escreverEmArquivo(file, Integer.toString(Player.getIdJogadorDaVez()));
			escreverEmArquivo(file, "//Quantidade de jogadores:");
			escreverEmArquivo(file, Integer.toString(Player.getQtdDeJogadores()));
			escreverEmArquivo(file, "//Jogadores:");
			escreverEmArquivo(file, "//pos|dadosIguaisSeguidos|dadosDaVez[0]|dadosDaVez[1]|avancouNoTabuleiro|jaConstruiu|saldo|idJogador|tirouCarta|estaFalido|estaDevendoCarta|estaDevendoAluguel|estaDevendoImpostoDeRenda|passeLivre|estaPreso");
			Player.salvarEmArquivo(file);
			escreverEmArquivo(file, "//Tabuleiro:");
			escreverEmArquivo(file, "//Territorio: proprietario|qtdCasas|temHotel");
			escreverEmArquivo(file, "//Companhia: proprietario");
			Board.getBoard().salvarTabuleiro(file);
			escreverEmArquivo(file, "//Cartas: passeLivreEstaNoDeque|posNoDeque|cartaDaVez");
			DequeDeCartas.getDequeDeCartas().salvarDeque(file);
			writer.close();
			addMensagemAoPlayer("Jogo Salvo com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void escreverEmArquivo(File file, String texto) {
		try {
			writer.println(texto);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	Scanner s;
	public void carregarJogo(File file) {
		try {
			s = new Scanner(file);
			devMode = Boolean.parseBoolean(lerLinha(file));
			int idJogadorDaVez = Integer.parseInt(lerLinha(file));
			int qtdJogadores = Integer.parseInt(lerLinha(file));
			System.out.println(idJogadorDaVez + "  " + qtdJogadores);
			Player.carregarDeArquivo(file, qtdJogadores, idJogadorDaVez);
			Board.getBoard().carregarTabuleiro(file);
			DequeDeCartas.getDequeDeCartas().carregarDeque(file);
			addMensagemAoPlayer("Jogo carregado com sucesso!");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String proximaLinha(File file) {
		try {
			if(s.hasNextLine()) {
				String line = s.nextLine();
				return line;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	public String lerLinha(File file) {
		String line = proximaLinha(file);
		while(line.charAt(0)=='/') {
			line = proximaLinha(file);
		}
		return line;
	}

	public boolean jaRolouDados() {
		if(Player.getJogadorDaVez().getSomaDadosDaVez() == 0) {
			return false;
		}
		return true;
	}
}














