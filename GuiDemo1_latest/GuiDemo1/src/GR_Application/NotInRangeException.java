package GR_Application;

/**
 * This class extends Exception class and is used to handle exception where the Number of team members entered by the
 * User is not in the valid range of 2 and 7.
 */
public class NotInRangeException extends Exception {
	/**
	 * This is a class constructor with the exception message.
	 */
	public NotInRangeException(String message)
	{
	  super(message);
	}
}
