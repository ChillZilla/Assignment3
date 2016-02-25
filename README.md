# Assignment3
422C- Java Assignment for Shopping Cart by Maxwell Archibald and Amanda Akin

About our program: 
Assumptions: 
1) All valid inputs follow the basic structure: Command category name price quantity weight op1 op2

2) Assuming there can be invalid input in all fields above, or completely wrong inputs altogether

3) price is a double type variable

4) weight and quantity should be whole numbers--but inputs like 3.00 for weight or quantity are valid
   
    a) inputs like 3.01 or 4.50 are invalid, because they are not whole numbers

5) all other categories should be Strings and will be treated as such

6) Our classes Clothing, Groceries, and Electronics extend the class Item

7) Each class has it's own calculate price specific to its class

8) Names can have numbers in them, and will be sorted accordingly with names starting with numbers first

9) Our shopping cart is an ArrayList that will sort inputs as our main method inserts them into the cart

10) our main method in A3Driver calls "CreateCart" which takes an empty shopping cart array list and populates it 

    a) populates the list based on the inputs and if they are valid

11) Quantities and Weights of "0" are considered valid inputs

12)Names like "shirt" and "SHIRT" are different items

  a) Shirt is also different from shirt

  b) Different capitalizations of letters in the same word are treated as separate items (i hope this makes sense, just a rehash of a)

13) Overflow can occur within the int weight and int quantity numbers 

    a) if overflow occurs, it is an invalid input (too large of a number for us to handle)

14) Header is included in our files

