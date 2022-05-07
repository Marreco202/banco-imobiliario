package model;

import exeptions.ProibidoConstruir;
import exeptions.TerritorioJaPossuiDono;
import exeptions.ValoresAluguelIncorreto;

public class Territorio extends Tile {

	private Player proprietario;
	private String nome;
	private int valor;
	private Cor cor;
	private int qtdCasas;
	private boolean temHotel;
	private int custoPorConstrucao;
	private int[] valoresAluguel; /*valor aluguel com 0 casas -> valoresAluguel[0]
	 								valor aluguel com 1 casa -> valoresAluguel[1]
	 								valor aluguel com 2 casas -> valoresAluguel[2]
	 								valor aluguel com 3 casas -> valoresAluguel[3]
	 								valor aluguel com 4 casas -> valoresAluguel[4]
	 								valor aluguel com hotel -> valoresAluguel[5]*/
	
	
	public Territorio(int pos, String nome,  Cor cor, int valor, int custoPorConstrucao, int[] valoresAluguel) throws ValoresAluguelIncorreto {
		super(pos);
		proprietario = null;
		this.nome = nome;
		this.cor = cor;
		this.valor = valor;
		qtdCasas = 0;
		temHotel = false;
		this.custoPorConstrucao = custoPorConstrucao;
		registrarAluguel(valoresAluguel);
	}
	
	public void setNovoProprietario(Player novoPropritario) throws TerritorioJaPossuiDono {
		if(proprietario != null) {
			throw new TerritorioJaPossuiDono("Está tentando settar novo dono para territorio que ja possui dono.");
		}
		proprietario = novoPropritario;
	}
	
	public void venderParaOBanco() {
		qtdCasas = 0;
		temHotel = false;
		proprietario = null;
	}
	
	private void registrarAluguel(int[] valoresAluguel) throws ValoresAluguelIncorreto {
		if(valoresAluguel.length != 6) {
			throw new ValoresAluguelIncorreto("O numero de valores deve ser igual a 6(valor sem casa, valor para cada uma das 4 casas, valor para Hotel)");
		}
		this.valoresAluguel = valoresAluguel;
	}
	
	private int calculaValorAluguel() {
		if(temHotel == true) {
			return valoresAluguel[5];
		}
		return valoresAluguel[qtdCasas];
	}
	
	private int calculaValorDeVenda() {
		int valorDeVenda = valor;
		if(temHotel) {
			valorDeVenda += custoPorConstrucao;
		}
		valorDeVenda += qtdCasas*custoPorConstrucao;
		return (int) (0.9*valorDeVenda);
	}
	
	public boolean podeConstruirCasa() {
		if(qtdCasas < 4) {
			return true;
		}
		return false;
	}
	
	public void construirCasa() throws ProibidoConstruir {
		if(!podeConstruirCasa()) {
			throw new ProibidoConstruir("Ja existe o numero máximo de casas nessa territorio!");
		}
		qtdCasas++;
	}
	
	public boolean podeConstruirHotel() {
		if(temHotel == false) {
			return true;
		}
		return false;
	}
	
	public void construirHotel() throws ProibidoConstruir {
		if(!podeConstruirHotel()) {
			throw new ProibidoConstruir("Já existe um hotel nessa territorio!");
		}
		temHotel = true;
	}
	
	
	///////////// GETTERS //////////////////////
	
	public int getValorAtualAluguel() {
		return calculaValorAluguel();
	}
	
	public int getValorDeVenda() {
		return calculaValorDeVenda();		
	}
	
	public int getValor() {
		return valor;
	}
	
	public int getCustoPorConstrucao() {
		return custoPorConstrucao;
	}
	
	public Player getProprietario() {
		return proprietario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int[] getValoresAluguel() {
		return valoresAluguel;
	}
	
	public int getQtdCasas() {
		return qtdCasas;
	}
	
	public boolean getTemHotel() {
		return temHotel;
	}
	
	public Cor getCor() {
		return cor;
	}
}
