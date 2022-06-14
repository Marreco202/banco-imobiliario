package model;

import exeptions.JogadorEDonoDaPropriedade;
import exeptions.JogadorNaoEDonoDaPropriedade;
import exeptions.PosicoesConflitantes;
import exeptions.ProibidoConstruir;
import exeptions.PropriedadeJaPossuiDono;
import exeptions.SaldoBancoInsuficiente;
import exeptions.SaldoJogadorInsuficiente;

/*
 * Classe que vai automatizar tudo pra gente
 * */

class Bank { 
	
	private int saldo;
	private static Bank bank = null;
	
	private Bank() {
		saldo = 200000;
	}
	
	public static Bank getBank() {
		if(bank == null) {
			bank = new Bank();
			return bank;
		}
		return bank;
	}
	
	private boolean podeComprar(Player comprador, Compravel propriedade) throws SaldoJogadorInsuficiente, PropriedadeJaPossuiDono, PosicoesConflitantes {
		if(comprador.getSaldo() < propriedade.getValor()) {
			throw new SaldoJogadorInsuficiente("O saldo do jogador é insuficiente para comprar essa propriedade!", comprador.getCor());
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
			comprador.setAcabouDeComprar(true);
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
			return ((Territorio) propriedade).getAluguelASerPago();
		}else if(propriedade instanceof Companhia) {
			int soma = Player.getJogadorDaVez().getSomaDadosDaVez();
			return ((Companhia) propriedade).getTaxaASerPaga(soma);
		}
		return -1; //criar uma exception
	}
	
	private boolean podePagarAluguel(Player devedor, Compravel propriedade) throws SaldoJogadorInsuficiente, PosicoesConflitantes, JogadorEDonoDaPropriedade {
		if(devedor.getSaldo() < descobreAluguelASerPago(propriedade)) {
			throw new SaldoJogadorInsuficiente("O saldo do jogador é insuficiente para pagar o aluguel!", devedor.getCor());
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
			throw new SaldoJogadorInsuficiente("O saldo do jogador é insuficiente para pagar o aluguel!", dono.getCor());
		}
		if(dono.getPos() != propriedade.getPos()) {
			throw new PosicoesConflitantes("A posicao do jogador nao é a mesma da proprieadade que se quer construir.");
		}
		return true;
	}
	
	public void construirPropriedade(Player dono, Territorio propriedade) throws SaldoJogadorInsuficiente, PosicoesConflitantes, ProibidoConstruir {
		if(podeConstruir(dono, propriedade) && (propriedade.podeConstruirCasa() || propriedade.podeConstruirHotel())) {
			int custo = propriedade.getCustoPorConstrucao();
			propriedade.construir();
			dono.pagarValor(custo);
			saldo += custo;
		}
	}
	
	public void jogadorReceberDeJogador(Player recebedor, Player devedor, int valor) throws SaldoJogadorInsuficiente {
		devedor.pagarValor(valor);
		recebedor.receberValor(valor);
	}
	
	public void receberDeTodosOsJogadorer(Player recebedor, int valor) throws SaldoJogadorInsuficiente {
		Player[] playerList = Player.getLista();
		for(int i = 0; i<Player.getQtdDeJogadores();i++) { 
			if(playerList[i] != null && playerList[i] != recebedor){ 
				jogadorReceberDeJogador(recebedor, playerList[i], valor);
				i++;
			}
		}
	}
	
	public void deposito(Player p, int valor) throws SaldoJogadorInsuficiente {
		p.pagarValor(valor);
		saldo+=valor;
	}
	
	public void saque(Player p,int valor) throws SaldoBancoInsuficiente {
		if(saldo<valor) {
			throw new SaldoBancoInsuficiente();
		}
		saldo-=valor;
		p.receberValor(valor);
	}
	
	public int getSaldo() {
		return saldo;
	}

}
