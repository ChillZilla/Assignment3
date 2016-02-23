/* WE DIDN'T FORGET THE HEADER :)
 * Names: Amanda Akin aa44462
 * 		  Max Archibald mma2629
 * Lab time : 9:30 - 11:00 am 
 * Assignment3 Shopping Cart
 */
package Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class A3Driver
	{

	  public static void main(String[] args) 
	  {
		// TODO Auto-generated method stub
		
		//Open file; file name specified in args (command line)
		
		//Parse input, take appropriate actions.
		
		//Stub for arraylist.
	
		  if (args.length != 1) 
			{
				System.err.println ("Error: Incorrect number of command line arguments");
				System.exit(-1);
			}
			processLinesInFile (args[0]);
		  
		  
		  
		  
		  
		// ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
			//Used in process Lines In file
		
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		//Iterator<Item> i = shoppingCart.iterator();
		//while (i.hasNext()) 
		//{
			//Item temp = i.next();
			//temp.calculatePrice(); 
			//temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke the calculatePrice () method defined in Grocery.
		//}		
	  }
	 
	  
	  public static void processLinesInFile (String filename) 
		{ 
		  ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		  
			try 
			{
				FileReader freader = new FileReader(filename);
				BufferedReader reader = new BufferedReader(freader);
				
				for (String s = reader.readLine(); s != null; s = reader.readLine()) 
				{
					createCart(shoppingCart, s);
				}
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println ("Error: File not found. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) 
			{
				System.err.println ("Error: IO exception. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			}
		}
	  
	  
	  public static ArrayList<Item> createCart(ArrayList<Item> cart, String input)
	  {
		 // ArrayList<Item> dummylist = new ArrayList<Item>();

		  ParsedInput parsedInput = parseInput(input);
		  if(parsedInput.command == "") //there is not a valid input given, so we couldnt parse it
		  {
			  return cart;
		  }
		  JFrame frame = new JFrame("Shopping Center");
		  
		  // Adds a specified item to the shopping cart
		  // Attempts to typecast superclass to subclass
		  if (parsedInput.command.equalsIgnoreCase("Insert")){
			  Item addItem = new Item();
			  
			  if (parsedInput.category.equalsIgnoreCase("Groceries")){
				  Grocery itemType = new Grocery();
				  addItem = itemType;
			  }
			  else if(parsedInput.category.equalsIgnoreCase("Electronics")){
				  Electronics itemType = new Electronics();
				  addItem = itemType;
			  }
			  else if(parsedInput.category.equalsIgnoreCase("Clothing")){
				  Clothing itemType = new Clothing();
				  addItem = itemType;
			  }
			  else {return cart;}
			  
			  addItem.name = parsedInput.name;
			  addItem.price = parsedInput.price;
			  addItem.quantity = parsedInput.quantity;
			  addItem.weight = parsedInput.weight;
			  addItem.optional1 = parsedInput.optional1;
			  addItem.optional2 = parsedInput.optional2;
			  
			  addItem.price = addItem.calculatePrice();
			  
			  sortInsert(addItem, cart);
			  return cart;
		  }
		  
		  // Notifies user of the total number of a specified item that was ordered
		  else if (parsedInput.command.equalsIgnoreCase("Search")){
			  int itemFound = 0;
			  for (int i = 0; i < cart.size(); i++){
				  if (cart.get(i).name.equalsIgnoreCase(parsedInput.name)){
					  itemFound++;
				  }
			  }
			  JOptionPane.showMessageDialog(frame, "There are a total of " + itemFound + " " + parsedInput.name + " in your cart.");
		  }
		  
		  // Deletes all orders of a specified item that was ordered
		  else if (parsedInput.command.equalsIgnoreCase("Delete")){
			  for (int i = 0; i < cart.size(); i++) {
				  if (cart.get(i).name.equalsIgnoreCase(parsedInput.name)){
					  cart.remove(i);
				  }
			  }
		  }
		  
		  // Sets the quantity of the first order of a specified item 
		  else if (parsedInput.command.equalsIgnoreCase("Update")){
			  int itemFound = 0;
			  int i = 0;
			  while(i < cart.size() && itemFound == 0){
				  if (cart.get(i).name.equalsIgnoreCase(parsedInput.name)){
					  cart.get(i).quantity = parsedInput.quantity;
					  itemFound = 1;
				  }
				  i++;
			  }
			  if(itemFound == 0 )
			  {
				  JOptionPane.showMessageDialog(frame,  "Invalid name, please try again");
				  return cart;
			  }
			  JOptionPane.showMessageDialog(frame, cart.get(i).name + " " + cart.get(i).quantity);
		  }
		  
		  // Prints out the attributes of all the items in the cart
		  else if (parsedInput.command.equalsIgnoreCase("Print")){
			 for (int i = 0; i < cart.size(); i++) {
				 cart.get(i).printItemAttributes();
			 }
		  }
		  
		  return cart;
	  }
	  
	  public static ParsedInput parseInput(String arg)
	  {
		  ParsedInput inputLine = new ParsedInput();
		  //String[] commands = {"insert", "search", "delete", "update", "print"};
		  String[] splitArg = arg.split("\\s+"); //takes in the input and splits it by spaces
		  int inputlength = splitArg.length;
		 if(splitArg[0].equalsIgnoreCase("insert"))
		 {//if it is insert command, can have up to six arguments.
			 if(inputlength < 6) //not a valid insert input
			 {
				 return inputLine;
			 }
			//need to check if the category is valid
			 else if(splitArg[1].equalsIgnoreCase("Groceries"))
			 {//need to check if the price, quantity, and weight are valid numbers
				 if(inputlength < 7){return inputLine;} //not enough to fill in the blanks for our command
				 double money = stringtoDec(splitArg[3]);
				 if(money == -1)
				 {
					 return inputLine;
				 } //check the price is right
				 double weight = stringtoDec(splitArg[5]);
				 if(weight == -1){
					 return inputLine;
				 }
				 if(!isValidNumber(splitArg[4])){
					 return inputLine;
				 }
				
				 int quantity = Integer.parseInt(splitArg[4]);
				 
				
				
				 if(splitArg[6].equalsIgnoreCase("p") || splitArg[6].equalsIgnoreCase("np")){
					 ParsedInput insertInput = new ParsedInput("insert", "Groceries", splitArg[2], money, quantity, weight, splitArg[6], "");
						return insertInput;
				 }
				 else {return inputLine;}
				
				 
			 }
			 else if(splitArg[1].equalsIgnoreCase("Clothing"))
			 {
				 			 
				 double money = stringtoDec(splitArg[3]);
				 if(money == -1)
				 {
					 return inputLine;
				 } //check the price is right
				 double weight = stringtoDec(splitArg[5]);
				 if(weight == -1){
					 return inputLine;
				 }
				  
				 if(!isValidNumber(splitArg[4])){
					 return inputLine;
				 }
				 int quantity = Integer.parseInt(splitArg[4]);
				 
				
				 ParsedInput insertInput = new ParsedInput("insert", "Clothing", splitArg[2], money, quantity, weight, "", "");
				 return insertInput;
				 
			 }
			 else if(splitArg[1].equalsIgnoreCase("Electronics"))
			 {	String states = "AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA MD MA MI MN"
			 		+ " MS MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI WY";
				 if(inputlength < 8)
				 {
					 return inputLine;
				 }
				 double money = stringtoDec(splitArg[3]);
				 if(money == -1)
				 {
					 return inputLine;
				 } //check the price is right
				 double weight = stringtoDec(splitArg[5]);

				 if(weight == -1){
					 return inputLine;
				 }
				 if(!isValidNumber(splitArg[4])) //quantity is a string
				 {
					 return inputLine;
				 }
				 int quantity = Integer.parseInt(splitArg[4]);
			
				 if(splitArg[6].equalsIgnoreCase("f") || splitArg[6].equalsIgnoreCase("nf")){
					 if(states.contains(splitArg[7])){
						 
					 ParsedInput insertInput = new ParsedInput("insert", "Electronics", splitArg[2], money, quantity, weight, splitArg[6], splitArg[7]);
					 return insertInput;
					 
					 }
				 }
				 
			 }
			 else
			 {
				 //INVALID 
				 return inputLine;
			 }			 
		 }
		 else if(splitArg[0].equalsIgnoreCase("search"))
		 {
			 if(inputlength < 2){ //not valid command
				 return inputLine;
			 }
			 ParsedInput inputParse = new ParsedInput("search", "", splitArg[1], 0.0, 0, 0.0, "", "");
			 return inputParse;
		 }
		 else if(splitArg[0].equalsIgnoreCase("delete")){
			 if(inputlength != 2){ //not valid command
				 return inputLine;
			 }
			 ParsedInput inputParse = new ParsedInput("delete", "", splitArg[1], 0.0, 0, 0.0, "", "");
			 return inputParse;
		 }
		 else if(splitArg[0].equalsIgnoreCase("update")){
			 if (inputlength != 3){
				 return inputLine;
			 }
			 int quantity = Integer.parseInt(splitArg[2]); //changing the quantity from a string to an integer
			 ParsedInput inputParse = new ParsedInput("update", "", splitArg[1], 0.0, quantity, 0.0, "", "");
			 return inputParse;
		 }
		 else if(splitArg[0].equalsIgnoreCase("print")){
			 ParsedInput inputParse = new ParsedInput("print", "", "", 0.0, 0, 0.0, "", "");
			 return inputParse;
		 }
		 else //not a valid command output a message.
		 {
			 return inputLine;
		 }
		
		 return inputLine;
	  }
/*
 * checks if the input given is a valid decimal integer from a string
 */
	public static double stringtoDec(String money) {
		String[] moneySplit = money.split("\\.");
		
		if (moneySplit.length == 1) {
			if (moneySplit[0].matches("(\\d+\\d)")||moneySplit[0].matches("\\d")) {
				double totalMoney = Integer.parseInt(moneySplit[0]);
				return totalMoney;
			}
		
		}
		else if(moneySplit.length == 2)
		{
			if (moneySplit[0].matches("(\\d+\\d)") || moneySplit[0].matches("\\d")){
				if(moneySplit[1].matches("(\\d+\\d)") || moneySplit[1].matches("\\d"))
				{
					double firstdec = Integer.parseInt(moneySplit[0]);
					double secdec = Integer.parseInt(moneySplit[1]);
					if(secdec < 10){
						secdec = secdec * 10;
					}
					double total = firstdec + secdec/100;
					return total;
				}
				else{return -1;}
			}
			else{return -1;}
		}
		else{return -1;}
		
		return -1;
	}
	
	public static boolean isValidNumber(String word)
	{	
		for(int i = 0; i < word.length(); i++){
			if(word.substring(i, i+1).matches("[a-zA-Z]")){
				return false;
			}
		}
		
		return true;
	}
	

	public static void sortInsert(Item addItem, ArrayList<Item> shoppingCart)
	{
		for(int i = 0; i < shoppingCart.size(); i ++ )
		{
			int compareName = addItem.name.compareToIgnoreCase(shoppingCart.get(i).name);
			if(compareName < 0)
			{
				shoppingCart.add(i, addItem);
				return;
			}
			
		}
		//out of loop, array list must be empty, add item and scram
		shoppingCart.add(addItem);
		return;
	}
	}
