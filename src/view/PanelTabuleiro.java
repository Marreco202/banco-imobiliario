package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Model;

public class PanelTabuleiro  extends JPanel implements MouseListener{

	private int[][] posNoTabuleiro = {{615,623},{547,641},{493,640},{436,643},{382,646},{329,646},{269,645},{217,644},{159,648},{105,646},{13,656},{12,559},{13,498},{11,451},{12,394},{12,335},{12,277},{11,223},{12,166},{12,115},{17,43},{105,22},{164,22},{216,23},{269,19},{327,20},{380,24},{439,16},{495,23},{550,21},{620,36},{626,116},{640,163},{626,232},{626,280},{628,336},{626,394},{630,452},{625,503},{624,558}};
	private Model model;
	
	private Image tabuleiro;
	private Image[] pinos = new Image[6];
	private Image[] dados = new Image[6];
	
	private Botao botaoRolarDados;
	
	public PanelTabuleiro() {
		super();
		this.addMouseListener(this);
		this.model = Model.getModel();
		
		this.tabuleiro = loadImage("./img/tabuleiro.png");
		
		this.botaoRolarDados = new Botao(715, 160, 255, 45, "Rolar Dados");
		this.botaoRolarDados.setFontSize(35);
		this.botaoRolarDados.setXposContent(8);
		this.botaoRolarDados.setYposContent(35);
		
		for(int i = 0; i< 6; i++) {
			pinos[i] = loadImage("./img/pinos/pin" + Integer.toString(i) + ".png");
			dados[i] = loadImage("./img/dados/die_face_"+(i+1)+".png");
		}
	}
	
	public void paintComponent(Graphics g) {
		drawTabuleiroEPinos(g);
		g.setColor(Color.black);
		g.fillRect(700, 0, 300, 700);
		drawAreaDeDados(g);
		this.botaoRolarDados.draw((Graphics2D) g);
	}
	
	public void repaintTabuleiro() {
		repaint();
	}
	
	private void drawAreaDeDados(Graphics g) {
		Color corJogadorDaVez = model.getCorJogadorDaVez();
		g.setColor(corJogadorDaVez);
		g.fillRect(700, 5, 290, 145);
		
		Image dado1, dado2;
		if(model.getDadosDaVez()[0]==0) {
			dado1 = dados[0];
		}else {
			dado1 = dados[model.getDadosDaVez()[0]-1];
		}
		
		if(model.getDadosDaVez()[1]==0) {
			dado2 = dados[0];
		}else {
			dado2 = dados[model.getDadosDaVez()[1]-1];
		}
		
		System.out.println(model.getDadosDaVez()[0] + " " + model.getDadosDaVez()[1]);
		
		g.drawImage(dado1, 710, 10, 130, 130, null);
		g.drawImage(dado2, 850, 10, 130, 130, null);
	}

	private void drawTabuleiroEPinos(Graphics g) {
		g.drawImage(tabuleiro, 0, 0, null); //tabuleiro
		for(int i = 0; i< model.getNumeroDeJogadores(); i++) {
			int posDoJogaodor = model.posDoJogador(i);
			g.drawImage(pinos[i], posNoTabuleiro[posDoJogaodor][0]+i*7, posNoTabuleiro[posDoJogaodor][1], null);
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
		if(botaoRolarDados.verificaSeFoiClicado(e.getX(), e.getY())) {
			View topFrame = (View) SwingUtilities.getWindowAncestor(this);
			topFrame.clicouRolarDados();
		}
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
