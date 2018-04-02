package customExceptions;

public class UnableToAddInLineException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnableToAddInLineException (String message) {
		super(message);
	}
}
