package exceptions;

@SuppressWarnings("serial")
public class InvalidID extends Exception
{
	//the method InvalidSeat that is used for validate id and is the subclass of exception
	public InvalidID(String exception)
	{
		super(exception);
	}
}
