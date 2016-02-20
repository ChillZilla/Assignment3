package Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

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
					//String pigLatin = translator.translate(s);
					//System.out.println(pigLatin);
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
		  ArrayList<Item> dummylist = new ArrayList<Item>();
		  ParsedInput parsedInput = parseInput(input);
		  
		  if (parsedInput.command.equalsIgnoreCase("Insert")){
			  
		  }
		  else if (parsedInput.command.equalsIgnoreCase("Search")){
			  int i = 0;
			  int itemFound = 0;
			  dummylist.size();
		  }
		  else if (parsedInput.command.equalsIgnoreCase("Delete")){
			  
		  }
		  else if (parsedInput.command.equalsIgnoreCase("Update")){
			  
		  }
		  else if (parsedInput.command.equalsIgnoreCase("Print")){
			  
		  }
		  
		  return dummylist;
	  }
	  
	  public static ParsedInput parseInput(String arg)
	  {
		  ParsedInput inputLine = new ParsedInput();
		  //String[] commands = {"insert", "search", "delete", "update", "print"};
		  String[] splitArg = arg.split("\\s+"); //takes in the input and splits it by spaces
		  
		 if(splitArg[0].equalsIgnoreCase("insert"))
		 {//if it is insert command, can have up to six arguments.
			 if(splitArg.length < 6) //not a valid insert input
			 {
				 return inputLine;
			 }
			//need to check if the category is valid
			 else if(splitArg[1].equalsIgnoreCase("Groceries"))
			 {//need to check if the price, quantity, and weight are valid numbers
				 if(stringtoDec(splitArg[3])== -1){} //check the price is right
				 if(stringtoDec(splitArg[5]) == -1){} //check the weight is valid
				 if(splitArg[3].matches("[(0-9)]")){}
				 
			 }
			 else if(splitArg[1].equalsIgnoreCase("Clothing"))
			 {
				 
			 }
			 else if(splitArg[1].equalsIgnoreCase("Electronics"))
			 {
				 
			 }
			 else
			 {
				 //INVALID 
			 }
			
			 
			 
		 }
		 else if(splitArg[0].equalsIgnoreCase("search"))
		 {
			 
		 }
		 else if(splitArg[0].equalsIgnoreCase("delete")){}
		 else if(splitArg[0].equalsIgnoreCase("update")){}
		 else if(splitArg[0].equalsIgnoreCase("print")){}
		 else //not a valid command output a message.
		 {
			 return inputLine;
		 }
		
		 return inputLine;
	  }
/*
 * checks if the input given is a valid decimal integer from a string
 */
	public static float stringtoDec(String money) {
		String[] moneySplit = money.split(".");
		if (moneySplit.length == 1) {
			if (moneySplit[0].matches("[(0-9)]")) {
				float totalMoney = Integer.parseInt(moneySplit[0]);
				return totalMoney;
			}
		
		}
		else if(moneySplit.length == 2)
		{
			if (moneySplit[0].matches("[(0-9)]")){
				if(moneySplit[1].matches("[(0-9)]"))
				{
					float firstdec = Integer.parseInt(moneySplit[0]);
					float secdec = Integer.parseInt(moneySplit[1]);
					float total = firstdec + secdec/100;
					return total;
				}
				else{return -1;}
			}
			else{return -1;}
		}
		else{return -1;}
		
		return -1;
	}
	
	public static boolean isValidWord(String word)
	{	
			if(word.matches("[a-zA-Z]"))
			{
				return true;
			}
		
		
		return false;
	}
}
