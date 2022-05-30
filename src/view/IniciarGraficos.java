package view;

import javax.swing.*;

import controller.Controller;

public class IniciarGraficos extends JFrame {
	public final int largura_menu=600;
	public final int altura_menu=360;
	
	public final int largura_tela_tabuleiro = 1000;
	public final int altura_tela_tabuleiro = 736;
	
	JPanel panelAtual;
	
	public IniciarGraficos() {
		setSize(largura_menu,altura_menu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		panelAtual = new PanelMainMenu();
		getContentPane().add(panelAtual);
		
		this.setTitle("Banco imobiliário");
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void iniciarTabuleiro(int qtd) {
		getContentPane().remove(panelAtual);
		
		Controller.getController().iniciarJogo(qtd);
		
		panelAtual = new PanelTabuleiro(qtd);
		getContentPane().add(panelAtual);
		
		setSize(largura_tela_tabuleiro, altura_tela_tabuleiro);
		
	}
}
