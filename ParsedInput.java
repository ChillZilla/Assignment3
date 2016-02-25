/* WE DIDN'T FORGET THE HEADER :)
 * Names: Amanda Akin aa44462
 * 		  Max Archibald mma2629
 * Lab time : 9:30 - 11:00 am 
 * Assignment3 Shopping Cart
 */
package Assignment3;

public class ParsedInput {
	String command;
	String category;
	String name;
	double price;
	int quantity;
	int weight;
	String optional1;
	String optional2;
	
	ParsedInput(String operation, String cat, String item, double value, int amount, int mass, String op1, String op2)
	{
		command = operation;
		category = cat;
		name = item;
		price = value;
		quantity = amount;
		weight = mass;
		optional1 = op1;
		optional2 = op2;
	}
	ParsedInput()
	{
		command = category = name = "";
		price = quantity = 0;
		weight = 0;
		optional1 = optional2 = "";
	}
	
}


