package view;
import java.awt.*;
import java.io.File;

import javax.swing.*;

public class Board extends JFrame{

	int qtdPlayer;
	
	public Board(int qp){
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,700);
		setVisible(true);
		qtdPlayer = qp;
	}

	public int getQtdPlayer() {
		return qtdPlayer;
	}
	
	public void paint(Graphics g) {
		Graphics2D teste = (Graphics2D) g;
		Image tabuleiro = null;
		File file = new File("");
		String path = file.getAbsolutePath();
		//System.out.println(path);
		
		tabuleiro = Util.loadImage(path + "/src/img/tabuleiro.png");

		//JogadorTeste = Util.loadImage(path + "/src/img/pinos/pin0.png");
		
		Image[] pinos = new Image[6];
		
		for(int i = 0; i< 6; i++) {
			pinos[i] = Util.loadImage(path + "/src/img/pinos/pin" + Integer.toString(i) + ".png");
		}
		g.drawImage(tabuleiro, 0, 0, null); //tabuleiro
		
		for(int i = 0; i < qtdPlayer; i++) {
			g.drawImage(pinos[i], 657 - (10*i), 607 + (8*i), null);
			
		}




	}
}
