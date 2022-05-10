package exeptions;

public class JogadorNaoEDonoDaPropriedade extends Exception {

	private static final long serialVersionUID = 1L;

	public JogadorNaoEDonoDaPropriedade() {
	}

	public JogadorNaoEDonoDaPropriedade(String message) {
		super(message);
	}

}
