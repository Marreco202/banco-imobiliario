package model;

public class Companhia extends Compravel {
	
	private int taxa;
	
	public Companhia(int pos, String nome,int valor, int taxa) {
		super(pos, nome, valor);	
		this.taxa = taxa;
	}
	public int getValorDeVenda() {
		return (int) (0.9* this.getValor());
	}
	public int getTaxaASerPaga(int valorDosDadosTirados) {
		return taxa*valorDosDadosTirados;
	}
}
