package model;
import java.util.random.*;

public class Player {

	private static int qtd = 0; //quantidade de jogadores in-game
	private int nota1, nota5, nota10, nota50, nota100, nota500; 
	Prop[] propriedades; //array das propriedades de um jogador
	//private int hoteis, casas; //quantidade de casas e hoteis que o jogador tem
	private Cor playerColor;
	private int pos; //posicao do jogador no tabuleiro
	private int seguidos;
	
	private boolean passeLivre;
	private boolean estaPreso;
	
	public static Player[] playerList = new Player[4];
	
	public Player(){
		
		playerList[qtd] = this; //lista de jogadores  jogando
		qtd++; //aumenta a quantidade de players in-game quando um é instanciado
		
		nota1 = 8;
		nota5 = 10;
		nota10 = 10;
		nota50 = 10;
		nota100 = 8;
		nota500 = 2;
		
		pos = 10; //posicao no array onde fica o ponto de partida
		passeLivre = false;
		estaPreso = false;
		seguidos = 0; //quantidade de vezes que o jogador tirou dois pares de dados seguidos
		
		//setar a cor do jogador caso qtd de jogadores < 4 usando o tipo enumerado Cor
		//caso qtd de jogadores > 4, nao adicionar o jogador na lista de jogadores
	}
	
	public int getSaldo() {
		return (nota1) + (5 * nota5) + (10 * nota10) + (50 * nota50) + (100 * nota100) + (500* nota500);
	}
	
	public int[] getNotas() {
		int notas[] = {nota1,nota5,nota10,nota50,nota100,nota500};
		return notas;
	}
	
	public void setPasseLivre() {
		passeLivre = true;
	}
	
	public void goPrison() {
		pos = (Board.tabuleiro[0]).getPos() ; //seta o jogador para ficar na posicao do array onde está o Tile da prisao
		estaPreso = true;
	}
	
	
	public static void checkFalencia(Player p) {
		
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
	
	public void hipoteca(Prop p) {
		
		/*
		 * Terrenos sem construção e empresas podem ser hipotecadas pelos valores determinados nos títulos por qualquer período de
		 * tempo
		 * 
		 * Caso haja casas ou hotel é necessário antes vendê-las ao Banco pela metade do preço
		 * */
		
		
	}
	
	
	private Prop findProp(Prop p) {
		
		for(int i = 0; i< propriedades.length; i++ ){
			if(propriedades[i] == p) {
				return propriedades[i];
			}
		}
		return null;
	}
	
	
	
}
