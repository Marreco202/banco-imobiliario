package view;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame{
	private int screenX = 800;
	private int screenY = 700;
	

	public MainMenu(String s){
		super();
		setVisible(true);
		setSize(screenX,screenY);
		JButton b = new JButton("Novo Jogo");
		b.addActionListener(new MyListener(this));
		
		JPanel p = new JPanel();
		
		JLabel l = new JLabel("Menu Inicial");
		l.
		
		setLayout(new FlowLayout());
		p.add(l);
		p.add(b);
		getContentPane().add(p);
		
		
		
	}
	
	
	
}