package view;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame{
	private int screenX = 800;
	private int screenY = 700;
	
	private void desenhaBotao(Graphics2D g,String conteudo, Color cor,int x, int y) {
		g.setPaint(cor);
		g.fillRect(390,225,x,y);
		
		g.setPaint(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD,12));
		//g.drawString(conteudo,x,);
	}
	
	private void desenhaMensagemTitulo(Graphics2D g) {
		Color oldColor = g.getColor();
		g.setPaint(Color.WHITE);
		g.setFont(new Font("Comic Sans",Font.BOLD, 50));
		g.drawString("Banco imobiliario",screenX/4 - 41 ,screenY/4 - 60);
		g.setPaint(oldColor);
	}
	

	public MainMenu(){
		super();
		setSize(800,700);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		canvas.setPaint(Color.black);
		canvas.fillRect(0,0,screenX,screenY);
		desenhaMensagemTitulo(canvas);
		desenhaBotao(canvas, "Meu nome eh esse", Color.red, 270, 90);
		
	}
	
	
	
}
