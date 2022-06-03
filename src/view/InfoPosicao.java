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
		int valorDeCompra, valorDeVenda;
		Color corProprietario;
		try {
			valorDeCompra = Model.getModel().getValorDeCompra(pos);
			valorDeVenda = Model.getModel().getValorDeVenda(pos);
		}catch (Exception e) {
			return;
		}
		
		Font f = new Font("Comic Sans MS", Font.BOLD, 11);
        g.setFont(f);
        g.setColor(Color.white);
		g.drawString("Valor de compra: ", x+185, y+30);
		g.drawString("Valor de venda: ", x+185, y+80);
		g.drawString("Proprietário: ", x+185, y+120);
		
		g.setColor(Color.red);
		g.drawString(Integer.toString(valorDeCompra), x+200, y+50);
		g.drawString(Integer.toString(valorDeVenda), x+200, y+100);
		
		try {
			corProprietario = Model.getModel().getCorProprietario(pos);
			g.setColor(corProprietario);
			g.fillRect(x+195, y+130, 50, 50);
		}catch (Exception e) {
			return;
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
