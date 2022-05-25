package view;
import java.awt.*;

public class MyBox {

	private int pos_x, pos_y, size_x, size_y;
	private Color filler_color = null;
	
	
	private String content = ""; //conteudo do que vai ficar ali dentro da caixa
	private Font f;
	private int content_pos_x, content_pos_y;// onde o conteudo da caixa vai ficar em relacao ao tamanho da caixa
	
	MyBox(int pos_x,int pos_y, int size_x, int size_y){ //caixa normal
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.size_x = size_x;
		this.size_y = size_y;
		
	}
	MyBox(int pos_x,int pos_y, int size_x, int size_y, String content){ //caixa com algo escrito
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.size_x = size_x;
		this.size_y = size_y;
		this.content = content;
	}
	
	MyBox(int pos_x,int pos_y, int size_x, int size_y,String content, Color fc){//caixa com linha colorida
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.size_x = size_x;
		this.size_y = size_y;
		this.content = content;
		this.filler_color = fc;	
	}

	
	
	public void draw(Graphics2D canvas) {
		Color oldColor = canvas.getColor();
		//caixa
		canvas.setPaint(Color.WHITE); //Default
		
		if(filler_color != null) { //se tiver cor
			canvas.setColor(filler_color);
			canvas.fillRect(pos_x, pos_y, size_x, size_y);
		}else {
			canvas.drawRect(pos_x, pos_y, size_x, size_y);			
		}
		
		if(content != "") {//se tiver texto
			canvas.setPaint(Color.black);
			canvas.drawString(content,content_pos_x + pos_x,content_pos_y + pos_y + (size_y/2) + 16);
		}
		
		canvas.setColor(oldColor);
	}
	
	public void setContent(String s) {
		content = s;
	}
}
