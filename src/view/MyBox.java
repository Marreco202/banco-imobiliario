package view;
import java.awt.*;

public class MyBox {

	private int pos_x, pos_y, size_x, size_y;
	private Color filler_color = null;
	
	
	private String content = ""; //conteudo do que vai ficar ali dentro da caixa
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
		canvas.setPaint(Color.WHITE); //padrao 
		
		if(filler_color != null) {
			canvas.fillRect(pos_x, pos_y, size_x, size_y);
		}else {
			canvas.drawRect(pos_x, pos_y, size_x, size_y);			
		}
		//texto
		canvas.setPaint(Color.WHITE);
		canvas.drawString(content,content_pos_x,content_pos_y);
		
		canvas.setColor(oldColor);
	}
}
