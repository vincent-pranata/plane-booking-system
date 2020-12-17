package exceptions;

@SuppressWarnings("serial")
public class InvalidSeat extends Exception
{
	//the method InvalidSeat that is used for validate seat and is the subclass of exception
	public InvalidSeat(String exception)
	{
		super(exception);
	}
}
