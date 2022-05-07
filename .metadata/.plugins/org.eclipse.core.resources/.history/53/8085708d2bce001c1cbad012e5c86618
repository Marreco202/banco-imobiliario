package model;

/*
 * 
 * 
 * */


public class Board {
	//Tabuleiro é um vetor circular de Casa
	// public void initBoard(): cria o vetor circular e seta cada casa individualmente
	// implementar array de cartas, e funcao para embaralhar as cartas
	// criar TAD de fila para a retirada de cartas
	public static Territorio[] tabuleiro = new Territorio[40];
	
	int placeHolder = 200; // custo de todas as Territorioriedades enquanto nao temos os valores certinhos
	//talvez colocar o preco de cada uma baseada na cor?
	
	Board(){
		
		//fazer um loop para a criacao do tabuleiro
		//colocar novo parametro no contructor de Territorio para receber os nomes de cada casa.
		
		//contando cada casa de cada aresta, nas casas 3, 5 e 8 (quase) sempre tem algo que é diferente de Territorioriedades coloridas 
		
		
		//criando o tabuleiro
			// aresta inferior
		
		tabuleiro[0] = (Territorio) new Tile(); //PRISAO 
		tabuleiro[1] = new Territorio("Av. 9 de Julho",Cor.azul,placeHolder);
		tabuleiro[2] = new Territorio("Av. Reboucas",Cor.azul,placeHolder);
		tabuleiro[3] = new Territorio("Companhia de Taxi", Cor.especial,placeHolder); //Onibus
		tabuleiro[4] = new Territorio("Av. Brig Faria Lima",Cor.azul,placeHolder);
		tabuleiro[5] = new Territorio("Companhia Ferroviaria",Cor.especial,placeHolder); //Trem;
		tabuleiro[6] = new Territorio("Av. Nossa Senhora de Copacabana",Cor.rosa,placeHolder);
				
		tabuleiro[7] = new Territorio("Av. Presidente Vargas",Cor.rosa,placeHolder);
		tabuleiro[8] = (Territorio) new Tile(); // MUDAR PARA SorteOuReves()
		tabuleiro[9] = new Territorio("Leblon",Cor.rosa,placeHolder);
		tabuleiro[10] = (Territorio) new Tile(); // Ponto de partida
		
			// aresta direita
		tabuleiro[11] = new Territorio("Brooklin",Cor.roxo,placeHolder);
		tabuleiro[12] = new Territorio("Jardim Paulista",Cor.roxo,placeHolder);
		tabuleiro[13] = (Territorio) new Tile(); // MUDAR PARA sorte ou reves;
		tabuleiro[14] = new Territorio("Ipanema",Cor.verde,placeHolder);
		tabuleiro[15] = new Territorio("Companhia de taxi aéreo",Cor.especial, placeHolder); //helicoptero
		tabuleiro[16] = new Territorio("Av. Atlantica",Cor.verde,placeHolder);
		tabuleiro[17] = new Territorio("Av. Vieira Souto",Cor.verde,placeHolder);
		tabuleiro[18] = new Territorio("Companhia de Aviação",Cor.especial,placeHolder); //aviao
		tabuleiro[19] = new Territorio("Copacabana",Cor.verde,placeHolder);
		tabuleiro[20] = (Territorio) new Tile(); // va para a cadeia
		
			//aresta superior
		tabuleiro[21] = new Territorio("Jardim Europa",Cor.amarelo,placeHolder);
		tabuleiro[22] = new Territorio("Av. Paulista",Cor.amarelo,placeHolder);
		//sorte ou reves
		tabuleiro[24] = new Territorio("Av. Brasil",Cor.amarelo,placeHolder);
		tabuleiro[25] = new Territorio("Companhia de Navegação",Cor.especial,placeHolder); //barco
		tabuleiro[26] = (Territorio) new Tile(); //???
		tabuleiro[27] = new Territorio("Botafogo",Cor.vermelho,placeHolder);
		//sorte ou reves
		tabuleiro[29] = new Territorio("Flamengo",Cor.vermelho,placeHolder);
		tabuleiro[30] = (Territorio) new Tile(); //parada livre
		
			//aresta esquerda
		tabuleiro[31] = new Territorio("Morumbi",Cor.laranja,placeHolder);
		tabuleiro[32] = (Territorio) new Tile(); //???
		tabuleiro[33] = new Territorio("Interlagos",Cor.laranja,placeHolder);
		//sorte ou reves
		tabuleiro[35] = new Territorio("Companhia de Taxi",Cor.especial,placeHolder); //taxi
		tabuleiro[36] = new Territorio("Av. Pacaembú",Cor.vinho,placeHolder);
		tabuleiro[37] = new Territorio("Rua Augusta",Cor.vinho,placeHolder);
		//sorte ou reves
		tabuleiro[39] = new Territorio("Av. Europa",Cor.vinho,placeHolder);
	}
	
	
	
}
