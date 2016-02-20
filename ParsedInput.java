package Assignment3;

public class ParsedInput {
	String command;
	String category;
	String name;
	double price;
	double quantity;
	int weight;
	String optional1;
	String optional2;
	
	ParsedInput(String operation, String cat, String item, double value, double amount, int mass, String op1, String op2)
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
		price = quantity = weight = 0;
		optional1 = optional2 = "";
	}
	
}


