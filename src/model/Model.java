package model;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Model {

	private int numeroDeJogadores;
	private int[] dadosDaVez = {1,1};
	
	private static Model model = null;
	
	private Color[] colorList = {Color.red,Color.blue,Color.orange,Color.yellow,Color.pink,Color.gray};
	
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
	
	private void rolarDadosDaVez() {
		int dado1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		int dado2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		dadosDaVez = new int []{dado1,dado2};		
	}
	
	public int getNumeroDeJogadores() {
		return numeroDeJogadores;
	}
	
	public Color getCorJogadorDaVez() {
		return colorList[Player.getIdJogadorDaVez()];
	}
	
	public int[] getDadosDaVez() {
		return dadosDaVez;
	}
	
	public int getSomaDadosDaVez() {
		return dadosDaVez[0] + dadosDaVez[1];
	}
}
