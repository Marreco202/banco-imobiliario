package exeptions;

public class ProibidoConstruir extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ProibidoConstruir(String errorMessage) {
		super(errorMessage);
	}
	
}