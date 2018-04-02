package customExceptions;

public class ZeroWeightItemException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ZeroWeightItemException (String message) {
		super(message);
	}
}
