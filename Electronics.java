package Assignment3;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Electronics extends Item 
{

	// Variables, constructors etc. here.
	Electronics(){
		name = "";
		price = 0;
		quantity = 0;
		weight = 0;
		optional1 = "";
		optional2 = "";
	}
	
	Electronics(String label, double value, int howmuch, double mass, String op1, String op2)
	{
		this.name = label;
		this.price = value;
		this.quantity = howmuch;
		this.weight = mass;
		this.optional1 = op1;
		this.optional2 = op2;
	}
	
	//Implement calculate price/print methods as necessary
	double calculatePrice () 
	{
		double premiumship = 1.2;
		double shipping = 20;
		
		String States = "NMTXVAAZAK";
		if(States.contains(this.optional2)) //then this state has no sales tax
		{
			if(this.optional1.equalsIgnoreCase("f")){
				//premium shipping
				double premium = (this.price * this.quantity + premiumship * shipping * this.weight * this.quantity) * 100.00/100.00;
				return premium;
			}
			//no premium shipping
			double nopremium = (this.price * this.quantity + shipping * this.weight * this.quantity) * 100.00/100.00;
			return nopremium;
		}
		double salesTax = 1.10;
		if(this.optional1.equalsIgnoreCase("f")){
			//fragile, need premium shipping
			double fragile = (this.price * this.quantity * salesTax + premiumship * shipping * this.weight * this.quantity) * 100.00/100.00;
			return fragile;
		}
		//otherwise not fragile, no premium shipping, and sales tax applies.
		double multiply = (this.price * salesTax) * 100.00/100.00;
		multiply = (this.quantity * multiply + this.weight * shipping * this.quantity) * 100.00/100.00; //calculate shipping
		
		return (multiply); //return the sales tax with the shipping
	}
	//fragile requires premium shipping
	//certain states have no sales tax
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
		JFrame frame = new JFrame("Shopping Cart");
		JOptionPane.showMessageDialog(frame, "Name: " + this.name + " Price: "+this.price + " Quantity: "
		+ this.quantity + " Weight: "+ this.weight + " Fragile? " + this.optional1 + " State: " + this.optional2, this.name + " attributes",  JOptionPane.INFORMATION_MESSAGE);
	}
}
