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
	public static Tile[] tabuleiro = new Tile[40];
	
	int placeHolder = 200; // custo de todas as propriedades enquanto nao temos os valores certinhos
	//talvez colocar o preco de cada uma baseada na cor?
	
	Board(){
		
		//fazer um loop para a criacao do tabuleiro
		//colocar novo parametro no contructor de Prop para receber os nomes de cada casa.
		
		//contando cada casa de cada aresta, nas casas 3, 5 e 8 (quase) sempre tem algo que é diferente de propriedades coloridas 
		
		
		//criando o tabuleiro
			// aresta inferior
		tabuleiro[0] = new Prison();
		tabuleiro[1] = new Prop(Cor.azul,placeHolder);
		tabuleiro[2] = new Prop(Cor.azul,placeHolder);
		tabuleiro[3] = new Tile(); //Onibus
		tabuleiro[4] = new Prop(Cor.azul,placeHolder);
		tabuleiro[5] = new Tile(); //Trem
		tabuleiro[6] = new Prop(Cor.rosa,placeHolder);
		tabuleiro[7] = new Prop(Cor.rosa,placeHolder);
		tabuleiro[8] = new Tile(); // MUDAR PARA SorteOuReves()
		tabuleiro[9] = new Prop(Cor.rosa,placeHolder);
		tabuleiro[10] = new Tile(); // Ponto de partida
		
			// aresta direita
		tabuleiro[11] = new Prop(Cor.roxo,placeHolder);
		tabuleiro[12] = new Prop(Cor.roxo,placeHolder);
		tabuleiro[13] = new Tile(); // MUDAR PARA sorte ou reves;
		tabuleiro[14] = new Prop(Cor.verde,placeHolder);
		tabuleiro[15] = new Tile(); //helicoptero
		tabuleiro[16] = new Prop(Cor.verde,placeHolder);
		tabuleiro[17] = new Prop(Cor.verde,placeHolder);
		tabuleiro[18] = new Prop(Cor.especial,placeHolder); //aviao
		tabuleiro[19] = new Prop(Cor.verde,placeHolder);
		tabuleiro[20] = new Tile(); // va para a cadeia
		
			//aresta superior
		tabuleiro[21] = new Prop(Cor.amarelo,placeHolder);
		tabuleiro[22] = new Prop(Cor.amarelo,placeHolder);
		
		tabuleiro[24] = new Prop(Cor.amarelo,placeHolder);
		tabuleiro[25] = new Prop(Cor.especial,placeHolder); //barco
		tabuleiro[26] = new Tile(); //???
		tabuleiro[27] = new Prop(Cor.vermelho,placeHolder);
		tabuleiro[28] = new Prop(Cor.especial,placeHolder);
		tabuleiro[29] = new Prop(Cor.vermelho,placeHolder);
		tabuleiro[30] = new Tile(); //parada livre
		
			//aresta esquerda
		tabuleiro[31] = new Prop(Cor.laranja,placeHolder);
		tabuleiro[32] = new Tile(); //???
		tabuleiro[33] = new Prop(Cor.laranja,placeHolder);
		tabuleiro[34] = new Tile(); //sorte ou reves
		tabuleiro[35] = new Prop(Cor.especial,placeHolder); //taxi
		tabuleiro[36] = new Prop(Cor.vinho,placeHolder);
		tabuleiro[37] = new Prop(Cor.vinho,placeHolder);
		tabuleiro[38] = new Prop(Cor.especial,placeHolder); //sorte ou reves
		tabuleiro[39] = new Prop(Cor.vinho,placeHolder);
	}
	
	
	
}
