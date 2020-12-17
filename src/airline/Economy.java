package airline;

import exceptions.InvalidID;
import exceptions.InvalidRow;
import exceptions.InvalidSeat;

public class Economy extends Booking
{
	String details="";
	boolean exitRowSeat=false;
	//the constructor and also the subclass of class Booking
	public Economy(String id, String rowNumber, String seatNumber, double fee) throws InvalidID, InvalidRow, InvalidSeat 
	{
		super(id, rowNumber, seatNumber, fee);
		if(fee==40)
		{
			exitRowSeat = true;
		}
	}
	//the method that prints the details of an economy booking of when its called
	public String getDetails()
	{
		if(exitRowSeat==true)
		{
			details = String.format("%-25s%s\n","Exit Row:", "YES");
		}
		else
		{
			details = String.format("%-25s%s\n","Exit Row:", "NO");
		}
		details = super.getDetails()+details+super.getBaggageDetails();
		return details;
	}
	//the method that gets the booking data  details of an economy booking to prints into the backup data
	public String toString()
	{
		if(exitRowSeat==true)
		{
			details = ":ER";
		}
		else
		{
			details = ":NO";
		}
		details = super.toString()+details+super.toStringBaggage();
		return details;
	}
}
