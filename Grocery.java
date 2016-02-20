package Assignment3;

public class Grocery extends Item {
	//variables, constructor here
	
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	Grocery(String label, double value, int howmuch, double mass, String op1, String op2)
	{
		this.name = label;
		this.price = value;
		this.quantity = howmuch;
		this.weight = mass;
		this.optional1 = op1;
		this.optional2 = op2;
	}
	
	double calculatePrice() //no sales tax for groceries
	{
		if(this.optional1.equals("p")){ //need an extra 20% on top of normal shipping
			return (this.price * this.weight * 20 * 1.2 * 100.00)/100.00;
		}
		
		return (this.price * this.weight * 20 * 100.00)/100.00; //normal standard shipping
	}
	
}
