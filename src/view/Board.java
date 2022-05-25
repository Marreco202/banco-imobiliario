package view;
import java.awt.*;
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
		tabuleiro = Util.loadImage("/home/joaovgw/git/banco-imobiliario/img/tabuleiro.png");

		Image JogadorTeste = null;
		JogadorTeste = Util.loadImage("/home/joaovgw/git/banco-imobiliario/img/pinos/pin0.png");

		g.drawImage(tabuleiro, 0, 0, null); //tabuleiro
		g.drawImage(JogadorTeste, 43, 607, null);



	}
}
