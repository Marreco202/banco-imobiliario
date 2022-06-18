package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sidekick {

	
	
	
	public static void readSaveFile() {
		
		Model model = Model.getModel();
		// getPlayerList nao adianta de nada pq nao existe uma lista de jogadores, o jogo esta zerado
		int qtdPlayer = 0;
		boolean devMode = false; 
		
		boolean lever1 = false; //"alavanca" que proca assim que a linha de quantidade de jogadores eh lida
		boolean lever2 = false; //"alavanca" que pega se o devMode esta ligado ou nao
		
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
					lever1 = true;
				}
				else if(lever2 == false) { //verifica se tem dev mode na linha 2, e inicia o jogo na perspectiva do model
					devMode = Boolean.parseBoolean(content);
					//model.iniciarJogo(qtdPlayer, devMode);
					lever2 = true;
				}
				
				
				
				else if(i < qtdPlayer) {
					Player playerlist[] = Player.getPlayerList();
					
					if(contador > 15) {//passou para outro player
						i+=1;
						contador = 0;
						
					}
					
					switch(contador) {
					case 0:
						playerlist[i].
							
					}
				}
				
				Player.setPlayerList(playerlist);
				
				System.out.println("Hello there!");
			}
			scan.close();
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
