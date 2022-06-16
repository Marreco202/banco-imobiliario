package model;

import java.util.ArrayList;

import exeptions.ProibidoConstruir;
import exeptions.ValoresAluguelIncorreto;
import exeptions.casaAtualNaoECompravel;

/*
 * 
 * 
 * */


class Board {
	private static Board board = null;
	
	private  int tamanhoTabuleiro = 40;
	
	public  int getTamanhoTabuleiro() {
		return tamanhoTabuleiro;
	}
	
	public  Tile[] tabuleiro = new Tile[40];
	
	public Board() throws ValoresAluguelIncorreto{
		
		/* ESCALAS DOS ALUGUEIS*/

		int[] a1 = {18,90,250,700,875,1050}; //9 de julho, reboucas
		int[] a2 = {20,100,300,750,925,1100}; //brig faria lima
		int[] a3 = {4,20,60,180,320,450};// nossa senhora de copacabana
		int[] a4 = {2,10,30,90,160,250}; //presidente vargas
		int[] a5 = {6,30,90,270,400,500}; //Leblon, botafogo
		int[] a6 = {22,110,330,800,975,1150}; //brooklin , copacabana
		int[] a7 = {24,120,360,850,1025,1200}; //jardim paulista
		int[] a8 = {26,130,390,900,1100,1275}; //ipanema , av atlantica
		int[] a9 = {28,150,450,1000,1200,1400}; //vieira souto
		int[] a10 = {10,50,150,450,625,750}; //jardim europa, av paulista
		int[] a11 = {8,40,100,300,450,600}; //flamengo
		int[] a12 = {50,200,600,1400,1700,2000}; //morumbi
		int[] a13 = {35,175,500,1100,1300,1500}; //interlagos
		int[] a14 = {14,70,200,550,750,950}; //av. pacaembu, rua augusta
		int[] a15 = {16,80,220,600,800,1000}; //av. europa
		int[] a16 = {12,60,180,500,700,900}; //av brasil
		
		//criando o tabuleiro
		// aresta inferior
		
		tabuleiro[0] = new SpecialTile(0,"Ponto de partida",TilesEspeciais.pontoDePartida); 
		tabuleiro[1] = new Territorio(1,"Leblon",Cor.rosa,100, 50,a5);
		tabuleiro[2] = new SpecialTile(2, "Sorte ou revés", TilesEspeciais.sorteOuReves);
		tabuleiro[3] = new Territorio(3,"Av. Presidente Vargas",Cor.rosa,60,50,a4);
		tabuleiro[4] = new Territorio(4,"Av. Nossa Senhora de Copacabana",Cor.rosa,60,50,a3);
		tabuleiro[5] = new Companhia(5,"Companhia Ferroviaria",200,50);
		tabuleiro[6] = new Territorio(6,"Av. Brigadero Faria Lima",Cor.azul,240,150,a2);
		tabuleiro[7] = new Companhia(7,"Companhia de Viação",150,50);
		tabuleiro[8] = new Territorio(8,"Av. Rebouças",Cor.azul,220,150,a1);
		tabuleiro[9] = new Territorio(9,"Av. 9 de Julho",Cor.azul,220,150,a1);
		tabuleiro[10] = new SpecialTile(10, "Prisao", TilesEspeciais.Prisao);
		
		//aresta esquerda
		tabuleiro[11] = new Territorio(11,"Av. Europa",Cor.vinho,200,100,a15);
		tabuleiro[12] = new SpecialTile(12, "Sorte ou reves", TilesEspeciais.sorteOuReves);
		tabuleiro[13] = new Territorio(13,"Rua Augusta",Cor.vinho,180,100,a14);
		tabuleiro[14] = new Territorio(14,"Av. Pacaembú",Cor.vinho,180,100,a14);
		tabuleiro[15] = new Companhia(15,"Companhia de Taxi",150,40); //taxi
		tabuleiro[16] = new SpecialTile(16, "Sorte ou reves", TilesEspeciais.sorteOuReves);
		tabuleiro[17] = new Territorio(17,"Interlagos",Cor.laranja,350,200,a13);
		tabuleiro[18] = new SpecialTile(18,"Lucros e dividendos", TilesEspeciais.lucrosEDividendos);
		tabuleiro[19] = new Territorio(19,"Morumbi",Cor.laranja,400,200,a12);
		
		//aresta superior
		tabuleiro[20] = new SpecialTile(20,"Parada Livre", TilesEspeciais.paradaLivre);
		tabuleiro[21] = new Territorio(21,"Flamengo",Cor.vermelho,120,50,a11);
		tabuleiro[22] = new SpecialTile(22, "Sorte ou reves", TilesEspeciais.sorteOuReves);
		tabuleiro[23] = new Territorio(23,"Botafogo",Cor.vermelho,100,50,a5);
		tabuleiro[24] = new SpecialTile(24,"Imposto de renda", TilesEspeciais.impostoDeRenda);
		tabuleiro[25] = new Companhia(25,"Companhia de Navegação",150,25);
		tabuleiro[26] = new Territorio(26,"Av. Brasil",Cor.amarelo,160,100,a16);
		tabuleiro[27] = new SpecialTile(27, "Sorte ou reves", TilesEspeciais.sorteOuReves);
		tabuleiro[28] = new Territorio(28,"Av. Paulista",Cor.amarelo,140,100,a10);
		tabuleiro[29] = new Territorio(29,"Jardim Europa",Cor.amarelo,140,100,a10);
		tabuleiro[30] = new SpecialTile(30, "Va para a prisao", TilesEspeciais.vaParaAPrisao);

		// aresta direita
		tabuleiro[31] = new Territorio(31,"Copacabana",Cor.verde,260,150,a6);
		tabuleiro[32] = new Companhia(32,"Companhia de Aviação",200,50);
		tabuleiro[33] = new Territorio(33,"Av. Vieira Souto",Cor.verde,320,200,a9);
		tabuleiro[34] = new Territorio(34,"Av. Atlântica",Cor.verde,300,200,a8);
		tabuleiro[35] = new Companhia(35,"Companhia de taxi aéreo",200,50);
		tabuleiro[36] = new Territorio(36,"Ipanema",Cor.verde,300,200,a8);
		tabuleiro[37] = new SpecialTile(37, "Sorte ou reves", TilesEspeciais.sorteOuReves);
		tabuleiro[38] = new Territorio(38,"Jardim Paulista",Cor.roxo,280,150,a7);
		tabuleiro[39] = new Territorio(39,"Brooklin",Cor.roxo,260,150,a6);
		
		
	}
	
	public static Board getBoard() {
		if(board == null) {
			try {
				board = new Board();
			} catch (ValoresAluguelIncorreto e) {
				System.out.println("Erro nos alugueis do tabuleiro");
				e.printStackTrace();
			}
		}
		return board;
	}
	
	public int getPosVaParaPrisao() {
		return 30;
	}
	
	public int getPosPrisao() {
		return 10;
	}

	public Player getDonoDePosicao(int pos) throws casaAtualNaoECompravel {
		if(tabuleiro[pos] instanceof Compravel) {
			if(((Compravel)tabuleiro[pos]).getProprietario() != null) {
				return ((Compravel)tabuleiro[pos]).getProprietario();
			}
		}
		throw new casaAtualNaoECompravel();
	}
	
	public int getQtdDeCasas(int pos) throws ProibidoConstruir {
		if(tabuleiro[pos] instanceof Territorio) {
			return ((Territorio)tabuleiro[pos]).getQtdCasas();
		}
		throw new ProibidoConstruir("");
	}
	
	public boolean getTemHotel(int pos) throws ProibidoConstruir {
		if(tabuleiro[pos] instanceof Territorio) {
			return ((Territorio)tabuleiro[pos]).getTemHotel();
		}
		throw new ProibidoConstruir("");
	}
	
	public ArrayList<Integer> getTodasPropriedadesDeJogador(Player jogador) {
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		for(int i=0; i<40; i++) {
			if(tabuleiro[i] instanceof Compravel) {
				if(((Compravel)tabuleiro[i]).getProprietario() == jogador) {
					retorno.add(i);
				}
			}
		}
		return retorno;
	}

	public boolean podeComprarPropriedade(int pos) {
		if(tabuleiro[pos] instanceof Compravel) {
			if(((Compravel)tabuleiro[pos]).getProprietario() == null) {
				return true;
			}
		}
		return false;
	}

	public int fortunaEmPropriedadesDePlayer(Player player) {
		ArrayList<Integer> propriedades = getTodasPropriedadesDeJogador(player);
		int soma = 0;
		for(int i=0; i<propriedades.size(); i++) {
			soma += ((Compravel) tabuleiro[(int)propriedades.get(i)]).getValorDeVenda();
		}
		System.out.println("FORTUNA " +soma);
		return soma;
	}
	
}
