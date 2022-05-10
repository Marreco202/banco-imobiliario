package exeptions;

public class ValoresAluguelIncorreto extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ValoresAluguelIncorreto(String errorMessage) {
		super(errorMessage);
	}

}
