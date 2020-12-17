package airline;

import exceptions.InvalidID;
import exceptions.InvalidRow;
import exceptions.InvalidSeat;

public class Business extends Booking
{
	boolean limosine;
	String details;
	//the constructor and also the subclass of class Booking
	public Business(String id, String rowNumber, String seatNumber, double fee) throws InvalidID, InvalidRow, InvalidSeat
	{
		super(id, rowNumber, seatNumber, fee);
	}
	//a method where its defining the variable of limosine
	public void limosine(boolean limosine)
	{
		this.limosine=limosine;
	}
	//the method that prints the details of an business booking of when its called
	public String getDetails()
	{
		details=String.format("%-25s%s\n%-25s%s\n\n","Menu:", "Beef Burgandy, Cheese Platter, Glass of Wine, "
							,"","Potato and Eggplant Curry, Chocolate Mouse, Fresh Juice");
		if(limosine==true)
		{
			details += String.format("%-25s%s", "Limosine:", "YES\n");
		}
		else
		{
			details += String.format("%-25s%s", "Limosine:", "NO\n");
		}
		details = super.getDetails()+details+super.getBaggageDetails();
		return details;
	}
	//the method that gets the booking data  details of an business booking to prints into the backup data
	public String toString()
	{
		if(limosine==true)
		{
			details = ":LI";
		}
		else
		{
			details = ":NO";
		}
		details = super.toString()+details+super.toStringBaggage();
		return details;
	}
}
