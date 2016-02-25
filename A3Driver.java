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
			return;
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
			  
			  addItem.setName(parsedInput.name);
			  addItem.setPrice(parsedInput.price);
			  addItem.setQuantity(parsedInput.quantity);
			  addItem.setWeight(parsedInput.weight);
			  addItem.setOptional1(parsedInput.optional1);
			  addItem.setOptional2(parsedInput.optional2);
			  
			  addItem.setPrice(addItem.calculatePrice());
			  
			  sortInsert(addItem, cart);
			  return cart;
		  }
		  
		  // Notifies user of the total number of a specified item that was ordered
		  else if (parsedInput.command.equalsIgnoreCase("Search")){
			  int itemFound = 0;
			  for (int i = 0; i < cart.size(); i++){
				  if (cart.get(i).getName().equals(parsedInput.name)){
					  itemFound += cart.get(i).getQuantity(); //add the quantity of each matched item to the itemFound number
				  }
			  }
			  JOptionPane.showMessageDialog(frame, "Searched " + parsedInput.name + " " + itemFound ); //just output name and itemFound amount
		  }
		  
		  // Deletes all orders of a specified item that was ordered
		  else if (parsedInput.command.equalsIgnoreCase("Delete")){
			  int removed = 0;
			  for (int i = 0; i < cart.size(); i++) {
				  if (cart.get(i).getName().equals(parsedInput.name)){
					  removed += cart.get(i).getQuantity(); //adding the amount we removed for each item
					  cart.remove(i);
					 i--;
				  }
			  }
			  if (removed == 0){
				  JOptionPane.showMessageDialog(frame, "The item " + parsedInput.name + " was not available for deletion");
				  return cart;
			  }
			  JOptionPane.showMessageDialog(frame, "Deleted " + parsedInput.name + " of the quantity " + removed);
		  }
		  
		  // Sets the quantity of the first order of a specified item 
		  else if (parsedInput.command.equalsIgnoreCase("Update")){
			  int itemFound = 0;
			  int i = 0;
			  while(i < cart.size() && itemFound == 0){
				  if (cart.get(i).getName().equals(parsedInput.name)){
					  cart.get(i).setQuantity(parsedInput.quantity); //sets the new quantity to the input quantity
					  itemFound = 1;
				  }
				  else {i++;}
			  }
			  if(itemFound == 0 )
			  {
				  JOptionPane.showMessageDialog(frame,  "Invalid name, please try again");
				  return cart;
			  }
			  JOptionPane.showMessageDialog(frame, "Updated " + cart.get(i).getName() + " to the quantity " + cart.get(i).getQuantity());
		  }
		  
		  // Prints out the attributes of all the items in the cart
		  else if (parsedInput.command.equalsIgnoreCase("Print")){
			  if(cart.size() == 0)
			  {
				  JOptionPane.showMessageDialog(frame, "No Objects. Total Charge = $0.00");
			  }
			  double total = 0;
			 for (int i = 0; i < cart.size(); i++) {
				 cart.get(i).printItemAttributes();
				 total += cart.get(i).getPrice();	//add the price of each item to the total
			 }
			 JOptionPane.showMessageDialog(frame,"Total Charge: $" + total); //print the total of the whole cart
		  }
		  
		  return cart;
	  }
	  
	  public static ParsedInput parseInput(String arg)
	  {
		  ParsedInput inputLine = new ParsedInput();
		  JFrame frame = new JFrame("Shopping Cart");
		  String[] splitArg = arg.split("\\s+"); //takes in the input and splits it by spaces
		  int inputlength = splitArg.length;
		 if(splitArg[0].equalsIgnoreCase("insert"))
		 {//if it is insert command, can have up to six arguments.
			 if(inputlength < 6) //not a valid insert input
			 {
				 JOptionPane.showMessageDialog(frame,"Invalid Insert parameters");
				 return inputLine;
			 }
			//need to check if the category is valid
			 else if(splitArg[1].equalsIgnoreCase("Groceries"))
			 {//need to check if the price, quantity, and weight are valid numbers
				 if(inputlength < 7){return inputLine;} //not enough to fill in the blanks for our command
				 double money = stringtoDec(splitArg[3]);
				 if(money == -1)
				 {
					 JOptionPane.showMessageDialog(frame,"Invalid price");
					 return inputLine;
				 } //check the price is right
				int weight = parseNum(splitArg[5]); //parse the weight string to see if it is a whole number
				 if(weight == -1){
					 JOptionPane.showMessageDialog(frame,"Invalid weight");
					 return inputLine;
				 }
				 if(!isValidNumber(splitArg[4])){
					 JOptionPane.showMessageDialog(frame,"Invalid Quantity");
					 return inputLine;
				 }
				
				 int quantity = parseNum(splitArg[4]);
				 if(quantity < 0){
					 JOptionPane.showMessageDialog(frame,"Invalid Quantity"); //overflow detected
					 return inputLine;
				 }
				 				
				 if(splitArg[6].equalsIgnoreCase("p") || splitArg[6].equalsIgnoreCase("np")){
					 ParsedInput insertInput = new ParsedInput("insert", "Groceries", splitArg[2], money, quantity, weight, splitArg[6], "");
						return insertInput;
				 }
				 else {
					 JOptionPane.showMessageDialog(frame,"Invalid Input");
					 return inputLine;
					 }
				
				 
			 }
			 else if(splitArg[1].equalsIgnoreCase("Clothing"))
			 {
				 			 
				 double money = stringtoDec(splitArg[3]);
				 if(money == -1)
				 {
					 JOptionPane.showMessageDialog(frame,"Invalid price");
					 return inputLine;
				 } //check the price is right
				 int weight = parseNum(splitArg[5]);
				 if(weight == -1){
					 JOptionPane.showMessageDialog(frame,"Invalid weight");
					 return inputLine;
				 }
				  
				 if(!isValidNumber(splitArg[4])){
					 JOptionPane.showMessageDialog(frame,"Invalid Quantity");
					 return inputLine;
				 }
				 
				 int quantity = parseNum(splitArg[4]);
				 if(quantity < 0) //check for integer overflow
				 {
					 JOptionPane.showMessageDialog(frame,"Invalid Quantity");
					 return inputLine;
				 }
				 
				 ParsedInput insertInput = new ParsedInput("insert", "Clothing", splitArg[2], money, quantity, weight, "", "");
				 return insertInput;
				 
			 }
			 else if(splitArg[1].equalsIgnoreCase("Electronics"))
			 {	String states = "AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA MD MA MI MN"
			 		+ " MS MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI WY";
				 if(inputlength < 8)
				 {
					 JOptionPane.showMessageDialog(frame,"Invalid insert for Electronics");
					 return inputLine;
				 }
				 double money = stringtoDec(splitArg[3]);
				 if(money == -1)
				 {
					 JOptionPane.showMessageDialog(frame,"Invalid price");
					 return inputLine;
				 } //check the price is right
				 int weight = parseNum(splitArg[5]);

				 if(weight == -1){
					 JOptionPane.showMessageDialog(frame,"Invalid weight");
					 return inputLine;
				 }
				 if(!isValidNumber(splitArg[4])) //quantity is a string
				 {
					 JOptionPane.showMessageDialog(frame,"Invalid Quantity");
					 return inputLine;
				 }
				 int quantity = parseNum(splitArg[4]);
				 if(quantity < 0) //check for integer overflow
				 {
					 JOptionPane.showMessageDialog(frame,"Invalid Quantity");
					 return inputLine;
				 }
			
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
				 JOptionPane.showMessageDialog(frame,"Invalid Insert parameters");
			
				 return inputLine;
			 }			 
		 }
		 else if(splitArg[0].equalsIgnoreCase("search"))//gives a name 
		 {
			 if(inputlength != 2){ //not valid command
				 JOptionPane.showMessageDialog(frame,"Invalid Search parameters");
				 return inputLine;
			 }
			 ParsedInput inputParse = new ParsedInput("search", "", splitArg[1], 0.0, 0, 0, "", "");
			 return inputParse;
		 }
		 else if(splitArg[0].equalsIgnoreCase("delete")){//gives a name
			 if(inputlength != 2){ //not valid command
				 JOptionPane.showMessageDialog(frame,"Invalid Delete parameters");
				 return inputLine;
			 }
			 ParsedInput inputParse = new ParsedInput("delete", "", splitArg[1], 0.0, 0, 0, "", "");
			 return inputParse;
		 }
		 else if(splitArg[0].equalsIgnoreCase("update")){//gives a name and a number
			 if (inputlength != 3){
				 JOptionPane.showMessageDialog(frame,"Invalid Update parameters");
				 return inputLine;
			 }
			 int quantity = parseNum(splitArg[2]); //changing the quantity from a string to an integer
			 if(quantity < 0)
			 {
				 JOptionPane.showMessageDialog(frame,"Invalid Quantity");
				 return inputLine;
			 }
			 ParsedInput inputParse = new ParsedInput("update", "", splitArg[1], 0.0, quantity, 0, "", "");
			 return inputParse;
		 }
		 else if(splitArg[0].equalsIgnoreCase("print")){
			 ParsedInput inputParse = new ParsedInput("print", "", "", 0.0, 0, 0, "", "");
			 return inputParse;
		 }
		 else //not a valid command output a message.
		 {	
			 JOptionPane.showMessageDialog(frame,"Invalid Input");
			 return inputLine;
		 }
		 JOptionPane.showMessageDialog(frame,"Invalid Input");
		 return inputLine;
	  }
/*
 * checks if the input given is a valid decimal integer from a string
 */
	public static double stringtoDec(String money) {
		if(money.matches("\\d +\\d+\\.") || money.matches("\\d + \\.")){return -1;} //"886." "8." are not valid inputs
		
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
			int compareName = addItem.getName().compareToIgnoreCase(shoppingCart.get(i).getName());
		
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
	
	public static int parseNum(String num)
	{
		String[] splitNum = num.split("\\."); //split the weight by decimal (if weight is 3.00)
		if(splitNum.length == 1)
		{//valid weight -- integer!
			if(splitNum[0].matches("\\d+\\d") || splitNum[0].matches("\\d"))
			{
				int newNum = Integer.parseInt(splitNum[0]);
				if(newNum < 0) //overflow detection, if it is an incredibly large number, will be negative
				{
					return -1;
				}
				return newNum;
			}
			else return -1; //not a valid number.
			
		}
		else if(splitNum.length == 2)//has two parts, must be decimal -- 
		{
			if(splitNum[0].matches("\\d+\\d") || splitNum[0].matches("\\d")) //check the first decimal
			{
				int firstdec = Integer.parseInt(splitNum[0]);
				if(splitNum[1].matches("\\d+\\d")|| splitNum[1].matches("\\d")) //check second decimal as a number
				{
					int secdec = Integer.parseInt(splitNum[1]); //parse the second decimal.
					if(secdec != 0)
					{
						return -1; //not a valid weight
					}
					if(firstdec < 0 ){return -1;} //overflow, too big of a number--went negative
					
					int finalNum = firstdec + secdec/100;
					return finalNum;
					
				}
			}
			return -1;
		}
		return -1; //not a valid input
		
	}
	}
