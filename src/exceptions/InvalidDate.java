package exceptions;

@SuppressWarnings("serial")
public class InvalidDate extends Exception
{
	//the method InvalidSeat that is used for validate collect date and is the subclass of exception
	public InvalidDate(String exception)
	{
		super(exception);
	}
}
