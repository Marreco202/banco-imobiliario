package model;

import exeptions.SaldoJogadorInsuficiente;

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
	private void recebeDinheiro(Player p, int valor,Bank b) {
		int deposito = b.saque(valor);
		p.receberValor(deposito);
	}
	
	
	private void daDinheiro(Player p, int valor,Bank b) throws SaldoJogadorInsuficiente {
		int deposito = p.pagarValor(valor);
		b.deposito(deposito);
	}
		
	private void recebeDosJogadores(Player p, int valor,Bank b) throws SaldoJogadorInsuficiente {
		int contador = 0;
		Player[] playerList = Player.getLista();
		for(int i = 0; i<4;i++) { //coleta do dinheiro
			if(playerList[i] != null){ //se este jogador estiver in game (jogadores que entram em falencia seu [i] na playerlist fica igual a null
				daDinheiro(playerList[i],valor,b);
				contador++;
			}
		}
		for(int i = 0; i< contador; i++) {
			recebeDinheiro(p,valor,b);
		}
	}
	
	
	//
	public void proc(Player p, Bank b) { //efeitos das cartas
		
		switch(id) {
		case 1:
			recebeDinheiro(p,25,b);
		
		case 2:
			recebeDinheiro(p,150,b);
		
		case 3:
			recebeDinheiro(p,80,b);
			
		case 4:
			recebeDinheiro(p,400,b);
		case 5:
			recebeDinheiro(p,50,b);
		case 6:
			recebeDinheiro(p,60,b);
		case 7:
			recebeDinheiro(p,100,b);
		case 8:
			recebeDinheiro(p,100,b);
		case 9:
			p.ganhouPasseLivre();
		case 10:
			recebeDinheiro(p,200,b);
		case 11:
			recebeDosJogadores(p,50,b);
		case 12:
			recebeDinheiro(p,45,b);
		case 13:
			recebeDinheiro(p,100,b);
		case 14:
			recebeDinheiro(p,100,b);
		case 15:
			recebeDinheiro(p,20,b);
		case 16:
			daDinheiro(p,15,b);
		case 17:
			daDinheiro(p,25,b);
		case 18:
			daDinheiro(p,45,b);
		case 19:
			daDinheiro(p,30,b);
		case 20:
			daDinheiro(p,100,b);
		case 21:
			daDinheiro(p,100,b);
		case 22:
			daDinheiro(p,40,b);
		case 23:
			p.goPrison();
		case 24:
			daDinheiro(p,30,b);
		case 25:
			daDinheiro(p,50,b);
		case 26:
			daDinheiro(p,25,b);
		case 27:
			daDinheiro(p,30,b);
		case 28:
			daDinheiro(p,45,b);
		case 29:
			daDinheiro(p,50,b);
		case 30:
			daDinheiro(p,50,b);
	}
	}
}
