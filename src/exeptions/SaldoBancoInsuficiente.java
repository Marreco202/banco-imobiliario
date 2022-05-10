package exeptions;

public class SaldoBancoInsuficiente extends Exception {

	private static final long serialVersionUID = 1L;

	public SaldoBancoInsuficiente() {
	}

	public SaldoBancoInsuficiente(String message) {
		super(message);
	}

}
