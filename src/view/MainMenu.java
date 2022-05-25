package view;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame{
	private int screenX = 800;
	private int screenY = 700;
	
	private void desenhaMensagemTitulo(Graphics2D g) {
		Color oldColor = g.getColor();
		g.setPaint(Color.WHITE);
		g.setFont(new Font("Comic Sans",Font.PLAIN, 50));
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
		int pos_x,pos_y,largura,altura;
		pos_x = 390;
		pos_y = 225;
		largura = 270;
		altura = 90;
		
		
		MyBox type = new MyBox(pos_x-(largura/2),pos_y-(altura/2),largura,altura,"3",Color.white);
		MyBox minus = new MyBox(pos_x,pos_y-(altura/2) + altura,(largura/2),altura,"+",Color.green); //trocar para botao
		MyBox plus = new MyBox(pos_x-(largura/2),pos_y-(altura/2) + altura,(largura/2),altura,"-",Color.red); //trocar para botao
		MyBox newGame = new MyBox(pos_x-(largura/2),pos_y-(altura/2) + 200,largura,altura,"New Game",Color.GRAY);
		MyBox loadGame =  new MyBox(pos_x-(largura/2),pos_y-(altura/2) + 350,largura,altura,"Load Game",Color.GRAY);
		
		canvas.setPaint(Color.black);
		canvas.fillRect(0,0,screenX,screenY);
		
		desenhaMensagemTitulo(canvas);
		type.draw(canvas);
		minus.draw(canvas);
		plus.draw(canvas);
		newGame.draw(canvas);
		loadGame.draw(canvas);
		//desenhaBotao(canvas, "Meu nome eh esse", Color.red, 270, 90);
	}
	
	
	
}