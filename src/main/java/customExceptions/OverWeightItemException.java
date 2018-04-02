package customExceptions;

/**
 * 
 * @author lucie
 * Exception class to handle an overweight object
 */
public class OverWeightItemException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public OverWeightItemException (String message) {
		super(message);
	}
	

}
