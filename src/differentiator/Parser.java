package differentiator; 
import java.util.Stack;

/**
 * The parser gets a bunch of tokens from the lexer and determines what
 * expression was written by the user.
 */
public class Parser {

	// lexer to be passed to the parser
	public Lexer lexer;

	/**
	 * Creates a new parser over the lexer.  This parser will use the passed
	 * lexer to get tokens--which it will then parse.
	 * @param lexer The lexer.
	 */
	public Parser(Lexer lexer) {
		this.lexer=lexer;
	}


	
	/**
     * Checks the validity of the logic behind the tokens and then creates a valid ADS by calling createADS
     * on the valid tokens list.  
     * If a sequence of tokens is not mathematically incorrect, throws an exception. If the input doesn't start with
     * an open parenthesis and end with a closed parenthesis, throw an Exception.
     * @param theTokens The array of tokens obtained from the lexer.
     * @return Expression that abstracts the initial, mathematically correct input.
     */
	public Expression createValidADS(Token[] theTokens) throws RuntimeException {
		// accounts for single inputs with no Paren such as : 1 or x 
		if (theTokens.length==1){
			if (theTokens[0].getType()==Token.Type.NUMBER){
				Expression onlyNumber= new Number(theTokens[0].getText());
				return onlyNumber;
			}
			else if (theTokens[0].getType()==Token.Type.VARIABLE){
				Expression onlyVariable= new Variable(theTokens[0].getText());
				return onlyVariable;
			}
			else {throw new RuntimeException("Illegal Single Input");}
		}
		// checks if the first character is an open Paren
		if (theTokens[0].getType()!=Token.Type.OPEN_PAREN && theTokens.length>1) 
		{
			throw new RuntimeException("Missing Open Paren");
		}
		// checks if the last character is a closed Paren
		if (theTokens[theTokens.length-1].getType()!=Token.Type.CLOSE_PAREN) 
		{
			throw new RuntimeException("Missing closed Paren");
		}
		// checks all illegal cases by comparing successive tokens
		for(int i=1; i<theTokens.length; i++){
			// illegal cases covered : similar successive tokens + + , * *, 2 2, a a except for (( and )) 
			if (theTokens[i-1].getType()==theTokens[i].getType() && 
					theTokens[i].getType()!=Token.Type.CLOSE_PAREN && 
					theTokens[i].getType()!=Token.Type.OPEN_PAREN )
			{
				throw new RuntimeException("Repeated Operation or Operand ");
			}
			//illegal cases covered : closed paren followed by a number, variable or open paren
			else   if ((theTokens[i-1].getType().equals(Token.Type.CLOSE_PAREN)) && 
					(theTokens[i].getType()==Token.Type.NUMBER || 
					theTokens[i].getType()==Token.Type.VARIABLE ||  theTokens[i].getType()==Token.Type.OPEN_PAREN)  )
			{
				throw new RuntimeException("closed paren followed by non legal input");
			}
			// illegal cases covered : operation (plus or multiply) immediately followed by a closed paren
			else   if ((theTokens[i].getType()==Token.Type.CLOSE_PAREN) && 
					(theTokens[i-1].getType()==Token.Type.PLUS ||
					theTokens[i-1].getType()==Token.Type.MULTIPLY ) )
			{
				throw new RuntimeException("no operand after opration");
			}
			// illegal cases covered : two operands not separated by any operation
			else   if ((theTokens[i].getType()==Token.Type.NUMBER && theTokens[i-1].getType()==Token.Type.VARIABLE) || 
					(theTokens[i].getType()==Token.Type.VARIABLE && theTokens[i-1].getType()==Token.Type.NUMBER))
			{
				throw new RuntimeException("operand without opeartion");
			}
			// illegal cases covered : two operations not separated by an operand
			else   if ((theTokens[i].getType()==Token.Type.PLUS && theTokens[i-1].getType()==Token.Type.MULTIPLY) || 
					(theTokens[i].getType()==Token.Type.MULTIPLY && theTokens[i-1].getType()==Token.Type.PLUS))
			{
				throw new RuntimeException("operations without operand");
			}
			// illegal cases covered : a variable or number immediately followed by an open paren 
			else   if ((theTokens[i].getType()==Token.Type.OPEN_PAREN) && 
					(theTokens[i-1].getType()==Token.Type.NUMBER ||
					theTokens[i-1].getType()==Token.Type.VARIABLE ) )
			{
				throw new RuntimeException("no operation before paren");
			}


		}
		// if no exception has been thrown , the ADS is created 
		return createADS(theTokens);
	}

	/**
     * Checks the validity of the logic behind the tokens and then creates a valid ADS by calling createADS
     * on the valid tokens list.  
     * @param theTokens A valid and mathematically correct array of tokens.
     * @return Expression that abstracts the initial, mathematically correct input.
     */
	public Expression createADS(Token[] theTokens){
		// operations stack
		Stack<Token> operations = new Stack<Token>();
		// operands stack
		Stack<Expression> operand = new Stack<Expression>();
		for (int i=0; i<theTokens.length; i++){
			if(theTokens[i].getType()==Token.Type.PLUS||theTokens[i].getType()==Token.Type.MULTIPLY ||theTokens[i].getType()==Token.Type.OPEN_PAREN)
				// push operations and open paren on operations stack
				operations.push(theTokens[i]);
			else if (theTokens[i].getType()==Token.Type.NUMBER)
				// push number on operands stack
				operand.push(new Number(theTokens[i].getText()));
			else if (theTokens[i].getType()==Token.Type.VARIABLE)
			{ Variable k = new Variable(theTokens[i].getText());
			// push variable on operands stack
			operand.push(k);
			System.out.println("In Parser: "+k.value);
			}
			// whenever a closed paren is seen, pop the elements between this paren and the previous open one 
			if (theTokens[i].getText()==")") {
				// if the previous one is open, there is nothing in between 
				if (i>0 && operations.peek().getText()=="(")
				{operations.pop();}
				// if the previous element pushed is a number or variable, it is the case for  (3) or (x)
				else if (i>1 && operations.peek().getText()=="(" && (theTokens[i-1].getType()==Token.Type.NUMBER ||theTokens[i-1].getType()==Token.Type.VARIABLE))
				{
					operations.pop();
				}
				// This is the case for a complete expression such as (4*x) or (4+x)
				else {
					Expression right=operand.pop();
					Expression left = operand.pop();
					Token op=operations.pop();
					operations.pop();
					if (op.getText()=="+")
						operand.push(new Add(left,right));
					else 
						operand.push(new Multiply(left,right));
				}

			}

		}
		// returns the complete expression
		return operand.pop();


	}


}
