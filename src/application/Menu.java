package application;
import java.util.Scanner;

import airline.Facade;
import utilities.DateTime;

/*
 * Class:			Menu
 * Description:		The class represents a menu that the user will use to
 * 					interact with the application class via a facade. 
 * Author:			[Vincent Pranata] - [s3665858]
 */
public class Menu
{
	private String id;
	private String rowNumber;
	private String seatNumber;
	private String firstName;
	private String lastName;
	private String limosine;
	private String prefix;
	private DateTime collectDate;
	private double weight;
	private String choice;
	private String details;
	//making a new object called scan of scanner class
	Scanner scan = new Scanner(System.in);
	//making a new object called facade of facade class
	Facade facade = new Facade();
	//the method that is run when the option AB is chosen
	public void addBooking()
	{	
		//scanning the value of id, row, seat, type
		System.out.print("Enter ID:			");
		id = scan.next().toUpperCase();
		System.out.print("Enter Row Number:		");
		this.rowNumber = scan.next().toUpperCase();
		System.out.print("Enter Seat Number:		");
		this.seatNumber = scan.next();
		System.out.print("Economy/ Business (E/B):	");
		prefix = scan.next().toUpperCase();	
		//to check if the prefix is either E and B and is 1 in length
		if((prefix.charAt(0)==69 || prefix.charAt(0)==66) && prefix.length()==1)
		{
			id=prefix+id;
			//if the prefix is E then it sends the value of id, row, and seat to method addEconomySeat of facade class
			if(prefix.equals("E"))
			{
				details=facade.addEconomySeat(id, rowNumber, seatNumber);
			}
			//if the prefix is B then it sends the value of id, row, and seat to method addBusinessSeat of facade class
			else
			{
				details=facade.addBusinessSeat(id, rowNumber, seatNumber);
			}
		}
		//if the prefix is neither E or B and is more than 1 in length
		else
		{
			details = "\nInvalid class, booking failed.\n";
		}
		System.out.println(details);
	}
	//the method that is run when the option BS is chosen
	public void bookSeat()
	{
		//scanning the value of id and seat
		System.out.print("Enter flight id:		");
		id = scan.next().toUpperCase();
		System.out.print("Enter seat:			");
		seatNumber = scan.next().toUpperCase();
		//sending the value of id and seat to the method isValidId in facade class
		String verify=facade.isValidId(id, seatNumber);
		//what to do if the value that returns from method isValid is ""
		if(verify.equals(""))
		{
			id=prefix+id;
			//sending the value of id and seat to the method checkIfBookingExist in facade class
			Boolean check = facade.checkIfBookingExists(id, seatNumber);
			//what to do if the value that returns from method checkIfBookinExist is false and the length of seat is not 2
			if(check==false || seatNumber.length()!=2)
			{
				details="Error - The booking could not be completed\n";

			}
			//what to do if the value that returns from method checkIfBookinExist is true and the length of seat is 2
			else
			{
				//scanning the value of first and last name
				System.out.print("Enter First Name:		");
				firstName = scan.next().toUpperCase(); 
				System.out.print("Enter Last Name:		");
				lastName = scan.next().toUpperCase(); 
				//what to do if the prefix is B
				if(prefix.equals("B"))
				{
					//scanning the value of limosine
					System.out.print("Would you like to book a limosine? (Y/N)	");
					limosine = scan.next().toUpperCase();
					//what to do if the value of limosine is either Y or N and the length is 1
					if((limosine.equals("Y") || limosine.equals("N")) && prefix.length()==1)
					{
						//what to do if the value of limosine is Y
						if(limosine.equals("Y"))
						{
							//wsendig the value of id and seat to the method bookLimosine in facade class
							facade.bookLimosine(id, seatNumber);
						}
					}
				}
				//sending the value of id, seat, first and last to the method book of facade class
				details=facade.book(id, seatNumber, firstName, lastName);
			}
		}
		//what to do if the value that returns from method isValid is not ""
		else
		{
			details = verify;
		}
		System.out.println(details);
	}
	//the method that is called when the option CB is called
	public void checkInBaggage()
	{
		//scanning the value of id, seat and last name 
		System.out.print("Enter flight id:		");
		id = scan.next().toUpperCase();
		System.out.print("Enter seat:			");
		seatNumber = scan.next().toUpperCase();
		System.out.print("Enter last name:		");
		lastName = scan.next().toUpperCase(); 
		//sending the value of id and seat to the method isValidId in facade class
		String verify=facade.isValidId(id, seatNumber);
		//what to do if the value that returns from method isValid is ""
		if(verify.equals(""))
		{
			id=prefix+id;
			//scanning the value of weight
			System.out.print("Enter weight:			");
			weight = scan.nextDouble();
			//sending the value of id, seat, last and weight to the method checkBaggage of facade class
			details = facade.checkBaggage(id, seatNumber, lastName, weight);
		}
		//what to do if the value that returns from method isValid is not ""
		else
		{
			details = verify;
		}
		System.out.println(details);
	}
	//the method that is called when the option PB is called
	public void pickUpBaggage()
	{
		//scanning id, seat and last number
		System.out.print("Enter flight id:		");
		id = scan.next().toUpperCase();
		System.out.print("Enter seat:			");
		seatNumber = scan.next().toUpperCase();
		System.out.print("Enter last name:		");
		lastName = scan.next().toUpperCase(); 
		//sending the value of id and seat to the method isValidId in facade class
		String verify=facade.isValidId(id, seatNumber);
		//what to do if the value that returns from method isValid is ""
		if(verify.equals(""))
		{
			id=prefix+id;
			collectDate = new DateTime();
			//sending the value of id, seat, last name and collect date to the method collectBaggage of facade classs
			details = facade.collectBaggage(id, seatNumber, lastName, collectDate);
			
		}
		//what to do if the value that returns from method isValid is not ""
		else
		{
			details = verify;
		}
		System.out.println(details);
	}
	//the method that is called when the option DB is called
	public void displayBooking()
	{
		//scanning the value of id and seat
		System.out.print("Enter flight id:		");
		id = scan.next().toUpperCase();
		System.out.print("Enter seat:			");
		seatNumber = scan.next().toUpperCase();
		//sending the value of id and seat to the method isValidId in facade class
		String verify=facade.isValidId(id, seatNumber);
		//what to do if the value that returns from method isValid is ""
		if(verify.equals(""))
		{
			details = facade.displayBooking(id, seatNumber);
		}
		//what to do if the value that returns from method isValid is not ""
		else 
		{
			details=verify;
		}
		System.out.println(details);
	}
	//the method that is called when the option DA is called
	public void displayAllBookings()
	{
		String result = facade.displayAllBookings();
		System.out.println(result);
	}
	//the method that is called when the option HB is called
	public void displayHistoricalBaggage()
	{
		//scanning the value of id and seat
		System.out.print("Enter flight id:		");
		id = scan.next().toUpperCase();
		System.out.print("Enter seat:			");
		seatNumber = scan.next().toUpperCase();
		//sending the value of id and seat to the method isValidId in facade class
		String verify=facade.isValidId(id, seatNumber);
		//what to do if the value that returns from method isValid is ""
		if(verify.equals(""))
		{
			id=prefix+id;
			details = facade.displayHistoricalBaggage(id, seatNumber);
		}
		//what to do if the value that returns from method isValid is not ""
		else
		{
			details=verify;
		}
		System.out.println(details);
	}
	//the method that is called when the option SD is called
	public void seedData()
	{
		//calling the method seedData of facade class
		facade.seedData();
	}
	//the method that is called when the option EX is called
	public void writeData()
	{
		//calling the method writeData of facade class
		facade.writeData();
	}
	//the method that is called from the driver class
	public String toString()
	{
		
		return String.format("%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n"
					,"***Simba Airways System Menu ***\n"
					,"Add Booking","AB"
					,"Book Seat","BS"
					,"Checkin Baggage","CB"
					,"Pick up Baggage","PB"
					,"Display Booking Details","DB"
					,"Display ALL Bookings","DA"
					,"Display Historical Baggage","HB"
					,"Seed Data","SD"
					,"Exit Program","EX");
	}
	//the method that is called from the driver class
	public String getInput()
	{
		//scanning the value of choice 
		System.out.print("Enter your option:		");
		choice = scan.next().toUpperCase();
		//what to do if either AB,BS,CB,PB,DB,DA,HB,SD,EX, and if choice is not among those options
		switch (choice)
		{
	        case "AB":
	        		addBooking();
	        		break;
	        case "BS":
	        		bookSeat();
	        		break;
	        case "CB":
	        		checkInBaggage();
	        		break;
	        case "PB":
	        		pickUpBaggage();
	        		break;
	        case "DB":
	        		displayBooking();
	        		 break;
    	    case "DA":
    	    		displayAllBookings();
      				break;
        	case "HB":
	        		displayHistoricalBaggage();
					break;
    	    case "SD":
        			seedData();
					break;
	        case "EX":
	        		writeData();
					break;
        	default:
        			System.out.println("Unknown option\n");
					break;
   		}
		return choice;
	}
}