package model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;

public class DequeDeCartas {
	
	private int[] deque = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
	private int posNoDeque = 0;
	private int cartaDaVez;
	
	public DequeDeCartas(){
		embaralharDeque(deque);
	}
	
	private void embaralharDeque(int[] deque){
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = deque.length - 1; i > 0; i--){
	      int index = rnd.nextInt(i + 1);
	      
	      int a = deque[index];
	      deque[index] = deque[i];
	      deque[i] = a;
	    }
	}
	
	public int pegarCarta() {
		cartaDaVez = deque[posNoDeque]; 
		posNoDeque++;
		
		return cartaDaVez; 
	}
	
	public int getCartaDaVez() {
		return cartaDaVez;
	}
	
	
	private void recebeDinheiro(Player p, int valor,Bank b) throws SaldoBancoInsuficiente {
		b.saque(p, valor);
	}
	
	private void daDinheiro(Player p, int valor,Bank b) throws SaldoJogadorInsuficiente {
		b.deposito(p, valor);
	}
		
	private void recebeDosJogadores(Player p, int valor,Bank b) throws SaldoJogadorInsuficiente {
		b.receberDeTodosOsJogadorer(p, valor);
	}
	
	public void proc(Player p, Bank b) throws SaldoJogadorInsuficiente, SaldoBancoInsuficiente { //efeitos das cartas
		
		switch(cartaDaVez) {
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
			p.vaParaAPrisao();
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
