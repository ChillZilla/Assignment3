package Assignment3;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?
private	String name;
private	double price;
private	int quantity;
private	int weight;
private	String optional1;
private	String optional2;

// You will need a constructor (Why?). Create it here.
	Item(String item, String cost, double value, int amount, int mass, String op1, String op2){
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getOptional1() {
		return optional1;
	}

	public void setOptional1(String optional1) {
		this.optional1 = optional1;
	}

	public String getOptional2() {
		return optional2;
	}

	public void setOptional2(String optional2) {
		this.optional2 = optional2;
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
