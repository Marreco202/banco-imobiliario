package model;

import exeptions.ValoresAluguelIncorreto;

/*
 * 
 * 
 * */


public class Board {
	//Tabuleiro é um vetor circular de Casa
	// public void initBoard(): cria o vetor circular e seta cada casa individualmente
	// implementar array de cartas, e funcao para embaralhar as cartas
	// criar TAD de fila para a retirada de cartas
	private static int tamanhoTabuleiro = 40;
	
	public static int getTamanhoTabuleiro() {
		return tamanhoTabuleiro;
	}
	
	public static Tile[] tabuleiro = new Tile[40];
	int placeHolder = 200; // custo de todas as Territorioriedades enquanto nao temos os valores certinhos
	//talvez colocar o preco de cada uma baseada na cor?
	
	Board() throws ValoresAluguelIncorreto{
		//colocar novo parametro no contructor de Territorio para receber os nomes de cada casa.
		
		//contando cada casa de cada aresta, nas casas 3, 5 e 8 (quase) sempre tem algo que é diferente de Territorioriedades coloridas 
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
		
		tabuleiro[0] = new Tile(0,"Prisao"); //PRISAO 
		tabuleiro[1] = new Territorio("Av. 9 de Julho",Cor.azul,placeHolder,1,150,a1);
		tabuleiro[2] = new Territorio("Av. Reboucas",Cor.azul,placeHolder,2,150,a1);
		tabuleiro[3] = new Companhia("Companhia de Taxi",placeHolder,50,3); //Onibus
		tabuleiro[4] = new Territorio("Av. Brig Faria Lima",Cor.azul,placeHolder,4,150,a2);
		tabuleiro[5] = new Companhia("Companhia Ferroviaria",placeHolder,50,5); //Trem;
		tabuleiro[6] = new Territorio("Av. Nossa Senhora de Copacabana",Cor.rosa,placeHolder,6,50,a3);
				
		tabuleiro[7] = new Territorio("Av. Presidente Vargas",Cor.rosa,placeHolder,7,50,a4);
		//tabuleiro[8] = new Tile(8); // MUDAR PARA SorteOuReves()
		tabuleiro[9] = new Territorio("Leblon",Cor.rosa,placeHolder,9,50,a5);
		tabuleiro[10] =new Tile(10,"Ponto de Partida"); // Ponto de partida
		
			// aresta direita
		tabuleiro[11] = new Territorio("Brooklin",Cor.roxo,placeHolder,11,150,a6);
		tabuleiro[12] = new Territorio("Jardim Paulista",Cor.roxo,placeHolder,12,150,a7);
		//tabuleiro[13] = new Tile(13); // MUDAR PARA sorte ou reves;
		tabuleiro[14] = new Territorio("Ipanema",Cor.verde,placeHolder,14,200,a8);
		tabuleiro[15] = new Companhia("Companhia de taxi aéreo",placeHolder,50,15); //helicoptero
		tabuleiro[16] = new Territorio("Av. Atlantica",Cor.verde,placeHolder,16,200,a8);
		tabuleiro[17] = new Territorio("Av. Vieira Souto",Cor.verde,placeHolder,17,200,a9);
		tabuleiro[18] = new Companhia("Companhia de Aviação",placeHolder,50,18); //aviao
		tabuleiro[19] = new Territorio("Copacabana",Cor.verde,placeHolder,19,150,a6);
		tabuleiro[20] = new Tile(20,"Va para a Cadeia"); // va para a cadeia
		
			//aresta superior
		tabuleiro[21] = new Territorio("Jardim Europa",Cor.amarelo,placeHolder,21,100,a10);
		tabuleiro[22] = new Territorio("Av. Paulista",Cor.amarelo,placeHolder,22,100,a10);
		//sorte ou reves
		tabuleiro[24] = new Territorio("Av. Brasil",Cor.amarelo,placeHolder,24,100,a16);
		tabuleiro[25] = new Companhia("Companhia de Navegação",placeHolder,40,25); //barco
		tabuleiro[26] = new Tile(26,"$$"); //???
		tabuleiro[27] = new Territorio("Botafogo",Cor.vermelho,placeHolder,27,50,a5);
		//sorte ou reves
		tabuleiro[29] = new Territorio("Flamengo",Cor.vermelho,placeHolder,29,50,a11);
		tabuleiro[30] = new Tile(30,"Parada Livre"); //parada livre
		
		//aresta esquerda
		tabuleiro[31] = new Territorio("Morumbi",Cor.laranja,placeHolder,31,200,a12);
		tabuleiro[32] = new Tile(32,"$$"); //???
		tabuleiro[33] = new Territorio("Interlagos",Cor.laranja,placeHolder,33,200,a13);
				//sorte ou reves
		tabuleiro[35] = new Companhia("Companhia de Taxi",placeHolder,50,35); //taxi
		tabuleiro[36] = new Territorio("Av. Pacaembú",Cor.vinho,placeHolder,36,100,a14);
		tabuleiro[37] = new Territorio("Rua Augusta",Cor.vinho,placeHolder,37,100,a14);
				//sorte ou reves
		tabuleiro[39] = new Territorio("Av. Europa",Cor.vinho,placeHolder,39,100,a15);
	}
	
	
	
}
