package airline;
import utilities.DateTime;

import airline.Baggage;
import exceptions.InvalidDate;
import exceptions.InvalidID;
import exceptions.InvalidRow;
import exceptions.InvalidSeat;
/*
 * Class:			Booking
 * Description:		The class represents a seat on a flight that can be booked 
 * Author:			[Vincent Pranata] - [s3665858]
 */
public class Booking 
{

	private final int maxBaggage = 3;
	private final int maxHistory = 10;
	private String id;
	private String rowNumber;
	private String seatNumber;
	private double bookingFee=1200;
	private String firstName;
	private String lastName;
	private Baggage checkedBaggage[] = new Baggage[maxBaggage];
	private Baggage baggageHistory[] = new Baggage[maxHistory];
	private boolean exitRowSeat;
	private double totalWeight=0.0;
	private DateTime checkInDate;
	private String passengerId;
	private int baggageNum=0;
	private int historyNum=9;
	private Baggage bag;
	private final double maxWeight = 20.0;
	private final int idLength=3;
	private String details;
	private boolean limosine;
	
	//the constructor of booking class
	public Booking(String id, String rowNumber, String seatNumber, double fee) throws InvalidID, InvalidRow, InvalidSeat
	{
		String prefix=id.substring(0,1);
		id=id.substring(1,id.length());
		//what to prints if the id is not 3 in length
		if(id.length()!=idLength)
		{
			throw new InvalidID("\nError: Id must be 3 characters");
		}
		//what to prints if the row is not between A and I
		else if(!(rowNumber.charAt(0)<74 && rowNumber.charAt(0)>64) || rowNumber.length()!=1)
		{
			throw new InvalidRow("\nError: Row must a single character be between A - I.");
		}
		//what to prints if the seat is not between 1 and 9
		else if(!(seatNumber.charAt(0)<58 && seatNumber.charAt(0)>48) || seatNumber.length()!=1)
		{
			throw new InvalidSeat("\nError: Seat must be a single character between 1 - 9.");
		}
		//what to if the id, row, seat are using the correct format
		else
		{
			bookingFee+=fee;
			this.id = prefix+id;
			this.rowNumber = rowNumber;
			this.seatNumber = seatNumber;
			//if the seat is either 3/4/6/7 then exit row is true
			if(this.seatNumber.equals("3") || this.seatNumber.equals("4") || this.seatNumber.equals("6") || this.seatNumber.equals("7"))
			{
				exitRowSeat=true;
			}
		}
	}
	//the method that is used for set a booking to a first and last name
	public String book(String firstName, String lastName)
	{
		//what to do if there are first and last name
		if(firstName!=null && lastName!=null)
		{
			this.firstName=firstName;
			this.lastName=lastName;
			//the passenger id if both the first and last name are more than 3 long
			if(this.firstName.length()>=idLength && this.lastName.length()>=idLength)
			{
				passengerId= this.firstName.substring(0,3).toUpperCase()+this.lastName.substring(0,3).toUpperCase();
			}
			//the passenger id if the first name is less than 3 long and last name is more than 3 long
			else if (this.firstName.length()<=idLength && this.lastName.length()>=idLength)
			{
				passengerId= this.firstName.toUpperCase()+this.lastName.substring(0, 3).toUpperCase();
			}
			//the passenger id if the first is more than 3 long and last name is less than 3 long
			else if (this.firstName.length()>=idLength && this.lastName.length()<=idLength)
			{
				passengerId= this.firstName.substring(0, 3).toUpperCase()+this.lastName.toUpperCase();
			}
			//the passenger id if both the first and last name are less than 3 long
			else
			{
				passengerId= this.firstName.toUpperCase()+this.lastName.toUpperCase();
			}
			return toString();
		}
		//what to do if there are no first and last name
		else if (firstName==null && lastName==null)
		{
			return "The booking could not be made as there is no id.";
		}
		return "";
	}
	//The method to check bags
	public String checkInBag(String lastName, double weight)
	{
		//what to do if the total weight and the weight inputed is more than 20
		if(weight+totalWeight>maxWeight)
		{
			details = "\nYour baggages' weight are exceeding the maximum weight.\n";
		}
		//what to do if the total bagagge is more than 3
		else if (baggageNum>=maxBaggage) 
		{
			details = "\nYour baggage is exceeding the total capacity.\n";
		}  
		//what to do if the total weight is less tahn 20 and the weight inputed is less than 0
		else if(weight>=0 && weight <=maxWeight)
		{
			checkInDate = new DateTime();
			bag = new Baggage(id, passengerId, weight, checkInDate);
			checkedBaggage[baggageNum]=bag;
			baggageNum++;
			totalWeight+=weight;
			details = "";
		}
		return details;
	}
	//the method to collect checked bags
	public String collectBags(DateTime dateCollected)
	{
		//a loop that is going until i is 3 
		for(int i=0; i<checkedBaggage.length; i++)
		{
			//what happen if baggage history of certain index is null
			if(baggageHistory[historyNum]== null)
			{
				baggageHistory[historyNum]=checkedBaggage[i];
			}
			//what happen if baggage history of certain index is not null
			if(checkedBaggage[i]!=null)
			{
				//trying the code to see if the collected date is possible/ okay/ valid
				try
				{
					checkedBaggage[i].collect(dateCollected);
					firstName=null;
					lastName=null;
					for(int x=baggageHistory.length-1 ; x >0; x--)
					{
						baggageHistory[x] = baggageHistory[x-1];
					}
					baggageHistory[0]=checkedBaggage[i];
				}
				//what to throw if the collected date is invalid
				catch(InvalidDate e)
				{
					System.out.println(e.getMessage());
					break;
				}
			}
		}
		return "";
	}
	//a getter method for id
	public String getId()
	{
		return id;
	}
	//a getter method for seat
	public String getSeatNumber()
	{
		return seatNumber;
	}
	//a getter method for row
	public String getRowNumber()
	{
		return rowNumber;
	}
	//a getter method for last name
	public String getLastName()
	{
		if(lastName!=null)
		{
			details = lastName;
		}
		else
		{
			details="";
		}
		return details;
	}
	//a method to change a date of string format to date of DateTime format when the backup data is loaded
	public DateTime getDateTime(String date)
	{
		String calendar[] = date.split("-");
		int year =  Integer.parseInt(calendar[0]);
		int month =  Integer.parseInt(calendar[1]);
		int day =  Integer.parseInt(calendar[2]);
		DateTime Date = new DateTime (day, month, year);
		return Date;
	}
	//a method to check the bags that are loaded from the backup class
	public String dataCheckInBag(String id, String passengerId, double weight, DateTime checkInDate)
	{
		if(weight+totalWeight>maxWeight)
		{
			details = "\nYour baggages' weight are exceeding the maximum weight.\n";
		}
		else if (baggageNum>=maxBaggage) 
		{
			details = "\nYour baggage is exceeding the total capacity.\n";
		}
		else
		{
			bag = new Baggage(id, passengerId, weight, checkInDate);
			checkedBaggage[baggageNum]=bag;
			baggageNum++;
			totalWeight+=weight;
			details = bag.toString();
		}
		return details;
	}
	//the method where you get the details of the bookings
	public String getDetails()
	{
		String details = String.format("\n%-25s%s%s%s\n%-25s%s\n%-25s%s\n%-25s%s%s\n",
										"ID:",id.substring(0,1),"_",id.substring(1,4),"Row Number:",rowNumber,
										"Seat Number:",seatNumber,"Standard Fare:","$",bookingFee);
		//what to add to details if both first and last name are not null
		if (firstName != null && lastName != null)
		{
			details += String.format("%-25s%s\n%-25s%s\n","First Name:", firstName, "Last Name:", lastName);
		}
		return details;
	}
	//the method where you get details of the bags from the baggage class
	public String getBaggageDetails()
	{
		details = "";
		if (checkedBaggage!=null)
		{	
			for (int i=0; i < checkedBaggage.length; i++)
			{
				if(checkedBaggage[i]==null)
				{
					break;
				}
				details += "____________________________________________\n"+checkedBaggage[i].getDetails()+"\n";
			}
		}
		return details;
	}
	//the method where you get the details of historical baggage
	public String getHistoryDetails()
	{
		details = "";
		if (baggageHistory[0]!=null)
		{
			for (int i=0; i < baggageHistory.length; i++)
			{
				if(baggageHistory[i]!=null)
				{
					details += "\n"+baggageHistory[i].getDetails()+"\n____________________________________________\n";
				}
			}
		}
		else
		{
			details ="\nError - There is no baggage for booking id: "+id.substring(0,1)+"_"+id.substring(1,4)+"-"+rowNumber+seatNumber;
		}
		return details;
	}
	//the method where you get the booking data to put in the backup file
	public String toString()
	{
		details = id+":"+rowNumber+":"+seatNumber+":"+bookingFee+":"+firstName+":"+lastName;
		return details;
	}
	//a method where you get the bookings data to put in the backup file
	public String toStringBaggage()
	{
		details="";
		for (int i=0; i < checkedBaggage.length; i++)
		{
			if(checkedBaggage[i]!=null)
			{
				details += ":"+checkedBaggage[i].toString();
			}
		}
		return details;
	}
	public void limosine(boolean limosine)
	{
		this.limosine=limosine;
	}
}