package controller;

import model.Model;
import observerView.ObservadorView;
import observerView.ViewEvent;
import view.View;

public class Controller implements ObservadorView{
	
	private static Controller controller = null;
	private View janelaPrincipal;
	private Model model;

	private Controller() {
		model = Model.getModel();
	}
	
	public static Controller getController() {
		if(controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	public void iniciarGraficos() {
		janelaPrincipal = View.getView();
		janelaPrincipal.addObservador(this);
	}

	public void iniciarJogo(int qtdPlayers) {
		model.iniciarJogo(qtdPlayers);
	}

	@Override
	public void handleInput(ViewEvent e) {
		if(e.getClicouInciarJogo()) {
			System.out.println("Iniciar Jogo");
			this.iniciarJogo(e.getQtdJogadores());
			janelaPrincipal.iniciarTabuleiro();
		}else if(e.getClicouRolarDados()) {
			System.out.println("Rolar dados");
			model.rolarDados();
			janelaPrincipal.repaintJanela();
		}else if(e.getClicouComprarPropriedade()) {
			try {
				model.comprarPropriedade();
			}catch (Exception ex) {
				// TODO: handle exception
			}
			janelaPrincipal.repaintJanela();
		}
	}
	
}
