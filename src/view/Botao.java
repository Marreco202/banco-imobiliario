package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Botao {
	
	private int pos_x, pos_y, size_x, size_y;
	private String content = "";
	private Color cor = Color.white;
	private int fontSize = 20;
	private int XposContent = 5;
	private int YposContent = 5;
	
	public Botao(int pos_x,int pos_y, int size_x, int size_y, String content){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.size_x = size_x;
		this.size_y = size_y;
		this.content = content;
	}
	
	public boolean verificaSeFoiClicado(int x, int y) {
		if(x > pos_x && x < pos_x+size_x && y > pos_y && y < pos_y+size_y) {
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D canvas) {
		canvas.setColor(cor);
		canvas.fillRect(pos_x, pos_y, size_x, size_y);
		
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        canvas.setFont(f);
		canvas.setColor(Color.black);
		canvas.drawString(content, pos_x+XposContent, pos_y+YposContent);
	}
		
	public void setCor(Color cor) {
		this.cor = cor;
	}
	
	public void setFontSize(int t) {
		fontSize = t;
	}
	
	public void setPosContent(int x, int y) {
		this.XposContent = x;
		this.YposContent = y;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
