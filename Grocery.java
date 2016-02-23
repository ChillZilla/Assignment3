package Assignment3;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Grocery extends Item {
	//variables, constructor here
	
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	Grocery(){
		name = "";
		price = 0;
		quantity = 0;
		weight = 0;
		optional1 = "";
		optional2 = "";
	}
	
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
		if(this.optional1.equalsIgnoreCase("p")){ //need an extra 20% on top of normal shipping
			return (this.price * this.quantity + this.quantity * this.weight * 20 * 1.2)* 100.00/100.00;
		}
		
		return (this.price * this.quantity + this.quantity * this.weight * 20) * 100.00/100.00; //normal standard shipping
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
		JFrame frame = new JFrame("Shopping Cart");
		JOptionPane.showMessageDialog(frame, "Name: " + this.name + " Price: "+this.price + " Quantity: "
		+ this.quantity + " Weight: "+ this.weight + " Perishable? " + this.optional1, this.name + " attributes", JOptionPane.INFORMATION_MESSAGE);
	}
}
