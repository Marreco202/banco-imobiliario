package model;

public class Prop extends Tile {

	private int valor;
	private Cor cor;
	
	public Prop(Cor c, int nv) {
		cor = c;
		valor = nv;
	}
	
	public void setCusto(int nv) {
		valor = nv;
	}
	
	
}
