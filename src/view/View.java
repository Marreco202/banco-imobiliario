package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import model.Model;
import observerView.ObservadoView;
import observerView.ObservadorView;
import observerView.ViewEvent;

public class View extends JFrame implements ObservadoView, ActionListener{
	
	public final int largura_menu=600;
	public final int altura_menu=380;
	
	public final int largura_tela_tabuleiro = 1000;
	public final int altura_tela_tabuleiro = 755;
	
	private static View view = null;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem carregarJogo, salvarJogo, terminarJogo;
	private TelaFinal telaFinal = null;
	
	JPanel panelAtual;
	
	private View() {
		setSize(largura_menu,altura_menu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		criaMenuBar();
		
		panelAtual = new PanelMainMenu();
		getContentPane().add(panelAtual);
		
		this.setTitle("Banco imobiliário");
		this.setVisible(true);
		this.setResizable(false);
		
	}
	
	boolean jaFoiAdicionado = false;
	void adicionarPararJogo() {
		if(jaFoiAdicionado) {
			return;
		}
		terminarJogo = new JMenuItem("Terminar Jogo");
		salvarJogo = new JMenuItem("Salvar Jogo");
		terminarJogo.addActionListener(this);
		salvarJogo.addActionListener(this);
		fileMenu.add(salvarJogo);
		fileMenu.add(terminarJogo);
		jaFoiAdicionado = true;
	}
	
	private void criaMenuBar() {
		menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        fileMenu = new JMenu("Jogo");
        menuBar.add(fileMenu);

        carregarJogo = new JMenuItem("Carregar Jogo");

        carregarJogo.addActionListener(this);
        
        fileMenu.add(carregarJogo);
	}
	
	public static View getView() {
		if(view == null) {
			view = new View();
		}
		return view;
	}
	
	public void clicouIniciarJogo(int qtd, boolean devMode) {
		ViewEvent e = new ViewEvent();
		e.setClicouIniciarJogo(true);
		e.setQtdJogadores(qtd);
		e.setDevMode(devMode);
		this.notificarObservadores(e);
	}
	
	public void clicouRolarDados() {
		ViewEvent e = new ViewEvent();
		e.setClicouRolarDados(true);
		this.notificarObservadores(e);
	}
	
	public void finalizarRodada() {
		ViewEvent e = new ViewEvent();
		e.setClicouFinalizarRodada(true);
		this.notificarObservadores(e);
		i=0;j=0;
	}
	
	private int i=0, j=0, t=0;
	public void clicouSetDados(int v) {
		if(t == 0) {
			i = v;
			t = 1;
		}else {
			j = v;
			t = 0;
		}
		ViewEvent e = new ViewEvent();
		e.setDadosDaVez(true, i, j);
		this.notificarObservadores(e);
	}
	
	public void repaintJanela() {
		panelAtual.repaint();
	}
	
	public void clicouComprar() {
		ViewEvent e = new ViewEvent();
		e.setClicouComprarPropriedade(true);
		this.notificarObservadores(e);
	}
	
	public void clicouVender(Integer pos) {
		ViewEvent e = new ViewEvent();
		e.setClicouVender(true, (int)pos);
		this.notificarObservadores(e);
	}
	
	public void clicouConstruir() {
		ViewEvent e = new ViewEvent();
		e.setClicouConstuir(true);
		this.notificarObservadores(e);
	}
	
	public void iniciarTabuleiro() {
		getContentPane().remove(panelAtual);
		
		this.setSize(largura_tela_tabuleiro, altura_tela_tabuleiro);
		
		adicionarPararJogo();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == terminarJogo) {
			if(telaFinal != null) {
				telaFinal.dispose();
				telaFinal = null;
			}
			telaFinal = new TelaFinal();
		}else if(e.getSource() == salvarJogo) {
			salvarJogo();
		}else if(e.getSource() == carregarJogo) {
			carregarJogo();
		}	
	}
	
	private void salvarJogo() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		int resposta = fileChooser.showSaveDialog(null);
		if(resposta == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			Model.getModel().salvarJogo(file);
			repaintJanela();
		}
	}
	
	private void carregarJogo() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		int resposta = fileChooser.showOpenDialog(null);
		if(resposta == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			Model.getModel().carregarJogo(file);
			iniciarTabuleiro();
		}
	}
}
