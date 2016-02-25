/* WE DIDN'T FORGET THE HEADER :)
 * Names: Amanda Akin aa44462
 * 		  Max Archibald mma2629
 * Lab time : 9:30 - 11:00 am 
 * Assignment3 Shopping Cart
 */
package Assignment3;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Electronics extends Item 
{

	// Variables, constructors etc. here.
	Electronics(){
		this.setName("");
		this.setPrice(0.0);
		this.setQuantity(0);
		this.setWeight(0);
		this.setOptional1("");
		this.setOptional2("");
		
	}
	
	Electronics(String label, double value, int howmuch, int mass, String op1, String op2)
	{
		this.setName(label);
		this.setPrice( value);
		this.setQuantity(howmuch);
		this.setWeight(mass);
		this.setOptional1(op1);
		this.setOptional2(op2);
	}
	
	//Implement calculate price/print methods as necessary
	double calculatePrice () 
	{
		double premiumship = 1.2;
		double shipping = 20;
		
		String States = "NM TX VA AZ AK";
		if(States.contains(this.getOptional2())) //then this state has no sales tax
		{
			if(this.getOptional1().equalsIgnoreCase("f")){
				//premium shipping
				double premium = (this.getPrice() * this.getQuantity() + premiumship * shipping * this.getWeight() * this.getQuantity()) * 100.00/100.00;
				return premium;
		 }
			//no premium shipping
			double nopremium = (this.getPrice() * this.getQuantity() + shipping * this.getWeight() * this.getQuantity()) * 100.00/100.00;
			return nopremium;
		}
		double salesTax = 1.10;
		if(this.getOptional1().equalsIgnoreCase("f")){
			//fragile, need premium shipping
			double fragile = (this.getPrice() * this.getQuantity() * salesTax + premiumship * shipping * this.getWeight() * this.getQuantity()) * 100.00/100.00;
			return fragile;
		}
		//otherwise not fragile, no premium shipping, and sales tax applies.
		double multiply = (this.getPrice() * salesTax) * 100.00/100.00;
		multiply = (this.getQuantity() * multiply + this.getWeight() * shipping * this.getQuantity()) * 100.00/100.00; //calculate shipping
		
		return (multiply); //return the sales tax with the shipping
	}
	//fragile requires premium shipping
	//certain states have no sales tax
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
		JFrame frame = new JFrame("Shopping Cart");
		JOptionPane.showMessageDialog(frame,  "A total of " + this.getQuantity() + " " + this.getName() + " for a total of $" + this.getPrice() , this.getName() + " attributes", JOptionPane.INFORMATION_MESSAGE); 
	}
}
