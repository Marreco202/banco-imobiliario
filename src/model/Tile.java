package model;


public abstract class Tile{ // adapatar para uma classe abstrata
	private int pos; //numero da casa do tabuleiro
	
	
	public Tile(int _pos) {
		pos = _pos;
	}
	
	public int getPos() {
		return pos;
	}
}


