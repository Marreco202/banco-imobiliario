package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Model;

public class InfoPosicao{
		
	//Essa é a classe responsavel por desenhar as informações de cada casa do tabuleiro
	
	public InfoPosicao() {
	}
	
	public void draw(Graphics g, int x, int y, int pos) {
		
		g.setColor(Color.black);
		g.fillRect(x, y-18, 300, y+200);
		
		Font f = new Font("Comic Sans MS", Font.BOLD, 15);
        g.setFont(f);
		g.setColor(Color.red);
		g.drawString(Model.getModel().getNomeDaCasa(pos), x+15, y);
		
		Image imagemDaCasa = loadImage(Model.getModel().getImagePath(pos));
		if(imagemDaCasa != null) {
			g.drawImage(imagemDaCasa, x+5, y+10, 175, 210, null);
		}
		
		drawValoresDaCasa(g, x, y, pos);
	}
	
	private void drawValoresDaCasa(Graphics g, int x, int y, int pos) {
		int valorDeCompra, valorDeVenda, numDeCasas;
		boolean temHotel;
		Color corProprietario;
		
		// se não for uma casa "Compravel" dara erro e nada sera impresso
		try {
			valorDeCompra = Model.getModel().getValorDeCompra(pos);
			valorDeVenda = Model.getModel().getValorDeVenda(pos);
		}catch (Exception e) {
			return;
		}
		
		Font f = new Font("Comic Sans MS", Font.BOLD, 11);
        g.setFont(f);
        g.setColor(Color.white);
		g.drawString("Valor de compra: ", x+185, y+20);
		g.drawString("Valor de venda: ", x+185, y+60);
		g.drawString("Proprietário: ", x+185, y+100);
		
		g.setColor(Color.red);
		g.drawString(Integer.toString(valorDeCompra), x+200, y+40);
		g.drawString(Integer.toString(valorDeVenda), x+200, y+80);
		
		// se for uma companhia dara erro e mais nenhuma informação será impressa na tela
		try {
			corProprietario = Model.getModel().getCorProprietario(pos);
			g.setColor(corProprietario);
			g.fillRect(x+195, y+110, 80, 15);
		}catch (Exception e) {
			return;
		}
		
		try {
			g.setColor(Color.white);
			numDeCasas = Model.getModel().getNumeroDeCasasEmPropriedade(pos);
			temHotel = Model.getModel().getTemHotel(pos);
			g.drawString("Numero de casas: ", x+185, y+140);
			g.drawString("Tem Hotel: ", x+185, y+180);
			g.setColor(Color.red);
			g.drawString(Integer.toString(numDeCasas), x+200, y+160);
			g.drawString(Boolean.toString(temHotel), x+200, y+200);
		}catch (Exception e) {
			// TODO: handle exception
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
}
