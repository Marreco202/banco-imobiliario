package model;
import java.util.concurrent.ThreadLocalRandom;

public class Model {

	private int numeroDeJogadores;
	private static int[] dadosDaVez;
	
	
	public Model(int numeroDeJogadores){	
		this.numeroDeJogadores = numeroDeJogadores;
		instanciaJogadores();		
	}
	
	private void instanciaJogadores() {
		for(int i = 0; i< numeroDeJogadores; i++) {
			new Player();
		}
	}
	
	private void rolarDadosDaVez() {
		int dado1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		int dado2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		dadosDaVez = new int []{dado1,dado2};		
	}
	
	public static int[] getDadosDaVez() {
		return dadosDaVez;
	}
	
	public static int getSomaDadosDaVez() {
		return dadosDaVez[0] + dadosDaVez[1];
	}
}
