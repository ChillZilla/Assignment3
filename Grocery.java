package Assignment3;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Grocery extends Item {
	//variables, constructor here
	
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	Grocery(){
		this.setName("");
		this.setPrice(0.0);
		this.setQuantity(0);
		this.setWeight(0);
		this.setOptional1("");
		this.setOptional2("");	
	}
	
	Grocery(String label, double value, int howmuch, int mass, String op1, String op2)
	{
		this.setName(label);
		this.setPrice(value);
		this.setQuantity(howmuch);
		this.setWeight(mass);
		this.setOptional1(op1);
		this.setOptional2(op2);
	}
	
	double calculatePrice() //no sales tax for groceries
	{
		if(this.getOptional1().equalsIgnoreCase("p")){ //need an extra 20% on top of normal shipping
			return (this.getPrice() * this.getQuantity() + this.getQuantity() * this.getWeight() * 20 * 1.2)* 100.00/100.00;
		}
		
		return (this.getPrice() * this.getQuantity() + this.getQuantity() * this.getWeight() * 20) * 100.00/100.00; //normal standard shipping
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
		JFrame frame = new JFrame("Shopping Cart");
		JOptionPane.showMessageDialog(frame, this.getName() + " " + this.getPrice()+ " " + this.getQuantity() , this.getName() + " attributes", JOptionPane.INFORMATION_MESSAGE);
	}
}
