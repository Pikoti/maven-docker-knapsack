package customExceptions;

public class UnableToRemoveFromLineException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnableToRemoveFromLineException (String message) {
		super(message);
	}
}
