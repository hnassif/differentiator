package differentiator;

public class Variable implements Expression {
	
	final String value;
	
	
	 /**
	    * Constructs the Variable expression  
	    * @param value The variable's value
	    */
	public Variable(String value) {
		this.value=value;}
	
	 /**
	    * Displays the Variable expression as a String  .
	    * @return String representing The expression.
	    */
	public String display() {
		return value;}
	
	 /**
	    * Differentiates the Variable expression with respect to variable diffVar  
	    * @param diffVar The variable with respect to which the differentiation happens.
	    * @return The variable's derivative as a string.
	    */	 
	public String calculate(String diffVar) {
		if (value.equals(diffVar))
			return "1";
		else
			return "0";
	}
	

}
