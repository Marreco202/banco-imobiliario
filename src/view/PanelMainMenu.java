package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelMainMenu extends JPanel implements MouseListener{
	
	private final int XposPergunta = 60;
	private final int YposPergunta = 60;

	private RadioButton[] botoesEscolhaNumeroJogadores = {
			new RadioButton(50, 100, 92, 92, "2"),
			new RadioButton(152, 100, 92, 92, "3"),
			new RadioButton(254, 100, 92, 92, "4"),
			new RadioButton(356, 100, 92, 92, "5"),
			new RadioButton(458, 100, 92, 92, "6")
	};
	private Botao iniciarJogo = new Botao(165, 240, 270, 60, "Iniciar Jogo");
	private CheckButton devMode = new CheckButton(500, 285, 85, 30, "DevMode");
	
	public PanelMainMenu() {
		super();
		this.setBackground(Color.black);
		this.addMouseListener(this);
		
		iniciarJogo.setFontSize(40);
		iniciarJogo.setXposContent(8);
		iniciarJogo.setYposContent(43);
		
		devMode.setFontSize(15);
		devMode.setXposContent(6);
		devMode.setYposContent(20);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		
        Font f = new Font("Comic Sans MS", Font.BOLD, 28);
        canvas.setFont(f);
        canvas.setColor(Color.white);
        canvas.drawString("Quantos jogadores vão jogar?", XposPergunta, YposPergunta);
        
        for(int i=0; i<5; i++) {
        	botoesEscolhaNumeroJogadores[i].draw(canvas); 
        }
        
        iniciarJogo.draw(canvas);
        devMode.draw(canvas);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		RadioButton.clicouNaTela(e.getX(), e.getY());
		devMode.verificaSeFoiClicado(e.getX(), e.getY());
		repaint();
		if(iniciarJogo.verificaSeFoiClicado(e.getX(), e.getY())) {
			IniciarGraficos topFrame = (IniciarGraficos) SwingUtilities.getWindowAncestor(this);
			topFrame.iniciarTabuleiro(RadioButton.getBotaoSelecionado()+2);
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
