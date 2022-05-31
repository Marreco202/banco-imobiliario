package observerView;

public class ViewEvent {
	private boolean clicouInciarJogo = false;
	private boolean clicouRolarDados = false;
	private int qtdJogadores;
	
	public ViewEvent(boolean userClicouIniciarJogo, boolean userClicouRolarDados){
		this.clicouInciarJogo = userClicouIniciarJogo;
		this.clicouRolarDados = userClicouRolarDados;
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
