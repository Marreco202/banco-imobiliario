package model;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import exeptions.PosicoesConflitantes;
import exeptions.PropriedadeJaPossuiDono;
import exeptions.PropriedadeNaoPossuiDono;
import exeptions.SaldoJogadorInsuficiente;
import exeptions.casaAtualNaoECompravel;

public class Model {

	private int numeroDeJogadores;
	private int[] dadosDaVez = {1,1};
	
	private static Model model = null;
	
	private Color[] colorList = {Color.red,Color.blue,Color.ORANGE,Color.yellow,Color.pink,Color.gray};
	
	private Model(){
	}
	
	public static Model getModel() {
		if(model == null) {
			model = new Model();
		}
		return model;
	}
	
	public void iniciarJogo(int numeroDeJogadores) {
		this.numeroDeJogadores = numeroDeJogadores;
		instanciaJogadores();		
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
	
	public void rolarDados() {
		Player p = Player.proximoJogador();
		p.rolarDados();
		p.avancarNoTabuleiro();
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
	
	public int getSomaDadosDaVez() {
		return dadosDaVez[0] + dadosDaVez[1];
	}

	public String getNomeDaCasa() {
		int posDoJogador = Player.getJogadorDaVez().getPos();
		String nomeDaCasa = Board.getBoard().tabuleiro[posDoJogador].getNome();
		if(nomeDaCasa != null) {
			return nomeDaCasa;
		}
		return "";
	}
	
	public String getImagePathDaCasaAtual() {
		int posDoJogador = Player.getJogadorDaVez().getPos();
		String imagePath = Board.getBoard().tabuleiro[posDoJogador].getImagePath();
		System.out.println(imagePath);
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
		Bank.getBank().realizarCompraDePropriedade(p, propriedade);
	}
}
