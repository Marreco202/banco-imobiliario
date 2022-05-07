package model;
import java.util.random.*;

public class Player {

	private static int qtd = 0; //quantidade de jogadores in-game
	private int nota1, nota5, nota10, nota50, nota100, nota500; 
<<<<<<< HEAD
	Territorio[] propriedades; //array das propriedades de um jogador
	//private int hoteis, casas; //quantidade de casas e hoteis que o jogador tem
=======
	public Prop[] propriedades; //array das propriedades de um jogador
>>>>>>> 67c7cb83bfce8ea20053bb29cea49b3deacf4f9d
	private Cor playerColor;
	private int pos; //posicao do jogador no tabuleiro
	private int seguidos; //quantidade de vezes que o jogador tirou dois pares de dados seguidos
	private int saldo;
	
	private boolean passeLivre;
	private boolean estaPreso;
	
	public static Player[] playerList = new Player[4];
	
	public Player(){
		
		playerList[qtd] = this; //lista de jogadores  jogando
		qtd++; //aumenta a quantidade de players in-game quando um é instanciado
		
		//nota1 = 8;
		//nota5 = 10;
		//nota10 = 10;
		//nota50 = 10;
		//nota100 = 8;
		//nota500 = 2;
		saldo = 2458;
		
		pos = 10; //posicao no array onde fica o ponto de partida
		passeLivre = false;
		estaPreso = false;
		seguidos = 0; 
		
		//setar a cor do jogador caso qtd de jogadores < 4 usando o tipo enumerado Cor
		//caso qtd de jogadores > 4, nao adicionar o jogador na lista de jogadores
	}
	
	public int getSaldo() {
		return saldo;
	}
	
	public void setPasseLivre() {
		passeLivre = true;
	}
	
	public void goPrison() {
		pos = (Board.tabuleiro[0]).getPos() ; //seta o jogador para ficar na posicao do array onde está o Tile da prisao
		estaPreso = true;
	}
	
	
	
	
	public static void checkFalencia(Player p, int valorCobrado) {
		
		if(saldo < valorCobrado) {
			
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
	
<<<<<<< HEAD
	public void hipoteca(Territorio p) {
		
		/*
		 * Terrenos sem construção e empresas podem ser hipotecadas pelos valores determinados nos títulos por qualquer período de
		 * tempo
		 * 
		 * Caso haja casas ou hotel é necessário antes vendê-las ao Banco pela metade do preço
		 * */
		
		
	}
	
=======
>>>>>>> 67c7cb83bfce8ea20053bb29cea49b3deacf4f9d
	
	private Territorio findProp(Territorio p) {
		
		for(int i = 0; i< propriedades.length; i++ ){
			if(propriedades[i] == p) {
				return propriedades[i];
			}
		}
		return null;
	}
	
	
	
}
