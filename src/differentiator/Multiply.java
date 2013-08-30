package differentiator;

public class Multiply implements Expression {
	
	// declaring the two fields of a Multiply operation
	Expression Left;
	Expression Right;
	
	 /**
	    * Constructs the Multiply expression  
	    * @param Left The Left expression.
	    * @param Right The Right expression.
	    */
	public Multiply(Expression Left, Expression Right) {
		this.Left= 	Left;
		this.Right = Right;
		}

	 /**
	    * Differentiates the Multiply expression with respect to variable diffVar
	    * and recursively returns its derivative as a string.  If the expression or variable
	    * is null, behavior is undefined.  
	    * @param diffVar The variable with respect to which the differentiation happens.
	    * @return The expression's derivative.
	    */	
	public String calculate(String diffVar) { 
	    return  "(" + Left.calculate(diffVar)+ ")"+ "*"+ "("+ Right.display() +")"   + "+"  +"("+Right.calculate(diffVar) + ")" +"*" +"(" +Left.display() + ")" ;
	}
	
	 /**
	    * Displays the Multiply expression 
	    * and recursively returns it as a string.  If the expression or variable
	    * is null, behavior is undefined.  .
	    * @return String representing The expression.
	    */
	public String display() {
		return "(" + Left.display() + "*" + Right.display() + ")";}
		
}
