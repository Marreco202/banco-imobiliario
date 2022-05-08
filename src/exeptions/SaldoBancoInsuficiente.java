package exeptions;

public class SaldoBancoInsuficiente extends Exception {

	public SaldoBancoInsuficiente() {
	}

	public SaldoBancoInsuficiente(String message) {
		super(message);
	}

}
