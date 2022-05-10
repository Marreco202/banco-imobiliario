package exeptions;

public class JogadorInexistente extends Exception {

	private static final long serialVersionUID = 1L;

	public JogadorInexistente() {
	}

	public JogadorInexistente(String message) {
		super(message);
	}
}
