package customExceptions;

public class ZeroWeightBoxException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ZeroWeightBoxException (String message) {
		super(message);
	}
}
