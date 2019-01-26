package GR_Application;

/**
 * This class extends the Exception class and is used to handle the exception when all zeros are selected as scores by
 * the User on the Grading Window.
 */
public class AllZerosException extends Exception {
	/**
	 * This is a class constructor with the exception message.
	 */
	public AllZerosException(String message)
	{
	  super(message);
	}

}

