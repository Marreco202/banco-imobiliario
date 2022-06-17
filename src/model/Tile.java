package model;

abstract class Tile{ 
	
	private int pos; 
	private String nome;
	private String imagePath=null;
	
	public Tile(int pos, String nome) {
		this.pos = pos;
		this.nome = nome;
	}
	
	public Tile(int pos, String nome, String imagePath) {
		this.pos = pos;
		this.nome = nome;
		this.imagePath = imagePath;
	}
	
	public int getPos() {
		return pos;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
}


