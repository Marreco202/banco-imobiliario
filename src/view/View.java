package view;

import javax.swing.*;


import controller.Controller;
import model.Model;
import observerView.ObservadoView;
import observerView.ObservadorView;
import observerView.ViewEvent;

public class View extends JFrame implements ObservadoView{
	
	public final int largura_menu=600;
	public final int altura_menu=360;
	
	public final int largura_tela_tabuleiro = 1000;
	public final int altura_tela_tabuleiro = 736;
	
	private static View view = null;
	
	JPanel panelAtual;
	
	private View() {
		setSize(largura_menu,altura_menu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		panelAtual = new PanelMainMenu();
		getContentPane().add(panelAtual);
		
		this.setTitle("Banco imobiliário");
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static View getView() {
		if(view == null) {
			view = new View();
		}
		return view;
	}
	
	public void clicouIniciarJogo(int qtd) {
		ViewEvent e = new ViewEvent(true, false, false);
		e.setQtdJogadores(qtd);
		this.notificarObservadores(e);
	}
	
	public void clicouRolarDados() {
		ViewEvent e = new ViewEvent(false, true, false);
		this.notificarObservadores(e);
	}
	
	public void repaintJanela() {
		panelAtual.repaint();
	}
	
	public void clicouComprar() {
		ViewEvent e = new ViewEvent(false, false, true);
		this.notificarObservadores(e);
	}
	
	public void iniciarTabuleiro() {
		getContentPane().remove(panelAtual);
		
		this.setSize(largura_tela_tabuleiro, altura_tela_tabuleiro);
		
		panelAtual = new PanelTabuleiro();
		getContentPane().add(panelAtual);
		
		panelAtual.setSize(largura_tela_tabuleiro, altura_tela_tabuleiro);	
	}

	@Override
	public void addObservador(ObservadorView novoObservador) {
		listaDeObservadores.add(novoObservador);
	}

	@Override
	public void notificarObservadores(ViewEvent e) {
		for (int i = 0; i < listaDeObservadores.size(); i++)
            listaDeObservadores.get(i).handleInput(e);
	}

}
