package model;

import exeptions.JogadorEDonoDaPropriedade;
import exeptions.JogadorNaoEDonoDaPropriedade;
import exeptions.PosicoesConflitantes;
import exeptions.PropriedadeJaPossuiDono;
import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;

/*
 * Classe que vai automatizar tudo pra gente
 * */

public class Bank { 
	
	private int saldo;
	
	public Bank() {
		saldo = 200000;
	}
	
	private boolean podeComprar(Player comprador, Compravel propriedade) throws SaldoJogadorInsuficiente, PropriedadeJaPossuiDono, PosicoesConflitantes {
		if(comprador.getSaldo() < propriedade.getValor()) {
			throw new SaldoJogadorInsuficiente("O saldo do jogador é insuficiente para comprar essa propriedade!");
		}
		if(propriedade.getProprietario() != null) {
			throw new PropriedadeJaPossuiDono("A propriedade já possui dono");
		}
		if(comprador.getPos() != propriedade.getPos()) {
			throw new PosicoesConflitantes("A posicao do jogador que deseja comprar não é a mesma que a da propriade a ser comprada");
		}
		return true;
	}
	
	public void realizarCompraDePropriedade(Player comprador, Compravel propriedade) throws SaldoJogadorInsuficiente, PropriedadeJaPossuiDono, PosicoesConflitantes {
		if(podeComprar(comprador, propriedade)) {
			comprador.pagarValor(propriedade.getValor());
			saldo += propriedade.getValor();
			propriedade.setNovoProprietario(comprador);
		}
	}
	
	private boolean podeVender(Player vendedor, Compravel propriedade) throws JogadorNaoEDonoDaPropriedade, SaldoBancoInsuficiente {
		if(propriedade.getProprietario() != vendedor) {
			throw new JogadorNaoEDonoDaPropriedade("Jogador esta tentando vender propriedade que nao é dele.");
		}
		if(saldo < propriedade.getValorDeVenda()) {
			throw new SaldoBancoInsuficiente("Saldo do banco é insuficiente para comprar a propriedade.");
		}
		return true;
	}
	
	public void realizarVendaDePropriedade(Player vendedor, Compravel propriedade) throws JogadorNaoEDonoDaPropriedade, SaldoBancoInsuficiente {
		if(podeVender(vendedor, propriedade)) {
			propriedade.venderParaOBanco();
			saldo -= propriedade.getValorDeVenda();
			vendedor.receberValor(propriedade.getValorDeVenda());
		}
	}
	
	private int descobreAluguelASerPago(Compravel propriedade) {
		if(propriedade instanceof Territorio) {
			return ((Territorio) propriedade).getValorAtualAluguel();
		}else if(propriedade instanceof Companhia) {
			return ((Companhia) propriedade).getTaxaASerPaga(Model.getSomaDadosDaVez());
		}
		return -1; //criar uma exception
	}
	
	private boolean podePagarAluguel(Player devedor, Compravel propriedade) throws SaldoJogadorInsuficiente, PosicoesConflitantes, JogadorEDonoDaPropriedade {
		if(devedor.getSaldo() < descobreAluguelASerPago(propriedade)) {
			throw new SaldoJogadorInsuficiente("O saldo do jogador é insuficiente para pagar o aluguel!");
		}
		if(devedor.getPos() != propriedade.getPos()) {
			throw new PosicoesConflitantes("A posicao do jogador nao é a mesma da proprieadade que se deve pagar o aluguel.");
		}
		if(devedor == propriedade.getProprietario()) {
			throw new JogadorEDonoDaPropriedade("O jogador nao pode pagar aluguel para si mesmo.");
		}
		return true;
	}
	
	public void realizarPagamentoDeAluguel(Player devedor, Compravel propriedade) throws SaldoJogadorInsuficiente, PosicoesConflitantes, JogadorEDonoDaPropriedade {
		if(podePagarAluguel(devedor, propriedade)) {
			int valorAluguel = descobreAluguelASerPago(propriedade);
			devedor.pagarValor(valorAluguel);
			propriedade.getProprietario().receberValor(valorAluguel);
		}
	}
	
	private boolean podeConstruir(Player dono, Territorio propriedade) throws SaldoJogadorInsuficiente, PosicoesConflitantes {
		if(dono.getSaldo() < propriedade.getCustoPorConstrucao()) {
			throw new SaldoJogadorInsuficiente("O saldo do jogador é insuficiente para pagar o aluguel!");
		}
		if(dono.getPos() != propriedade.getPos()) {
			throw new PosicoesConflitantes("A posicao do jogador nao é a mesma da proprieadade que se quer construir.");
		}
		return true;
	}
	
	public void construirCasa(Player dono, Territorio propriedade) throws SaldoJogadorInsuficiente, PosicoesConflitantes {
		if(podeConstruir(dono, propriedade)) {
			int custo = propriedade.getCustoPorConstrucao();
			dono.pagarValor(custo);
			saldo += custo;
		}
	}
	
	public void deposito(int valor) {
		saldo+=valor;
	}
	
	public int saque(int valor) {
		saldo-=valor;
		return valor;
	}

}
