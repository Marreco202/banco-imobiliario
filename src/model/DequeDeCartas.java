package model;

import java.io.File;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;

class DequeDeCartas {
	
	private int[] deque = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
	private int tamanhoDeque = 30;
	private boolean passeLivreEstaNoDeque = true;
	private int posNoDeque = 0;
	private int cartaDaVez;
	
	private static DequeDeCartas dequeDeCartas = null;
	
	private DequeDeCartas(){
		embaralharDeque(deque);
	}
	
	public static DequeDeCartas getDequeDeCartas() {
		if(dequeDeCartas == null) {
			dequeDeCartas = new DequeDeCartas();
		}
		return dequeDeCartas;
	}
	
	public void salvarDeque(File file) {
		String texto = passeLivreEstaNoDeque + "|" + posNoDeque + "|" + cartaDaVez;
		Model.getModel().escreverEmArquivo(file, texto);
	}
	
	public void carregarDeque(File file) {
		String[] items = Model.getModel().lerLinha(file).split("\\|");
		passeLivreEstaNoDeque = Boolean.parseBoolean(items[0]);
		posNoDeque = Integer.parseInt(items[1]);
		cartaDaVez = Integer.parseInt(items[2]);
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
	
	public void retornarPasseLivre() {
		passeLivreEstaNoDeque = true;
	}
	
	private void proximaCarta() {
		cartaDaVez = deque[posNoDeque]; 
		posNoDeque++;
		if(posNoDeque > tamanhoDeque) {
			posNoDeque = 0;
		}
	}
	
	public void pegarCarta() throws SaldoJogadorInsuficiente, SaldoBancoInsuficiente {
		proximaCarta();
		if(cartaDaVez == 9 && !passeLivreEstaNoDeque) {
			proximaCarta();
		}
	}
	
	public int getCartaDaVez() {
		return cartaDaVez;
	}
	
	
	private void recebeDinheiro(Player p, int valor) throws SaldoBancoInsuficiente {
		Bank.getBank().saque(p, valor);
	}
	
	private void daDinheiro(Player p, int valor) throws SaldoJogadorInsuficiente {
		p.checkFalencia(valor);
		Bank.getBank().deposito(p, valor);
	}
		
	private void recebeDosJogadores(Player p, int valor) throws SaldoJogadorInsuficiente {
		Bank.getBank().receberDeTodosOsJogadorer(p, valor);
	}
	
	public void usarCarta(Player p) throws SaldoJogadorInsuficiente, SaldoBancoInsuficiente { //efeitos das cartas
		
		switch(cartaDaVez) {
			case 1:
				recebeDinheiro(p,25);
				break;
			
			case 2:
				recebeDinheiro(p,150);
				break;
			
			case 3:
				recebeDinheiro(p,80);
				break;
				
			case 4:
				recebeDinheiro(p,400);
				break;
			case 5:
				recebeDinheiro(p,50);
				break;
			case 6:
				recebeDinheiro(p,60);
				break;
			case 7:
				recebeDinheiro(p,100);
				break;
			case 8:
				recebeDinheiro(p,100);
				break;
			case 9:
				p.ganhouPasseLivre();
				passeLivreEstaNoDeque = false;
				break;
			case 10:
				recebeDinheiro(p,200);
				break;
			case 11:
				recebeDosJogadores(p,50);
				break;
			case 12:
				recebeDinheiro(p,45);
				break;
			case 13:
				recebeDinheiro(p,100);
				break;
			case 14:
				recebeDinheiro(p,100);
				break;
			case 15:
				recebeDinheiro(p,20);
				break;
			case 16:
				daDinheiro(p,15);
				break;
			case 17:
				daDinheiro(p,25);
				break;
			case 18:
				daDinheiro(p,45);
				break;
			case 19:
				daDinheiro(p,30);
				break;
			case 20:
				daDinheiro(p,100);
				break;
			case 21:
				daDinheiro(p,100);
				break;
			case 22:
				daDinheiro(p,40);
				break;
			case 23:
				p.vaParaAPrisao();
				break;
			case 24:
				daDinheiro(p,30);
				break;
			case 25:
				daDinheiro(p,50);
				break;
			case 26:
				daDinheiro(p,25);
				break;
			case 27:
				daDinheiro(p,30);
				break;
			case 28:
				daDinheiro(p,45);
				break;
			case 29:
				daDinheiro(p,50);
				break;
			case 30:
				daDinheiro(p,50);
				break;
			default:
				break;
		}
	}
}
