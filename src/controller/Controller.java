package controller;

import view.MainMenu;

public class Controller {
	
	private static Controller controller = null;

	private Controller() {
	}
	
	public static Controller getController() {
		if(controller == null) {
			controller = new Controller();
			return controller;
		}
		return controller;
	}
	
	public void iniciarJogo() {
		MainMenu m = new MainMenu();
	}

}
