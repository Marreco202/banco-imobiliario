package model;

import java.io.File;

import exeptions.PropriedadeJaPossuiDono;

class Companhia extends Compravel {
	
	private int taxa;
	
	public Companhia(int pos, String nome,int valor, int taxa) {
		super(pos, nome, valor, "./img/companhias/"+nome+".png");	
		this.taxa = taxa;
	}
	public int getValorDeVenda() {
		return (int) (0.9* this.getValor());
	}
	public int getTaxaASerPaga(int valorDosDadosTirados) {
		return taxa*valorDosDadosTirados;
	}
	
	@Override
	public void carregarArquivo(File file) {
		String[] items = Model.getModel().lerLinha(file).split("\\|");
		int id = Integer.parseInt(items[0]);
		try {
			if(id == -1) {
				this.setNovoProprietario(null);
			}else {
				this.setNovoProprietario(Player.getPlayerList()[Integer.parseInt(items[0])]);
			}
		} catch (PropriedadeJaPossuiDono e) {
		}
	}
}
