package observerView;

import java.util.ArrayList;

public interface ObservadoView {
	ArrayList<ObservadorView> listaDeObservadores = new ArrayList<ObservadorView>();
	
	public void addObservador(ObservadorView novoObservador);
	public void notificarObservadores(ViewEvent e);
}
