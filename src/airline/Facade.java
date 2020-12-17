package airline;
import application.AirlineSystem;
import application.SimbaAirways;
import utilities.DateTime;
/*
 * Class:			Facade
 * Description:		The class is used to separate the menu from the actual system. 
 * Author:			[Vincent Pranata] - [s3665858]
 * NOTE: 			You will need to modify the method bodies in this class
 * 					to forward calls to the application class.
 * 					You will need to make sure the return statements are also
 * 					updated to return the correct data back to the menu.
 */
public class Facade implements AirlineSystem 
{
	//making an object called system of SimbaAierways class
	SimbaAirways system = new SimbaAirways();
	@Override
	//the method where it gets data from menu class and sends it to the method addEconomy in SimbaAirways class and returns it to menu class
	public String addEconomySeat(String id, String rowNumber, String seatNumber)
	{
		String result = system.addEconomy(id, rowNumber, seatNumber);
		return result;
	}
	
	@Override
	//the method where it gets data from menu class and sends it to the method addBusiness in SimbaAirways class and returns it to menu class
	public String addBusinessSeat(String id, String rowNumber, String seatNumber) 
	{
		String result = system.addBusiness(id, rowNumber, seatNumber);
		return result;
	}
	
	@Override
	//the method where it gets data from menu class and sends it to the method book in SimbaAirways class and returns it to menu class
	public String book(String flightId, String seatNumber, String firstName, String lastName)
	{
		String result = system.bookSeat(flightId, seatNumber, firstName, lastName);
		return result;
	}
	
	@Override
	//the method where it gets data from menu class and sends it to the method checkInBaggage in SimbaAirways class and returns it to menu class
	public String checkBaggage(String flightId, String seat,String lastName, double weight)
	{
		String result=system.checkInBaggage(flightId, seat, lastName, weight);
		return result;
	}
	
	@Override
	//the method where it gets data from menu class and sends it to the method pickUpBaggage in SimbaAirways class and returns it to menu class
	public String collectBaggage(String id, String seat, String lastName, DateTime dateOfCollection)
	{
		String result=system.pickUpBaggage(id, seat, lastName, dateOfCollection);
		return result;
	}
	//method were left empty because dunno what its used for
	public boolean getItemById(String flightId, String seatNumber)
	{
		return false;
	}

	@Override
	//Method was left empty because have no idea about age
	public String bookEconomy(String id, String firstName, String lastName, int age, boolean exitRow)
	{
		return "";
	}

	@Override
	//the method where it gets data from menu class and sends it to the method displayAllBooking in SimbaAirways class and returns it to menu class
	public String displayAllBookings()
	{
		String result = system.displayAllBookings();
		return result;
	}

	@Override
	//Method was left empty because have no idea about age and platforms
	public String bookBusiness(String id, String firstName, String lastName, int age, String platforms)
	{
		return "";
	}

	@Override
	//the method where it gets data from menu class and sends it to the method seedData in SimbaAirways class
	public void seedData()
	{
		system.seedData();
	}

	@Override
	//the method where it gets data from menu class and sends it to the method writeData in SimbaAirways class
	public void writeData()
	{
		system.writeData();
	}

	@Override
	//the method where it gets data from menu class and sends it to the method bookSeat in SimbaAirways class and returns it to menu class
	public boolean checkIfBookingExists(String flightId, String seatNumber)
	{
		boolean result = system.bookSeat(flightId, seatNumber);
		return result;
	}

	@Override
	//the method where it gets data from menu class and sends it to the method displayBooking in SimbaAirways class and returns it to menu class
	public String displayBooking(String id, String seatId)
	{
		String result = system.displayBooking(id, seatId);
		return result;
	}

	@Override
	//the method where it gets data from menu class and sends it to the method displayHistoricalBaggage in SimbaAirways class and returns it to menu class
	public String displayHistoricalBaggage(String id, String seatId)
	{
		String result = system.displayHistoricalBaggage(id, seatId);
		return result;
	}

	@Override
	//the method where it gets data from menu class and sends it to the method isValidId in SimbaAirways class and returns it to menu class
	public String isValidId(String id, String seatId)
	{
		String result = system.isValidId(id, seatId);
		return result;
	}

	@Override
	//the method where it gets data from menu class and sends it to the method bookLimosine in SimbaAirways class and returns it to menu class
	public boolean bookLimosine(String id, String seatId)
	{
		system.bookLimosine(id, seatId);
		return false;
	}

}