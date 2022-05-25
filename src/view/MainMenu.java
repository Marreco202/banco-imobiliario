package view;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame{
	private int screenX = 200;
	private int screenY = 400;
	private int qtdPlayer = 3;
	

	public MainMenu(String s){
		super();
		setVisible(true);
		setSize(screenX,screenY);
		JButton b = new JButton("Novo Jogo");
		b.addActionListener(new NewGameListener(this));
		
		
		JPanel p = new JPanel();
		JLabel l = new JLabel("Menu Inicial");
		
		
		setLayout(new FlowLayout());
		p.add(l);
		p.add(b);
		JRadioButton b1 = new JRadioButton("3",true);
		JRadioButton b2 = new JRadioButton("4");
		JRadioButton b3 = new JRadioButton("5");
		JRadioButton b4 = new JRadioButton("6");
		
		b1.addActionListener(new QtdPlayerListener(this,b1));
		b2.addActionListener(new QtdPlayerListener(this,b2));
		b3.addActionListener(new QtdPlayerListener(this,b3));
		b4.addActionListener(new QtdPlayerListener(this,b4));
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(b1);bg.add(b2);bg.add(b3);bg.add(b4);
		p.add(b1);p.add(b2);p.add(b3);p.add(b4);
		
		
		
		
		getContentPane().add(p);
	}
	
	public int getQtdPlayer() {
		return qtdPlayer;
}
	public void setQtdPlayer(int i) {
		qtdPlayer = i;
	}
}