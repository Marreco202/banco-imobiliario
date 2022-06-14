package observerView;

public class ViewEvent {
	
	private boolean clicouInciarJogo = false;
	private boolean clicouRolarDados = false;
	private boolean clicouFinalizarRodada = false;
	private boolean clicouComprarPropriedade = false;
	private boolean devMode = false;
	
	private boolean setDadosDaVez = false;
	public int i, j;
	
	private int qtdJogadores;
	
	public ViewEvent(){
	}
	
	public void setClicouIniciarJogo(boolean clicouIniciarJogo) {
		this.clicouInciarJogo = clicouIniciarJogo;
	}
	
	public void setClicouRolarDados(boolean clicouRolarDados) {
		this.clicouRolarDados = clicouRolarDados;
	}
	
	public void setClicouComprarPropriedade(boolean clicouComprarPropriedade) {
		this.clicouComprarPropriedade = clicouComprarPropriedade;
	}
	
	public void setClicouFinalizarRodada(boolean clicouFinalizarRodada) {
		this.clicouFinalizarRodada = clicouFinalizarRodada;
	}
	
	public boolean getClicouFinalizarRodada() {
		return clicouFinalizarRodada;
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
	
	public boolean getDevMode() {
		return devMode;
	}

	public void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}
	
	public void setDadosDaVez(boolean setDadosDaVez, int i, int j) {
		this.setDadosDaVez = setDadosDaVez;
		this.i = i;
		this.j = j;
	}
	
	public boolean getSetDadosDaVez() {
		return this.setDadosDaVez;
	}
}
