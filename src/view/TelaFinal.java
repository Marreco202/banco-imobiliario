package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

import model.Model;

public class TelaFinal extends JFrame {

	private Model model = Model.getModel();

	private int largura = 400;
	private int altura = 300;
	
	public TelaFinal(){
		this.setSize(largura, altura);
		this.setTitle("Fim de Jogo");
		this.setVisible(true);
		this.setResizable(false);
	}

	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.largura, this.altura);
		
		Color[] jogadores = model.getJogadoresOrdenadosPorFortuna();
		int[] fortundaJogadores = model.getFortunaOrdenadaDosJogadores();
		
		int[][] pos = {{100,70},{110,105},{120,140},{130,170},{140,195},{142,220}};
		int[] size = {30,26,22,18,14,12};
		
		for(int i=0; i<fortundaJogadores.length; i++) {
			Font f = new Font("Comic Sans MS", Font.BOLD, size[i]);
        	g.setFont(f);
        	g.setColor(jogadores[i]);
			g.drawString("Jogador: "+Integer.toString(fortundaJogadores[i]), pos[i][0], pos[i][1]);
		}
	}
}
