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

	public void iniciarJogo(int qtdPlayers, boolean devMode) {
		model.iniciarJogo(qtdPlayers, devMode);
	}

	@Override
	public void handleInput(ViewEvent e) {
		if(e.getClicouInciarJogo()) {
			this.iniciarJogo(e.getQtdJogadores(), e.getDevMode());
			janelaPrincipal.iniciarTabuleiro();
		}else if(e.getClicouFinalizarRodada()){
			model.finalizarRodada();
			janelaPrincipal.repaintJanela();
		}else if(e.getClicouRolarDados()) {
			model.rolarDados();
			janelaPrincipal.repaintJanela();
		}else if(e.getClicouComprarPropriedade()) {
			try {
				model.comprarPropriedade();
			}catch (Exception ex) {
				// TODO: handle exception
			}
			janelaPrincipal.repaintJanela();
		}else if(e.getSetDadosDaVez()) {
			model.setDadosDaVez(e.i, e.j);
			janelaPrincipal.repaintJanela();
		}
	}
	
}
