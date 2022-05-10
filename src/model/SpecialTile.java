package model;

import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;

public class SpecialTile extends Tile {
	
	TilesEspeciais tipoDeTileEspecial;

	// pontoDePartida, sorteOuReves, Prisao, lucrosEDividendos, impostoDeRenda, vaParaAPrisao, paradaLivre;
	
	public SpecialTile(int pos, String nome, TilesEspeciais tipoDeTileEspecial) {
		super(pos, nome);
		this.tipoDeTileEspecial = tipoDeTileEspecial;
	}
	
	public void acaoSpecialTile(Player jogador, Bank banco, Board tabuleiro) throws SaldoBancoInsuficiente, SaldoJogadorInsuficiente {
		if(this.tipoDeTileEspecial == TilesEspeciais.pontoDePartida) {
			banco.saque(jogador, 200);
		}else if(this.tipoDeTileEspecial == TilesEspeciais.sorteOuReves) {
			// nao decidi ainda a melhor forma de implementar esse
		}else if(this.tipoDeTileEspecial == TilesEspeciais.lucrosEDividendos) {
			banco.saque(jogador, 200);
		}else if(this.tipoDeTileEspecial == TilesEspeciais.impostoDeRenda) {
			banco.deposito(jogador, 200);
		}else if(this.tipoDeTileEspecial == TilesEspeciais.vaParaAPrisao) {
			jogador.vaParaAPrisao(tabuleiro);
		}
	}
	
	public TilesEspeciais getTipoDeTileEspecial() {
		return tipoDeTileEspecial;
	}

	public boolean temAcaoEspecial() {
		if(tipoDeTileEspecial == TilesEspeciais.Prisao || tipoDeTileEspecial == TilesEspeciais.paradaLivre) {
			return false;
		}else {
			return true;
		}
	}
	
}
