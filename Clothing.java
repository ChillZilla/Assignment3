/* WE DIDN'T FORGET THE HEADER :)
 * Names: Amanda Akin aa44462
 * 		  Max Archibald mma2629
 * Lab time : 9:30 - 11:00 am 
 * Assignment3 Shopping Cart
 */
package Assignment3;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Clothing extends Item 
{

	// variables, constructors as necessary
	Clothing(){
		this.setName("");
		this.setPrice(0.0);
		this.setQuantity(0);
		this.setWeight(0);
		this.setOptional1("");
		this.setOptional2("");	
	}
	
	Clothing(String label, double value, int howmuch, int mass, String op1, String op2)
	{
		this.setName(label);
		this.setPrice(value);
		this.setQuantity(howmuch);
		this.setWeight(mass);
		this.setOptional1(op1);
		this.setOptional2(op2);
	}
	
	double calculatePrice () 
	{
		
		double salesTax = 1.10;
		double multiply = (this.getPrice() * salesTax* 100.00)/100.00;
		multiply = (this.getQuantity() * multiply + this.getWeight() * 20 * this.getQuantity()*100.00)/100.00; //calculate shipping
		
		return (multiply); //return the sales tax with the shipping
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
		JFrame frame = new JFrame("Shopping Cart");
		JOptionPane.showMessageDialog(frame, "A total of " + this.getQuantity() + " " + this.getName() + " for a total of $" + this.getPrice(),  this.getName() + " attributes", JOptionPane.INFORMATION_MESSAGE);
	}
	

}
