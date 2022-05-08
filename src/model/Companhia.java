package model;

import exeptions.PropriedadeJaPossuiDono;

public class Companhia extends Compravel {
	
	private int taxa;
	
	public Companhia(String nome,int valor, int taxa, int pos) {
		super(pos, nome, valor);
		//this.nome = nome;
		this.taxa = taxa;
		//this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getValor() {
		return valor;
	}
	
	public int gerValorDeVenda() {
		return (int) (0.9*this.valor);
	}
	
	public int getTaxaASerPaga(int valorDosDadosTirados) {
		return taxa*valorDosDadosTirados;
	}
	
}
