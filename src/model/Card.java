package model;

public class Card {
	int id; 
	/*o numero do id da carta eh relativo ao nome do .png
	 * por exemplo, chance1.png se refere a carta de id = 1
	 * implementar no view o String.format() para carregarmos todas as .pngs de uma vez só
	 */
	
	private Card ini;
	private Card fin;
	
	private Card ant;
	private Card prox;
	
	Card(int n_id){
		id = n_id;
		ini = null; //topo da stack de cartas
		fin = null; //ultima carta
		prox = null; //proxima carta
		ant = null; //carta anterior
	}
	
	// Implementacoes de filas
	private void insFin(int id) {
		Card insert = new Card(id);
		Card temp;
		
		if(ini == null && fin == null) {
			ini = insert;
			fin = insert;
			ini.prox = fin;
			fin.ant = ini;
		}
		
		temp = fin; //salva a antiga ultima carta
		fin = insert; //a nova ultima carta eh a inserida
		temp.prox = fin; //atualiza o antigo fim para apontar para o novo fim
	}
	
	private Card retIni() {
		Card temp = ini;
		
		if(ini == fin) {
			ini = null;
			fin = null;
			return temp;
		}
		temp = ini; //resgata o antigo inicio
		ini = ini.prox; //atualiza a carta do topo da pilha para aquela que estava debaixo dela
		ini.ant = null;
		
		return temp;
	}
	//
	
	//Cartas
		public Card pegaCarta() {
			
			Card ret = retIni(); //pega a carta
			insFin(ret.id); //coloca ela no fim
			
			return ret; //retorna ela para o jogador ter as informacoes dela
		}
		
		
		
	//AUX || TO DO!
	private void recebeDinheiro(Player p, int valor) {
		int[] notas = p.getNotas(); //pega as notas desse player
		//ver quais notas o banco tem para dar para o player
		//tirar as notas do banco e dar para o player
	}
	
	
	private void daDinheiro(Player p, int valor) {
		int[] notas = p.getNotas(); //pega as notas desse player
		//ve quais notas o player tem
		//tirar as notas do bolso do player e depositar no banco
	}
		
	private void recebeDosJogadores(Player p, int valor) {
		int contador = 0;
		for(int i = 0; i<4;i++) { //coleta do dinheiro
			if(Player.playerList[i] != null){ //se este jogador estiver in game (jogadores que entram em falencia seu [i] na playerlist fica igual a null
				daDinheiro(Player.playerList[i],valor);
				contador++;
			}
		}
		for(int i = 0; i< contador; i++) {
			recebeDinheiro(p,valor);
		}
	}
	
	
	//
	public void proc(Player p) { //efeitos das cartas
		
		switch(id) {
		case 1:
			recebeDinheiro(p,25);
		
		case 2:
			recebeDinheiro(p,150);
		
		case 3:
			recebeDinheiro(p,80);
			
		case 4:
			recebeDinheiro(p,400);
		case 5:
			recebeDinheiro(p,50);
		case 6:
			recebeDinheiro(p,60);
		case 7:
			recebeDinheiro(p,100);
		case 8:
			recebeDinheiro(p,100);
		case 9:
			p.setPasseLivre();
		case 10:
			recebeDinheiro(p,200);
		case 11:
			recebeDosJogadores(p,50);
		case 12:
			recebeDinheiro(p,45);
		case 13:
			recebeDinheiro(p,100);
		case 14:
			recebeDinheiro(p,100);
		case 15:
			recebeDinheiro(p,20);
		case 16:
			daDinheiro(p,15);
		case 17:
			daDinheiro(p,25);
		case 18:
			daDinheiro(p,45);
		case 19:
			daDinheiro(p,30);
		case 20:
			daDinheiro(p,100);
		case 21:
			daDinheiro(p,100);
		case 22:
			daDinheiro(p,40);
		case 23:
			p.goPrison();
		case 24:
			daDinheiro(p,30);
		case 25:
			daDinheiro(p,50);
		case 26:
			daDinheiro(p,25);
		case 27:
			daDinheiro(p,30);
		case 28:
			daDinheiro(p,45);
		case 29:
			daDinheiro(p,50);
		case 30:
			daDinheiro(p,50);
	}
	}
}
