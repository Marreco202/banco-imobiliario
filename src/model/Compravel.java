package model;

import exeptions.PropriedadeJaPossuiDono;

public abstract class Compravel extends Tile{
	
	private Player proprietario;
	protected String nome;
	protected int valor;
	
	public Compravel(int pos,  String nome, int valor) {
		super(pos,nome);
		this.valor = valor;
		proprietario = null;
	}
	
	public void setNovoProprietario(Player novoPropritario) throws PropriedadeJaPossuiDono {
		if(proprietario != null) {
			throw new PropriedadeJaPossuiDono("Está tentando settar novo dono para territorio que ja possui dono.");
		}
		proprietario = novoPropritario;
	}
	
	public void venderParaOBanco() {
		proprietario = null;
	}
	
	public Player getProprietario() {
		return proprietario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getValor() {
		return valor;
	}

}
