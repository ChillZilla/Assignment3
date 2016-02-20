package Assignment3;

public class Clothing extends Item 
{

	// variables, constructors as necessary
	Clothing(String label, double value, int howmuch, double mass, String op1, String op2)
	{
		this.name = label;
		this.price = value;
		this.quantity = howmuch;
		this.weight = mass;
		this.optional1 = op1;
		this.optional2 = op2;
	}
	
	double calculatePrice () 
	{
		
		double salesTax = 1.10;
		double multiply = (this.price * salesTax* 100.00)/100.00;
		multiply = (multiply * this.weight * 20 * this.quantity* 100.00)/100.00; //calculate shipping
		
		return (multiply); //return the sales tax with the shipping
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
	}
	

}
