package differentiator;

public class Number implements Expression {
	// the field corresponding to the value of the number 
	public String value;
	
	 /**
	    * Constructs the Number expression  
	    * @param value The number's value
	    */
	public Number(String value) {
		this.value=value;}
	
	 /**
	    * Differentiates the Number expression with respect to variable diffVar  
	    * @param diffVar The variable with respect to which the differentiation happens.
	    * @return The expression's derivative.
	    */	
	public String calculate(String diffVar) {
		return "0";}
	
	 /**
	    * Displays the Number expression as a String  .
	    * @return String representing The expression.
	    */
	public String display() {
		return value;}
	

		
		
}
	
	


