package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Model;

public class FrameListaPropriedades extends JFrame implements MouseListener{

	private Model model = Model.getModel();
	
	private int largura = 320;
	private int altura = 350;
	
	private Botao proximo;
	private Botao anterior;
	private Botao vender;
	
	private ArrayList<Integer> lista;
	private int indexDaLista = 0;
	
	public FrameListaPropriedades(){
		this.setSize(largura, altura);
		this.setTitle("Lista de propriedade");
		this.setVisible(true);
		this.setResizable(false);
		
		this.anterior = new Botao(10, 310, 130, 25, "Anterior");
		this.anterior.setPosContent(15, 20);
		
		this.proximo = new Botao(180, 310, 130, 25, "Proximo");
		this.proximo.setPosContent(15, 20);
		
		this.vender = new Botao(95, 280, 130, 25, "Vender");
		this.vender.setPosContent(25, 20);
		
		// array com a posição das propriedades que o player é dono
		lista = model.getTodasPropriedadesJogadorAtual(); 
		if(lista.size()<1) {
			this.dispose();
		}
		
		this.addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		if(lista.size() == 0) {
			this.dispose();
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, this.largura, this.altura);
		
		int y = 50;
		InfoPosicao ip = new InfoPosicao();
		ip.draw(g, 5, y, lista.get(indexDaLista));
		
		proximo.draw((Graphics2D) g);
		anterior.draw((Graphics2D) g);
		vender.draw((Graphics2D) g);
	}

	private void calculaAnterior() {
		int size = lista.size();
		if(size < 2) {
			return;
		}else if(indexDaLista >0) {
			indexDaLista--;
		}else if(indexDaLista==0) {
			indexDaLista = size-1;
		}
	}
	
	private void calculaProximo() {
		int size = lista.size();
		if(size < 2) {
			return;
		}else if(indexDaLista < size-1) {
			indexDaLista++;
		}else if(indexDaLista==size-1) {
			indexDaLista = 0;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(anterior.verificaSeFoiClicado(e.getX(), e.getY())) {
			calculaAnterior();
			repaint();
		}else if(proximo.verificaSeFoiClicado(e.getX(), e.getY())) {
			calculaProximo();
			repaint();
		}else if(vender.verificaSeFoiClicado(e.getX(), e.getY())) {
			View.getView().clicouVender(lista.get(indexDaLista));
			View.getView().repaintJanela();
			repaint();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
}
