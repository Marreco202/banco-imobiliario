package exeptions;

public class PropriedadeNaoPossuiDono extends Exception {
	
	public PropriedadeNaoPossuiDono() {
		super();
	}
	
	public PropriedadeNaoPossuiDono(String errorMessage) {
		super(errorMessage);
	}
}
