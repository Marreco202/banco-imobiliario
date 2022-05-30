package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Model;

public class PanelTabuleiro  extends JPanel implements MouseListener{

	private int qtdPlayer;
	private int[][] posNoTabuleiro = {{615,623},{547,641},{493,640},{436,643},{382,646},{329,646},{269,645},{217,644},{159,648},{105,646},{13,656},{12,559},{13,498},{11,451},{12,394},{12,335},{12,277},{11,223},{12,166},{12,115},{17,43},{105,22},{164,22},{216,23},{269,19},{327,20},{380,24},{439,16},{495,23},{550,21},{620,36},{626,116},{640,163},{626,232},{626,280},{628,336},{626,394},{630,452},{625,503},{624,558}};
	private Model model;
	
	
	public PanelTabuleiro(int qtdPlayer) {
		super();
		this.qtdPlayer = qtdPlayer;
		this.addMouseListener(this);
		model = Model.getModel();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D teste = (Graphics2D) g;
		Image tabuleiro = null;
		
		tabuleiro = loadImage("./img/tabuleiro.png");

		
		g.drawImage(tabuleiro, 0, 0, null); //tabuleiro
		for(int i = 0; i< model.getNumeroDeJogadores(); i++) {
			Image pinos = loadImage("./img/pinos/pin" + Integer.toString(i) + ".png");
			int posDoJogaodor = model.posDoJogador(i);
			g.drawImage(pinos, posNoTabuleiro[posDoJogaodor][0]+i*7, posNoTabuleiro[posDoJogaodor][1], null);
			System.out.println(posNoTabuleiro[posDoJogaodor][0] + " " + posNoTabuleiro[posDoJogaodor][1]);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.print("{"+e.getX() + "," + e.getY()+"},");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
