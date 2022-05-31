package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RadioButton{
	
	private static int botaoSelecionado = 0;
	private static ArrayList<RadioButton> listaBotoes = new ArrayList<RadioButton>();
	private static int numeroDeBotoes = 0;
	
	public static void clicouNaTela(int x, int y) {
		if(numeroDeBotoes == 0) {
			return;
		}
		
		RadioButton b;
		for(int i=0; i<numeroDeBotoes; i++) {
			b = listaBotoes.get(i);
			if(x > b.pos_x && x < b.pos_x+b.size_x && y > b.pos_y && y < b.pos_y+b.size_y) {
				RadioButton botaoAnterior = listaBotoes.get(botaoSelecionado);
				botaoAnterior.cor = Color.white;
				b.cor = Color.yellow;
				botaoSelecionado = i;
			}
		}
	}
	
	public static int getBotaoSelecionado() {
		return botaoSelecionado;
	}
	
	private int pos_x, pos_y, size_x, size_y;
	private String content = "";
	private int id;
	private Graphics2D canvas;
	private Color cor = Color.white;
	
	public RadioButton(int pos_x,int pos_y, int size_x, int size_y, String content){ //caixa com algo escrito
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.size_x = size_x;
		this.size_y = size_y;
		this.content = content;
		this.id = RadioButton.numeroDeBotoes++;
		
		RadioButton.listaBotoes.add(this);
		
		if(RadioButton.numeroDeBotoes == 1) {
			cor = Color.yellow;
		}
		
	}
	
	public void draw(Graphics2D canvas) {
		Color oldColor = canvas.getColor();
		
		canvas.setColor(cor);
		canvas.fillRect(pos_x, pos_y, size_x, size_y);
		
		int fontSize = 40;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        canvas.setFont(f);
		canvas.setColor(Color.black);
		canvas.drawString(content, pos_x+(size_x/2)-12, pos_y+(size_y/2)+15);
		
		canvas.setColor(oldColor);
	}
	
}
