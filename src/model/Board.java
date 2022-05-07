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
	public static Prop[] tabuleiro = new Prop[40];
	
	int placeHolder = 200; // custo de todas as propriedades enquanto nao temos os valores certinhos
	//talvez colocar o preco de cada uma baseada na cor?
	
	Board(){
		
		//fazer um loop para a criacao do tabuleiro
		//colocar novo parametro no contructor de Prop para receber os nomes de cada casa.
		
		//contando cada casa de cada aresta, nas casas 3, 5 e 8 (quase) sempre tem algo que é diferente de propriedades coloridas 
		
		
		//criando o tabuleiro
			// aresta inferior
		
		tabuleiro[0] = (Prop) new Tile(); //PRISAO 
		tabuleiro[1] = new Prop("Av. 9 de Julho",Cor.azul,placeHolder);
		tabuleiro[2] = new Prop("Av. Reboucas",Cor.azul,placeHolder);
		tabuleiro[3] = new Prop("Companhia de Taxi", Cor.especial,placeHolder); //Onibus
		tabuleiro[4] = new Prop("Av. Brig Faria Lima",Cor.azul,placeHolder);
		tabuleiro[5] = new Prop("Companhia Ferroviaria",Cor.especial,placeHolder); //Trem;
		tabuleiro[6] = new Prop("Av. Nossa Senhora de Copacabana",Cor.rosa,placeHolder);
				
		tabuleiro[7] = new Prop("Av. Presidente Vargas",Cor.rosa,placeHolder);
		tabuleiro[8] = (Prop) new Tile(); // MUDAR PARA SorteOuReves()
		tabuleiro[9] = new Prop("Leblon",Cor.rosa,placeHolder);
		tabuleiro[10] = (Prop) new Tile(); // Ponto de partida
		
			// aresta direita
		tabuleiro[11] = new Prop("Brooklin",Cor.roxo,placeHolder);
		tabuleiro[12] = new Prop("Jardim Paulista",Cor.roxo,placeHolder);
		tabuleiro[13] = (Prop) new Tile(); // MUDAR PARA sorte ou reves;
		tabuleiro[14] = new Prop("Ipanema",Cor.verde,placeHolder);
		tabuleiro[15] = new Prop("Companhia de taxi aéreo",Cor.especial, placeHolder); //helicoptero
		tabuleiro[16] = new Prop("Av. Atlantica",Cor.verde,placeHolder);
		tabuleiro[17] = new Prop("Av. Vieira Souto",Cor.verde,placeHolder);
		tabuleiro[18] = new Prop("Companhia de Aviação",Cor.especial,placeHolder); //aviao
		tabuleiro[19] = new Prop("Copacabana",Cor.verde,placeHolder);
		tabuleiro[20] = (Prop) new Tile(); // va para a cadeia
		
			//aresta superior
		tabuleiro[21] = new Prop("Jardim Europa",Cor.amarelo,placeHolder);
		tabuleiro[22] = new Prop("Av. Paulista",Cor.amarelo,placeHolder);
		//sorte ou reves
		tabuleiro[24] = new Prop("Av. Brasil",Cor.amarelo,placeHolder);
		tabuleiro[25] = new Prop("Companhia de Navegação",Cor.especial,placeHolder); //barco
		tabuleiro[26] = (Prop) new Tile(); //???
		tabuleiro[27] = new Prop("Botafogo",Cor.vermelho,placeHolder);
		//sorte ou reves
		tabuleiro[29] = new Prop("Flamengo",Cor.vermelho,placeHolder);
		tabuleiro[30] = (Prop) new Tile(); //parada livre
		
			//aresta esquerda
		tabuleiro[31] = new Prop("Morumbi",Cor.laranja,placeHolder);
		tabuleiro[32] = (Prop) new Tile(); //???
		tabuleiro[33] = new Prop("Interlagos",Cor.laranja,placeHolder);
		//sorte ou reves
		tabuleiro[35] = new Prop("Companhia de Taxi",Cor.especial,placeHolder); //taxi
		tabuleiro[36] = new Prop("Av. Pacaembú",Cor.vinho,placeHolder);
		tabuleiro[37] = new Prop("Rua Augusta",Cor.vinho,placeHolder);
		//sorte ou reves
		tabuleiro[39] = new Prop("Av. Europa",Cor.vinho,placeHolder);
	}
	
	
	
}
