package exceptions;

@SuppressWarnings("serial")
public class InvalidRow extends Exception
{
	//the method InvalidSeat that is used for validate row and is the subclass of exception
	public InvalidRow(String exception)
	{
		super(exception);
	}
}
