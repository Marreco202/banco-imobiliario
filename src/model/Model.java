package model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

import exeptions.PosicoesConflitantes;
import exeptions.ProibidoConstruir;
import exeptions.PropriedadeJaPossuiDono;
import exeptions.PropriedadeNaoPossuiDono;
import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;
import exeptions.casaAtualNaoECompravel;

public class Model {

	private int numeroDeJogadores;
	
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
		this.numeroDeJogadores = numeroDeJogadores;
		this.devMode = devMode;
		instanciaJogadores();		
	}
	
	public void iniciarJogoLoad(int numeroDeJogadores, boolean devMode) {
		this.numeroDeJogadores = numeroDeJogadores;
		this.devMode = devMode; 
	}
	
	private void instanciaJogadores() {
		for(int i = 0; i< numeroDeJogadores; i++) {
			new Player();
		}
	}
	
	public int posDoJogador(int jogador) {
		Player[] playerList = Player.getPlayerList();
		return playerList[jogador].getPos();
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
		return numeroDeJogadores;
	}
	
	public Color getCorJogadorDaVez() {
		if(Player.getIdJogadorDaVez() == -1) {
			return colorList[Player.getIdJogadorDaVez()+1];
		}
		return colorList[Player.getIdJogadorDaVez()];
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
		Player proprietario;
		if(getCasa(pos) instanceof Compravel) {
			proprietario = ((Compravel) getCasa(pos)).getProprietario();
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
		return Player.getJogadoresOrdenadosPorFortuna();
	}

	public int[] getFortunaOrdenadaDosJogadores() {
		return Player.getFortunaOrdenadaDosJogadores();
	}
	public Player[] getPlayerList() {
		return Player.getPlayerList();
	}
	
}
