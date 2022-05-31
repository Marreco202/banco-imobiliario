package model;

import static org.junit.Assert.*;

import org.junit.Test;

import exeptions.JogadorEDonoDaPropriedade;
import exeptions.JogadorNaoEDonoDaPropriedade;
import exeptions.PosicoesConflitantes;
import exeptions.PropriedadeJaPossuiDono;
import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;

class TestePrimInt {

	@Test
	public void testaCompraDePropriedade() throws SaldoJogadorInsuficiente, PropriedadeJaPossuiDono, PosicoesConflitantes {
		
		Compravel prop = (Compravel) Board.getBoard().tabuleiro[1];
		Player p = new Player(1);
		Bank b = new Bank();
		int saldoInicialJogador = p.getSaldo();
		int saldoInicialBanco = b.getSaldo();
		
		b.realizarCompraDePropriedade(p, prop);
		assertEquals(p,prop.getProprietario());
		assertEquals(saldoInicialJogador - prop.getValor(),p.getSaldo());
		assertEquals(saldoInicialBanco +prop.getValor(),b.getSaldo());
		
	}
	
	@Test
	public void testaVendaDePropriedade() throws SaldoJogadorInsuficiente, PropriedadeJaPossuiDono, PosicoesConflitantes, JogadorNaoEDonoDaPropriedade, SaldoBancoInsuficiente {
		
		Compravel prop = (Compravel) Board.getBoard().tabuleiro[1];
		Player p = new Player(1);
		Bank b = new Bank();
		int saldoInicialJogador = p.getSaldo();
		int saldoInicialBanco = b.getSaldo();
		
		b.realizarCompraDePropriedade(p, prop);
		b.realizarVendaDePropriedade(p, prop);
		
		assertEquals(null,prop.getProprietario());
		assertEquals(saldoInicialJogador - prop.getValor() + prop.getValorDeVenda(), p.getSaldo());
		assertEquals(saldoInicialBanco + prop.getValor() - prop.getValorDeVenda(), b.getSaldo());
		
	}
	
	@Test
	public void realizarPagamentoDeAluguel() throws SaldoJogadorInsuficiente, PropriedadeJaPossuiDono, PosicoesConflitantes, JogadorEDonoDaPropriedade {
		
		Compravel prop = (Compravel) Board.getBoard().tabuleiro[1];
		Player vaiPagar = new Player(1);
		Player dono = new Player(1);
		Bank b = new Bank();
		
		
		int saldoInicialDono = dono.getSaldo();
		int saldoInicialVaiPagar =vaiPagar.getSaldo(); 
		
		b.realizarCompraDePropriedade(dono, prop);
		b.realizarPagamentoDeAluguel(vaiPagar, prop);
		assertEquals(saldoInicialDono + ((Territorio) prop).getAluguelASerPago(), dono.getSaldo());
		assertEquals(saldoInicialVaiPagar - ((Territorio)prop).getAluguelASerPago(), vaiPagar.getSaldo());
		
	}
}
