package Assignment3;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?
	String name;
	double price;
	int quantity;
	double weight;
	String optional1;
	String optional2;

// You will need a constructor (Why?). Create it here.
	Item(String item, String cost, double value, int amount, double mass, String op1, String op2){
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
	
	double calculatePrice () 
	{
		double final_price = 0;
		// Insert price calculation here
		return final_price;
	}
	

	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
	}

}
