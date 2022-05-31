package model;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

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
}
