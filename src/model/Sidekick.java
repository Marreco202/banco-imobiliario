package model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Sidekick {

	
	
	
	public static void readSaveFile() {
		
		Model model = Model.getModel();
		Player playerList[] = null;
		
		int qtdPlayer = 0;
		boolean devMode = false; 
		
		boolean lever1 = false; //"alavanca" que proca assim que a linha de quantidade de jogadores eh lida
		boolean lever2 = false; //"alavanca" que pega se o devMode esta ligado ou nao
		
		//elementos do player atualmente lido
		Color cor = null;
		int id = 0;
		int saldo= 0; 
		int pos= 0;
		boolean passeLivre = false;
		boolean estaPreso = false;
		
		boolean avancouTab = false;
		boolean jaConstruiu = false;
		int dadosIguaisSeguidos = 0;
		boolean tirouCarta = false;
		boolean estaFalido = false;
		boolean estaDevendoCarta = false;
		boolean estaDevendoAluguel = false;
		boolean estaDevendoImpostoDeRenda = false;
		
		
		try {
			File file = new File("./save_files/mySaveFileTeste.txt");
			Scanner scan = new Scanner(file);
			int contador = 0;
			int i = 0; //indice para percorrer a lista de jogadors
			
			while(scan.hasNextLine()) { //lendo o save file linha a linha
				String bruta = scan.nextLine();
				int ini = bruta.indexOf(":");//encontra oq ele tem q fatiar de fato
				int fin = bruta.indexOf(";");
				String content = bruta.substring(ini+1,fin); //conteudo de fato daquela linha
				
				if(lever1 == false) { //pega a quantidade de jogadores
					qtdPlayer = Integer.parseInt(content);
					playerList = new Player[qtdPlayer];
					lever1 = true;
				}
				else if(lever2 == false) { //verifica se tem dev mode na linha 2, e inicia o jogo na perspectiva do model
					devMode = Boolean.parseBoolean(content);
					model.iniciarJogoLoad(qtdPlayer, devMode);
					lever2 = true;
				}
				
				
				
				else if(i < qtdPlayer) {
					if(contador > 14) {//passou para outro player
						playerList[i] = new Player(cor,id,saldo,pos,avancouTab,jaConstruiu,dadosIguaisSeguidos,tirouCarta,estaFalido,estaDevendoCarta,estaDevendoAluguel,estaDevendoImpostoDeRenda,passeLivre,estaPreso);
						i+=1;
						contador = 0;
						
					}

					switch(contador) { //associa uma variavel a um tipo de conteudo do txt 
					case 0:
						if(content == "r") {
							cor = Color.red;
							contador++;
						}
						else if(content == "b"){
							cor = Color.blue;
							contador++;
							}
						else if(content == "o"){
							cor = Color.orange;
							contador++;
						}
						else if(content == "y"){
							cor = Color.yellow;
							contador++;
						}
						else if(content == "p"){
							cor = Color.pink;
							contador++;
						}
						else if(content == "g"){
							cor = Color.gray;
							contador++;
							}
					case 1:
						id = Integer.parseInt(content);
						contador++;
					case 2:
						saldo = Integer.parseInt(content);
						contador++;
					case 3:
						pos = Integer.parseInt(content);
						contador++;
					case 4:
						avancouTab = Boolean.parseBoolean(content);
						contador++;
					case 5:
						jaConstruiu = Boolean.parseBoolean(content);
						contador++;
					case 6:
						dadosIguaisSeguidos = Integer.parseInt(content);
						contador++;
					case 7:
						tirouCarta = Boolean.parseBoolean(content);
						contador++;
					case 8:
						estaFalido = Boolean.parseBoolean(content);
						contador++;
					case 9:
						estaDevendoCarta = Boolean.parseBoolean(content);
						contador++;
					case 10:
						estaDevendoAluguel = Boolean.parseBoolean(content);
						contador++;
					case 11:
						estaDevendoImpostoDeRenda = Boolean.parseBoolean(content);
						contador++;
					case 12:
						passeLivre = Boolean.parseBoolean(content);
						contador++;
					case 13:
						estaPreso = Boolean.parseBoolean(content);
						contador++;
					}
				}
				/*
				 * Colocar aqui outro switch case para pegar outras propriedades salvas
				 * do jogo que nao estao associadas diretamente a jogadores, como por exemplo
				 * de propriedades ou do proprio tabuleiro
				 * */
				
				
				Player.setPlayerList(playerList); //seta a lista de jogagdores
				//aqui, a funcao "iniciar jogo" da Model ja foi feita, porem com outra cara
				System.out.println("Hello there!");
			}
			scan.close();
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
