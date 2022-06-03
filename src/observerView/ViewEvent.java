package observerView;

public class ViewEvent {
	private boolean clicouInciarJogo = false;
	private boolean clicouRolarDados = false;
	private boolean clicouComprarPropriedade = false;
	private int qtdJogadores;
	
	public ViewEvent(boolean userClicouIniciarJogo, boolean userClicouRolarDados, boolean clicouComprarPropriedade){
		this.clicouInciarJogo = userClicouIniciarJogo;
		this.clicouRolarDados = userClicouRolarDados;
		this.clicouComprarPropriedade = clicouComprarPropriedade;
	}
	
	public boolean getClicouComprarPropriedade() {
		return clicouComprarPropriedade;
	}
	
	public boolean getClicouInciarJogo() {
		return clicouInciarJogo;
	}
	
	public boolean getClicouRolarDados() {
		return clicouRolarDados;
	}
	
	public int getQtdJogadores() {
		return qtdJogadores;
	}
	
	public void setQtdJogadores(int qtdJogadores) {
		this.qtdJogadores = qtdJogadores;
	}
}
