package model;

public class Prop extends Tile {

	private int valor;
	private Cor cor;
	private int qtdCasas;
	private boolean temHotel;
	
	public Prop(Cor c, int nv) {
		cor = c;
		valor = nv;
		qtdCasas = 0;
		temHotel = false;
	}
	
	public void setCusto(int nv) {
		valor = nv;
	}
	
	
}
