package model;


public abstract class Tile{ 
	private int pos; 
	private String nome;
	
	
	public Tile(int pos, String nome) {
		this.pos = pos;
		this.nome = nome;
	}
	
	public int getPos() {
		return pos;
	}
	
	public String getNome() {
		return nome;
	}
}


