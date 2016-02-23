package Assignment3;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Clothing extends Item 
{

	// variables, constructors as necessary
	Clothing(){
		name = "";
		price = 0;
		quantity = 0;
		weight = 0;
		optional1 = "";
		optional2 = "";
	}
	
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
		multiply = (this.quantity * multiply + this.weight * 20 * this.quantity) * 100.00/100.00; //calculate shipping
		
		return (multiply); //return the sales tax with the shipping
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
		JFrame frame = new JFrame("Shopping Cart");
		JOptionPane.showMessageDialog(frame,  "Name: " + this.name + " Price: "+this.price + " Quantity: "
				+ this.quantity + " Weight: "+ this.weight,  this.name + " attributes", JOptionPane.INFORMATION_MESSAGE);
	}
	

}
