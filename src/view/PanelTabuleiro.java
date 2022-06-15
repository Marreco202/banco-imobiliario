package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import exeptions.PropriedadeNaoPossuiDono;
import model.Model;

public class PanelTabuleiro extends JPanel implements MouseListener{

	private int[][] posNoTabuleiro = {{615,623},{547,641},{493,640},{436,643},{382,646},{329,646},{269,645},{217,644},{159,648},{105,646},{13,656},{12,559},{13,498},{11,451},{12,394},{12,335},{12,277},{11,223},{12,166},{12,115},{17,43},{105,22},{164,22},{216,23},{269,19},{327,20},{380,24},{439,16},{495,23},{550,21},{620,36},{626,116},{640,163},{626,232},{626,280},{628,336},{626,394},{630,452},{625,503},{624,558}};
	private Model model;
	
	private Image tabuleiro;
	private Image[] pinos = new Image[6];
	private Image[] dados = new Image[6];
	
	private Botao botaoRodada;
	private Botao botaoDesejaComprar;
	private Botao botaoConstruir;
	private Botao botaoVerPropriedades;
	
	private EscolheDados escolheDados;
	
	private JFrame listaPropriedades;
	private InfoPosicao infoPosicao;
	
	public PanelTabuleiro() {
		super();
		this.addMouseListener(this);
		this.model = Model.getModel();
		
		this.tabuleiro = loadImage("./img/tabuleiro.png");
		
		this.botaoRodada = new Botao(715, 170, 255, 45, "Rolar Dados");
		this.botaoRodada.setFontSize(35);
		this.botaoRodada.setPosContent(8, 35);
		
		this.botaoDesejaComprar = new Botao(710, 500, 130, 45, "Comprar", false);
		this.botaoDesejaComprar.setFontSize(22);
		this.botaoDesejaComprar.setPosContent(10, 32);
		
		this.botaoConstruir = new Botao(860, 500, 130, 45, "Construir", false);
		this.botaoConstruir.setFontSize(22);
		this.botaoConstruir.setPosContent(5, 32);
		
		this.botaoVerPropriedades = new Botao(750, 640, 200, 30, "Ver Propriedades");
		this.botaoVerPropriedades.setFontSize(18);
		this.botaoVerPropriedades.setPosContent(10, 20);
		
		this.escolheDados = new EscolheDados(model.getDevMode());
		
		this.infoPosicao = new InfoPosicao();
		
		//carrega as imagens dos pinos e dos dados
		for(int i = 0; i< 6; i++) {
			pinos[i] = loadImage("./img/pinos/pin" + Integer.toString(i) + ".png");
			dados[i] = loadImage("./img/dados/die_face_"+(i+1)+".png");
		}
	}
	
	public void paintComponent(Graphics g) {
		//fecha janela da lista de propriedades caso esteja aberta
		if(listaPropriedades != null) {
			listaPropriedades.dispose();
			listaPropriedades = null;
		}
		this.botaoDesejaComprar.setActive(false);
		this.botaoConstruir.setActive(false);

		
		drawTabuleiroEPinos(g);
		drawCartaTirada(g);
		drawMensagemParaJogador(g);
		
		//background
		g.setColor(Color.black);
		g.fillRect(700, 0, 300, 700);
		
		this.drawAreaDeDados(g);
		this.escolheDados.draw(g);
		this.botaoRodada.draw((Graphics2D) g);
		
		Font f = new Font("Comic Sans MS", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.white);
		g.drawString("Você está em: ", 705, 250);
		this.infoPosicao.draw(g, 700, 270, model.getPosJogadorDaVez());
		
		this.drawOpcoesJogador(g, model.getPosJogadorDaVez());
		
		this.botaoVerPropriedades.draw((Graphics2D) g);
	}
	
	private void drawMensagemParaJogador(Graphics g) {
		String m = Model.getModel().getMensagemAoPlayer();
		if(m != "") {
			g.setColor(Color.black);
			g.fillRect(100, 100, 500, 26);
		}
		Font f = new Font("Comic Sans MS", Font.BOLD, 12);
        g.setFont(f);
        g.setColor(Color.red);
		g.drawString(m, 105, 120);
	}

	public void repaintTabuleiro() {
		repaint();
	}
	
	private void drawOpcoesJogador(Graphics g, int pos) {
		
		Font f = new Font("Comic Sans MS", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.white);
		g.drawString("Você tem: ", 705, 620);
		
		int saldo = model.getSaldoJogadorDaVez();
		g.setColor(Color.red);
		g.drawString(Integer.toString(saldo), 830, 620);
		
		if(model.podeComprarPropriedade(pos)) {
			this.botaoDesejaComprar.setActive(true);
			this.botaoDesejaComprar.draw((Graphics2D) g);
		}else if(model.podeConstruir(pos)) {
			this.botaoConstruir.setActive(true);
			this.botaoConstruir.draw((Graphics2D) g);
		}
	}
	
	private void drawAreaDeDados(Graphics g) {
		Color corJogadorDaVez = model.getCorJogadorDaVez();
		g.setColor(corJogadorDaVez);
		g.fillRect(700, 5, 290, 145);
		
		Image dado1, dado2;
		if(model.getDadosDaVez()[0]==0) {
			return;
		}else {
			dado1 = dados[model.getDadosDaVez()[0]-1];
			g.drawImage(dado1, 710, 20, 120, 120, null);
		}
		
		if(model.getDadosDaVez()[1] == 0) {
			return;
		}else {
			dado2 = dados[model.getDadosDaVez()[1]-1];
			g.drawImage(dado2, 860, 20, 120, 120, null);
		}
		
	}
	
	private void drawCartaTirada(Graphics g) {
		if(model.tirouCarta()) {
			Image carta = loadImage("img/sorteReves/chance"+model.getNumeroDaCarta()+".png");
			g.drawImage(carta, 300, 300, 200, 200, null);
		}
	}

	private void drawTabuleiroEPinos(Graphics g) {
		g.drawImage(tabuleiro, 0, 0, null); //tabuleiro
		for(int i = 0; i< model.getNumeroDeJogadores(); i++) {
			int posDoJogaodor = model.posDoJogador(i);
			g.drawImage(pinos[i], posNoTabuleiro[posDoJogaodor][0]+i*7, posNoTabuleiro[posDoJogaodor][1], null);
		}
	}
	
	public Image loadImage(String path) {
		if(path == null) {
			return null;
		}
		Image i = null;
		try {
			i = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return i;
	}

	boolean terminouRodada = true;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(botaoRodada.verificaSeFoiClicado(e.getX(), e.getY())) {
			View topFrame = (View) SwingUtilities.getWindowAncestor(this);
			if(!terminouRodada && model.podeFinalizarRodada()) {
				botaoRodada.setContent("Rolar Dados");
				botaoRodada.setPosContent(8, 35);
				terminouRodada = true;
				topFrame.finalizarRodada();
			}else if(terminouRodada && model.podeFinalizarRodada()){
				botaoRodada.setContent("Finalizar");
				botaoRodada.setPosContent(43, 35);
				terminouRodada = false;
				topFrame.clicouRolarDados();
			}
		}else if(botaoDesejaComprar.verificaSeFoiClicado(e.getX(), e.getY())) {
			View topFrame = (View) SwingUtilities.getWindowAncestor(this);
			topFrame.clicouComprar();
		}else if(botaoVerPropriedades.verificaSeFoiClicado(e.getX(), e.getY())) {
			listaPropriedades = new FrameListaPropriedades();
		}else if(escolheDados.verificaSeFoiClicado(e.getX(), e.getY())) {
			View topFrame = (View) SwingUtilities.getWindowAncestor(this);
			topFrame.clicouSetDados(escolheDados.getBotaoClicado());
		}else if(botaoConstruir.verificaSeFoiClicado(e.getX(), e.getY())) {
			View topFrame = (View) SwingUtilities.getWindowAncestor(this);
			topFrame.clicouConstruir();
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
