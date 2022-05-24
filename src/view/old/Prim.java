package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prim extends JFrame {
	JButton b1 = new JButton("Botão 1");
	JButton b2 = new JButton("Botão 2");
	JPanel p = new JPanel();
public Prim(String s) {
	super(s);
	p.add(b1);
	p.add(b2);
	p.setBackground(Color.WHITE);
	getContentPane().add(p);
	setSize(400,300);
}
}
