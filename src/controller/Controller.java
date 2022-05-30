package controller;

import model.Model;
import view.IniciarGraficos;

public class Controller {
	
	private static Controller controller = null;
	private IniciarGraficos janelaPrincipal;
	private Model model;

	private Controller() {
		model = Model.getModel();
	}
	
	public static Controller getController() {
		if(controller == null) {
			controller = new Controller();
			return controller;
		}
		return controller;
	}
	
	public void iniciarGraficos() {
		janelaPrincipal = new IniciarGraficos();
	}

	public void iniciarJogo(int qtdPlayers) {
		model.iniciarJogo(qtdPlayers);
	}
	
}
