package view;
import java.awt.*;
import java.io.File;

import javax.swing.*;

public class Board extends JFrame{

	public Board(){
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,700);
		setVisible(true);
		Insets i = getInsets(); //descricoes da janela
	}


	public void paint(Graphics g) {
		Graphics2D teste = (Graphics2D) g;
		Image tabuleiro = null;
		File file = new File("");
		String path = file.getAbsolutePath();
		//System.out.println(path);
		
		tabuleiro = Util.loadImage(path + "/src/img/tabuleiro.png");

		Image JogadorTeste = null;
		JogadorTeste = Util.loadImage(path + "/src/img/pinos/pin0.png");

		g.drawImage(tabuleiro, 0, 0, null); //tabuleiro
		g.drawImage(JogadorTeste, 43, 607, null);



	}
}
