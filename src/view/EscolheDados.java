package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

class EscolheDados {

	private Botao[] b = {
			new Botao(835, 5, 20, 20, "1"),
			new Botao(835, 30, 20, 20, "2"),
			new Botao(835, 55, 20, 20, "3"),
			new Botao(835, 80, 20, 20, "4"),
			new Botao(835, 105, 20, 20, "5"),
			new Botao(835, 130, 20, 20, "6"),
	};
	private int numeroBotoes = 6;
	private int botaoClicado;
	private boolean active;
	
	public EscolheDados(boolean active) {
		this.active = active;
		for(int i=0; i<numeroBotoes; i++) {
			b[i].setPosContent(4, 17);;
		}
	}
	
	public void draw(Graphics g) {
		if(!this.active) {
			return;
		}
		for(int i=0; i<numeroBotoes; i++) {
			b[i].draw((Graphics2D) g);
		}
	}
	
	public boolean verificaSeFoiClicado(int x, int y) {
		if(!this.active) {
			return false;
		}
		for(int i=0; i<numeroBotoes; i++) {
			if(b[i].verificaSeFoiClicado(x, y)) {
				botaoClicado = i+1;
				return true;
			}
		}
		return false;
	}
	
	public int getBotaoClicado() {
		return botaoClicado;
	}

}
