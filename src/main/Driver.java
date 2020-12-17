package main;
import application.Menu;

public class Driver 
 {
	//the main method that is used to run everything
	public static void main(String[] args)  
	{
		//creating a new object called menu of class menu
		Menu menu = new Menu();
		
		String choice = "";
		//a loop that will only stop if the method getInput isEX
		while(!(choice.equals("EX")))
		{
			System.out.println(menu.toString());
			choice=menu.getInput();
		}
	}
}
