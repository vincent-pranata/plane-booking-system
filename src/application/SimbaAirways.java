package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import airline.Booking;
import airline.Business;
import airline.Economy;
import exceptions.InvalidID;
import exceptions.InvalidRow;
import exceptions.InvalidSeat;
import utilities.DateTime;

/*
 * Class:			AirlineSystem
 * Description:		The airline system manager the manages the 
 *              	collection of data. 
 * Author:			[Vincent Pranata] - [s3665858]
 */
public class SimbaAirways
{
	private Booking bookings[] = new Booking[81];
	private String rowNumber;
	private String seatNumber;
	private boolean limosine = false;
	private int counter=0;
	//creating a new object called booking of class Booking
	private Booking booking;
	private String details;
	private double fee;
	private String id;
	private PrintWriter output;
	private Scanner scanFile;
	//creating a new object called file of class File
	private File file = new File("Bookings.txt");
	//the constructor of the class
	public SimbaAirways()
	{
		//calling the class readData that is run every time the program is started
		readData();
	}
	//the method that is called from facade to make an economy booking
	public String addEconomy(String id, String rowNumber, String seatNumber)
	{
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
			{
				//getting the value of id, row, and seat from booking of certain index
				String ID=bookings[i].getId();
				String row=bookings[i].getRowNumber();
				String seat=bookings[i].getSeatNumber();
				//what to do if the input id and the ID of a certain booking is the same
				if(id.equals(ID))
				{
					details = "\nError - Flight ID already exists\n";
					break;
				}
				//what to do if the input row and seat with the row and seat of a certain booking is the same
				if(rowNumber.equals(row) && seatNumber.equals(seat))
				{
					details = "\nError - Seat is already booked by another user\n";
					break;
				}
			}
			//what to do if the booking of a certain index is null
			else
			{
				//what to do if the input seat is equal 3/4/6/7
				if(seatNumber.equals("3")||seatNumber.equals("4")||seatNumber.equals("6")||seatNumber.equals("7"))
				{
					fee+=40;
				}
				//the code that  is supposed to be try to be checked of any error 
				try
				{
					//sending the value of id, row, seat and fee to the Economy class  
					booking = new Economy(id, rowNumber, seatNumber, fee);
					details ="\nNew Economy booking added successfully for booking id: E_"+id.substring(1,4)+"\n";
					bookings[counter]=booking;
					counter++;
					fee=0;
					break;	
				}
				//if either of the exception class do not work, it will do this
				catch (InvalidID | InvalidRow | InvalidSeat e)
				{
					System.out.println(e.getMessage());
					details="";
					break;
				}
			}
		}
		return details;
	}
	//the method that is called from facade to make a business booking
	public String addBusiness(String id, String rowNumber, String seatNumber)
	{
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
			{
				//getting the value of id, row, and seat from booking of certain index
				String ID=bookings[i].getId();
				String row=bookings[i].getRowNumber();
				String seat=bookings[i].getSeatNumber();
				//what to do if the input id and the ID of a certain booking is the same
				if(id.equals(ID))
				{
					details = "\nError - Flight ID already exists\n";
					break;
				}
				//what to do if the input row and seat with the row and seat of a certain booking is the same
				if(rowNumber.equals(row) && seatNumber.equals(seat))
				{
					details = "\nError - Seat is already booked by another user\n";
					break;
				}
			}
			//what to do if the booking of a certain index is null
			else
			{
				fee=0;
				details = "\nNew Business booking added successfully for booking id: B_"+id.substring(1,4)+"\n";
				//the code that  is supposed to be try to be checked of any error 
				try
				{
					//sending the value of id, row, seat and fee to the business class  
					booking = new Business(id, rowNumber, seatNumber, fee);
					bookings[counter]=booking;
					counter++;
					break;
				}
				//if either of the exception class do not work, it will do this
				catch (InvalidID | InvalidRow | InvalidSeat e)
				{
					System.out.println(e.getMessage());
					details ="";
					break;
				}
			}
		}
		return details;
	}
	//the method that is called from facade to add the first and last name to an existing booking 
	public String bookSeat(String id, String seatNumber, String firstName, String lastName)
	{
		//sending the value of first and last name to the method book of booking class
    	String check=booking.book(firstName, lastName);
    	for(int i=0; i<bookings.length;i++)
		{
    		//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
    		{
    			details="\nThe flight: "+id.substring(0,1)+"_"+id.substring(1,4)+"-"+seatNumber+" has been successfully booked\n";
    			break;
			}
    		details=check;
    	}
		return details;
	}
	//the method that is called from facade to make a business booking
	public boolean bookSeat(String id, String seatNumber)
	{
		rowNumber=seatNumber.substring(0,1);
		this.seatNumber=seatNumber.substring(1);
		id=id.substring(1,4);
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is null
			if(bookings[i]==null)
			{
				return false;
			}
			//what to do if the booking of a certain index is not null
			else if(bookings[i]!=null )
			{
				String ID=bookings[i].getId();
				String prefix=ID.substring(0,1);
				this.id=prefix+id;
				String row=bookings[i].getRowNumber();
				String seat=bookings[i].getSeatNumber();
				if(this.id.equals(ID) && rowNumber.equals(row) && this.seatNumber.equals(seat))
				{
					return true;
				}
			}
		}
		return false;
	}
	//the method that is called from facade to make a business booking
	public String isValidId(String id, String seatNumber)
	{
		if(id.length()!=3)
		{
			details = "\nInput valid id\n";
		}
		else if(seatNumber.length()!=2)
		{
			details = "\nInput valid seat\n";
		}
		else
		{
			details = "";
		}
		return details;
	}
	//the method that is called from facade to make a baggage check in
	public String checkInBaggage(String id, String seatNumber, String lastName, double weight)
	{
		rowNumber=seatNumber.substring(0,1);
		this.seatNumber=seatNumber.substring(1);
		id=id.substring(1,4);
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
			{
				//getting the value of id,row,seat and last name from a booking of certain index
				String ID=bookings[i].getId();
				String prefix=ID.substring(0,1);
				this.id=prefix+id;
				String row=bookings[i].getRowNumber();
				String seat=bookings[i].getSeatNumber();
				String last=bookings[i].getLastName();
				//comparing the value of inputed id, row, seat and last name with the id, row, seat and last name with from booking of certain index
				if(this.id.equals(ID) && rowNumber.equals(row) && this.seatNumber.equals(seat) && lastName.equals(last))
				{
						//calling the method checkInBag and sending the value of inputed last name and weight
						String detail = bookings[i].checkInBag(lastName, weight);
						//getting the value of a certain index booking details
						details = bookings[i].getDetails()+detail;
						break;
				}
				//what happens if the input last name and the last name of the booking not the same
				else if (!(lastName.equals(last)))
				{
					details = "\nError - The name "+ lastName+" is not found";
				}
				//what happen if there is no booking with the same id,row,seat found
				else
				{
					details = "\nError - The booking with id "+prefix+"_"+id+"-"+seatNumber+", not found\n";
				} 
			}
		}
		return details;
	}
	//the method that is called from facade to pick a bag
	public String pickUpBaggage(String id, String seatNumber, String lastName, DateTime dateCollected)
	{
		rowNumber=seatNumber.substring(0,1);
		this.seatNumber=seatNumber.substring(1);
		id=id.substring(1,4);
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
			{
				//getting the value of id, row, seat, and last name
				String ID=bookings[i].getId();
				String prefix=ID.substring(0,1);
				this.id=prefix+id;
				String row=bookings[i].getRowNumber();
				String seat=bookings[i].getSeatNumber();
				String last=bookings[i].getLastName();
				//comparing the value of inputed id, row, seat and last name with the id, row, seat and last name with from booking of certain index
				if(this.id.equals(ID) && rowNumber.equals(row) && this.seatNumber.equals(seat) && lastName.equals(last))
				{
					//calling the method collectBags of Booking class and sending the value of date collected
					bookings[i].collectBags(dateCollected);
					//getting the value of a certain index booking details
					details = bookings[i].getDetails();
					break;
				}
				//what happens if the input last name and the last name of the booking not the same
				else if (!(lastName.equals(last)))
				{
					details = "\nError - The name "+ lastName+" is not found";
					break;
				}
				//what happen if there is no booking with the same id,row,seat found
				else  
				{
					details = "\nError - The booking with id "+prefix+"_"+id+"-"+seatNumber+", not found\n";
					break;
				}
			}
		}
		return details;
	}
	//the method that is called from facade to display a certain booking
	public String displayBooking(String id, String seatNumber)
	{
		rowNumber=seatNumber.substring(0,1);
		this.seatNumber=seatNumber.substring(1);
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
			{
				// getting the value of id,row,seat from booking of certain index
				String ID=bookings[i].getId();
				String prefix=ID.substring(0,1);
				this.id=prefix+id;
				String row=bookings[i].getRowNumber();
				String seat=bookings[i].getSeatNumber();
				//comparing the value of inputed id, row, seat and last name with the id, row, seat with from booking of certain index
				if(this.id.equals(ID) && rowNumber.equals(row) && this.seatNumber.equals(seat))
				{
					//getting the value of a certain index booking details
					details = bookings[i].getDetails()+"\n";
					break;
				}
				//what happen if there is no booking with the same id,row,seat found
				else
				{
					details="\nThe booking with id "+prefix+"_"+id+"-"+seatNumber+", not found\n";
				}
			}
		}
		return details;
	}
	//the method that is called from facade to make a business booking
	public String displayAllBookings()
	{
		details="";
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
			{
				//getting the value of a certain index booking details
				details +=bookings[i].getDetails()+"______________________________________________\n______________________________________________\n";
			}
		}
		return details;
	}
	//the method that is called from facade to make a business booking
	public String displayHistoricalBaggage(String id, String seatNumber)
	{
		rowNumber=seatNumber.substring(0,1);
		this.seatNumber=seatNumber.substring(1);
		id=id.substring(1,4);
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
			{
				//getting the value of id, row, seat from booking of certain index
				String ID=bookings[i].getId();
				String prefix=ID.substring(0,1);
				this.id=prefix+id;
				String row=bookings[i].getRowNumber();
				String seat=bookings[i].getSeatNumber();
				//comparing the value of inputed id, row, seat and last name with the id, row, seat and last name with from booking of certain index
				if(this.id.equals(ID) && rowNumber.equals(row) && this.seatNumber.equals(seat))
				{
					details = bookings[i].getHistoryDetails();
					break;
				}
				//what happen if there is no booking with the same id,row,seat found
				else
				{
					details="\nThe booking with id "+prefix+"_"+id+"-"+seatNumber+", not found\n";
					break;
				}
			}
		}
		return details;
	}
	//the method that is called from facade to make a business booking
	public void seedData() 
	{
		//what to do if the bookings array of index 0 is not null
		if(bookings[0]!=null)
		{
			details="\nError - There already exist a booking\n";
		}
		//what to do if the bookings array of index 0 is null
		else
		{
			//the code that  is supposed to be try to be checked of any error 
			try
			{
				bookings[0] = new Economy("EAAA","A","3",40);
			
				bookings[1] = new Economy("EBBB","B","4",40);
				bookings[1].book("Anndy", "Tanuri");
			
				bookings[2] = new Economy("ECCC","A","1",0);
				bookings[2].book("Hans", "Bonaventura");
				bookings[2].checkInBag("Bonaventura", 12);
				bookings[2].checkInBag("Bonaventura", 1.53);
			
				bookings[3] = new Economy("EDDD","F","6",40);
				bookings[3].book("Gomel", "Sei");
				bookings[3].checkInBag("Sei", 12);
				bookings[3].checkInBag("Sei", 11.31);
			
				bookings[4] = new Economy("EEEE","I","8",0);
				bookings[4].book("Amelia", "Agatha");
				bookings[4].checkInBag("Agatha", 5.3);
				bookings[4].checkInBag("Agatha", 10.3);
				bookings[4].checkInBag("Agatha", 2.8);
				bookings[4].collectBags(new DateTime());
			
				bookings[5] = new Business("BFFF","A","9",0);
				
				bookings[6] = new Business("BGGG","C","8",0);
				bookings[6].book("Babi", "Guling");
			
				bookings[7] = new Business("BHHH","E","1",0);
				bookings[7].book("Yosua", "Bunga");
				bookings[7].checkInBag("Bunga", 5.21);
				bookings[7].checkInBag("Bunga", 6.39);
			
				bookings[8] = new Business("BIII","G","6",0);
				bookings[8].book("Edwin", "Kys");
				bookings[8].checkInBag("Kys", 10.14);
				bookings[8].checkInBag("Kys", 19.91);
			
				bookings[9] = new Business("BJJJ","D","5",0);
				bookings[9].book("Robert", "Gans");
				bookings[9].checkInBag("Gans", 4.61);
				bookings[9].checkInBag("Gans", 9.09);
				bookings[9].checkInBag("Gans", 5.78);
				bookings[9].collectBags(new DateTime());
				
				bookings[10] = new Business("BKKK","H","5",1200);
				bookings[10].book("Kentut", "Kuda");
				bookLimosine("BKKK","H5");
			
				bookings[11] = new Business("BLLL","C","6",0);
				bookings[11].book("Mario", "Hitam");
			}
			//if either of the exception class do not work, it will do this
			catch(NumberFormatException | InvalidID | InvalidSeat | InvalidRow  e)
			{
				System.out.println(e.getMessage());
			}
			details="Random bookings have been made\n";
		}
		System.out.println(details);
	}
	//the method that is called from facade to make a business booking
	public boolean bookLimosine(String id, String seatNumber) 
	{
		fee+=1200;
		rowNumber=seatNumber.substring(0,1);
		this.seatNumber=seatNumber.substring(1);
		String Id=id.substring(1,4);
		for(int i=0;i<bookings.length; i++)
		{
			//what to do if the booking of a certain index is not null
			if(bookings[i]!=null)
			{
				//getting the value of id, row, seat from booking of certain index
				String ID=bookings[i].getId();
				String prefix=ID.substring(0,1);
				this.id=prefix+Id;
				String row=bookings[i].getRowNumber();
				String seat=bookings[i].getSeatNumber();
				//comparing the value of inputed id, row, seat and last name with the id, row, seat with from booking of certain index
				if(this.id.equals(ID) && rowNumber.equals(row) && this.seatNumber.equals(seat))
				{
					fee=0;	
					limosine = true;
					//calling the method limosine of Business class and sending the value of limosine
					bookings[i].limosine(limosine);
					break;
				}
			}
		}
		return false;
	}
	//the method that is called from facade to make a business booking
	public void writeData()
	{
		//the code that  is supposed to be try to be checked of any error 
		try
		{
			//making a new object called output of PrintWriter class
			output = new PrintWriter(file);
			for(int i=0; i<bookings.length;i++)
			{
				//what to do if the booking of a certain index is not null
				if(bookings[i]!=null)
				{
					//adding a new line that is get from the method toString of class booking
					output.println(bookings[i].toString());
				}
			}
			//closing the output
			output.close();
		}
		//what to do if there is an errors
		catch(IOException e)
		{
			System.out.println("Error - Unable to save bookings");
		}
	}
	//the method that is called from facade to make a business booking
	public void readData()
	{
		String bookingString;
		//the code that  is supposed to be try to be checked of any error 
		try
		{
			//creating a new object called scanFile of scanner class
			scanFile = new Scanner(file);
		}
		//what to do if there is an errors
		catch(FileNotFoundException e)
		{
			System.out.println("Error - "+file+" not found");
			System.out.println("No data has been added\n");
		}
		//what to do if scanFile is not null
		if(scanFile!=null)
		{
			//a loop that keeps on going while scanFile has a line
			while(scanFile.hasNext())
			{
				//getting the value of bookingString
				bookingString = scanFile.next();
				//creating an array that is split by :
				String data[] = bookingString.split(":");
				//assigning the value of data[0]
				String dataID = data[0];
				String dataPrefix = dataID.substring(0,1);
				//assigning the value of data[1]
				String dataRow = data[1];
				//assigning the value of data[2]
				String dataSeat = data[2];
				String dataRowSeat = dataRow + dataSeat;
				//assigning the value of data[3]
				String dataFee = data[3];
				//assigning the value of data[4]
				String dataFirst = data[4];
				//assigning the value of data[5]
				String dataLast = data[5];
				//what to do if dataPrefix is E
				if(dataPrefix.equals("E"))
				{
					//assigning the value of data[6]
					String dataER = data[6];
					//what to do of dataER is ER
					if(dataER.equals("ER"))
					{
						fee = 40;
					}
					//the code that are supposed to be tried
					try
					{
						//calling the Economy class and sending the value of id, row, seat and fee
						booking = new Economy(dataID, dataRow, dataSeat, fee);
						bookings[counter] = booking;
					}
					//what to do if the is/ are (an) error(s)
					catch(InvalidID|InvalidRow|InvalidSeat e)
					{
						System.out.println(e.getMessage());
					}
					fee=0;
				}
				//what to do if dataPrefix is B
				else
				{
					//assigning the value if data[6]
					String dataLimo = data[6];
					//the code that are supposed to be tried
					try
					{
						//what to do if dataLimo is LI
						if(dataLimo.equals("LI"))
						{
							fee=1200;
							//calling the Business class and sending the value of id, row, seat and fee
							booking = new Business(dataID, dataRow, dataSeat, fee);
							bookings[counter] = booking;
							//calling the method bookLimosine and sending the value of id, row, and seat
							bookLimosine(dataID, dataRowSeat);
							fee=0;
						}
						//what to do if dataLimo is not LI
						else
						{
							booking = new Business(dataID, dataRow, dataSeat, fee);
							bookings[counter] = booking;
						}
					}
					//what to do if there is an error
					catch(InvalidID|InvalidRow|InvalidSeat e)
					{
						System.out.println(e.getMessage());
					}
				}
				//what to do if first and last name is not null
				if(!(dataFirst.equals("null") && dataLast.equals("null")))
				{
					//sending the value of first and last name to the method book in Booking class
					booking.book(dataFirst, dataLast);
				}
				//what to do if there is more or equal to 8 indexes
				if(data.length>=8)
				{
					//assigning the value of data[0]
					String dataBaggageID = data[7];
					//assigning the value of data[0]
					double dataWeight = Double.parseDouble(data[8]);
					//assigning the value of data[0]
					String dataCheckIn = data[9];
					//assigning the value of data[0]
					String dataCollect = data[10];
					//what to do if there is more or equal to 12 indexes
					if(data.length>=12)
					{
						//assigning the value of data[0]
						double dataWeight1 = Double.parseDouble(data[12]);
						//what to do if there is more or equal to 16 indexes
						if(data.length>=16)
						{
							//assigning the value of data[0]
							double dataWeight2 = Double.parseDouble(data[16]);		
							//calling the method getDateTime and sending the value of check in 
							DateTime dataCheckInDate = booking.getDateTime(dataCheckIn);
						}
					}
				}
				counter++;
			}
			System.out.println("The bookings in "+file+" has been inputted\n");
		} 			
	}
}