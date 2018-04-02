package customExceptions;

public class UnableToaddInboxException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UnableToaddInboxException (String message) {
		super(message);
	}
}
