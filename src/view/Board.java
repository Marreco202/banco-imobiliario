package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	
	public void paintComponent(Graphics g) {
		Graphics2D teste = (Graphics2D) g;
		Image tabuleiro = null;
		File file = new File("");
		String path = file.getAbsolutePath();
		
		tabuleiro = loadImage(path + "/src/img/tabuleiro.png");

		
		Image[] pinos = new Image[6];
		
		for(int i = 0; i< 6; i++) {
			pinos[i] = loadImage(path + "/src/img/pinos/pin" + Integer.toString(i) + ".png");
		}
		g.drawImage(tabuleiro, 0, 0, null); //tabuleiro
		
		for(int i = 0; i < qtdPlayer; i++) {
			g.drawImage(pinos[i], 657 - (10*i), 607 + (8*i), null);
			
		}



	}
	
	public Image loadImage(String path) {
		Image i = null;
		try {
			i = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
}
