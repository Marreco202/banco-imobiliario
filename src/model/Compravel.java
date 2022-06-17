package model;

import java.io.File;

import exeptions.PropriedadeJaPossuiDono;

abstract class Compravel extends Tile{
	
	private Player proprietario;
	private int valor;
	
	public Compravel(int pos,  String nome, int valor, String imagePath) {
		super(pos,nome, imagePath);
		this.valor = valor;
		proprietario = null;
	}
	
	public String salvar() {
		if(proprietario != null) {
			return ""+proprietario.getIdJogador();
		}
		return "-1";
	}
	
	public abstract void carregarArquivo(File file);
	
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
	
	public int getValor() {
		return valor;
	}
	
	public abstract int getValorDeVenda();


}
