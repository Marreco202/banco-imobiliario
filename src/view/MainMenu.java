package view;

import java.awt.Color;

import javax.swing.*;

public class MainMenu extends JFrame {
	public final int LARG_DEFAULT=600;
	public final int ALT_DEFAULT=360;
	
	public MainMenu() {
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel p = new PanelMainMenu();
		getContentPane().add(p);
		
		this.setTitle("Banco imobiliário");
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void iniciarTabuleiro(int qtd) {
		new Board(qtd);
		this.dispose();
	}
}
