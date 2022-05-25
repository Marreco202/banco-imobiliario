package view;

import java.awt.Color;

public class CheckButton extends Botao {

	private boolean checked = false;
	
	public boolean verificaSeFoiClicado(int x, int y) {
		if(super.verificaSeFoiClicado(x, y)) {
			if(checked) {
				checked = false;
				setCor(Color.white);
			}else {
				checked = true;
				setCor(Color.yellow);
			}
		}
		return super.verificaSeFoiClicado(x, y);
	}
	
	public CheckButton(int pos_x,int pos_y, int size_x, int size_y, String content) {
		super(pos_x, pos_y, size_x, size_y, content);
	}
	
}
