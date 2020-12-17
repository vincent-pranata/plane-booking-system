package airline;
import exceptions.InvalidDate;
import utilities.DateTime;

/*
 * Class:			Baggage
 * Description:		The class represents a piece of luggage 
 * Author:			[Vincent Pranata] - [s3665858]
 */
public class Baggage
{
	private String id;
	private double weight;
	private DateTime checkInDate;
	private DateTime collectedDate;
	
	//The constructor of baggage class
	public Baggage(String id, String passengerId, double weight, DateTime checkedInDate)
	{
		//to save the value of checkedInDate, weight, and passengerId, and id that are sent from other class in this class
		checkInDate = checkedInDate;
		this.weight = weight;
		this.id = id+"_"+passengerId +"_"+checkedInDate.getEightDigitDate();
	}
	//the method collect that is used to determine if a bag is able to be collected or not
	public boolean collect(DateTime collectionDate) throws InvalidDate
	{
		//What to print if the collected date is less than the checkInDate
		if((collectionDate != null) && (DateTime.diffDays(collectionDate, checkInDate)<0))
		{
			throw new InvalidDate("\nCollection date can't be made since it is before check in date.");
		}
		else
		{
			//to save the value of collection date that is sent from other class in this class
			this.collectedDate=collectionDate;
			return true;
		}
	}
	//the method where you get the details of each bag
	public String getDetails()
	{
		//what to print if there is no collected date
		if(collectedDate == null)
		{
			return String.format("%-25s%s%-25s%s%s%-25s%s"
					,"\nBaggage Id",id
					,"\nWeight",weight," kg"
					,"\nChecked in",checkInDate.getFormattedDate());
		}
		//what to print if there is a collected date
		else
		{
			return String.format("%-25s%s%-25s%s%s%-25s%s%-25s%s"
					,"\nBaggage Id",id
					,"\nWeight",weight," kg"
					,"\nChecked in",checkInDate.getFormattedDate()
					,"\nCollected",collectedDate.getFormattedDate());
		}
	}
	//the method where you get the bags data to put in the backup file
	public String toString()
	{
		//what to put in the backup file if there is no collected date
		if(collectedDate == null)
		{
			return id+":"+weight+":"+checkInDate+":NO";
		}
		//what to put in the backup file if there is a collected date	
		else
		{
			return id+":"+weight+":"+checkInDate+":"+collectedDate;
		}
	}
	
}