package Assignment3;

public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?
	String name;
	int price;
	int quantity;
	int weight;
	String optional1;
	String optional2;

// You will need a constructor (Why?). Create it here.
	Item(String item, String cost, int value, int amount, int mass, String op1, String op2){
		name = item;
		price = value;
		quantity = amount;
		weight = mass;
		optional1 = op1;
		optional2 = op2;
	}
	
	Item(){
		name = "";
		price = 0;
		quantity = 0;
		weight = 0;
		optional1 = "";
		optional2 = "";
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		// Insert price calculation here
		return final_price;
	}
	

	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
	}

}
