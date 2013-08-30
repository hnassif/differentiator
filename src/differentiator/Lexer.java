package differentiator;
import java.util.ArrayList;  
import java.io.StringReader;
import java.io.IOException;
import java.lang.StringBuffer;

/**
 * A lexer takes a string and splits it into tokens that are meaningful to a
 * parser.
 */
public class Lexer {

	// InitialInput : The string to be lexed
	public final String InitialInput;

	/**
	 * Creates the lexer over the passed string.
	 * @param string The string to tokenize.
	 */
	public Lexer(String string) {
		this.InitialInput=string;

	}


    /**
     * Lexes the input into typeless tokens .  If the number of parenthesis is not balanced ,
     * throws an Exception. If a character is not recognized, throws an Exception.
     * @param InitialString The initial input to be lexed.
     * @return ArrayList<String> of tokens that might include both valid and invalid tokens.
     */
	public ArrayList<String> createEntities(String InitialString)  {


		
		int[] b= new int[InitialString.length()];
		StringReader reader=new StringReader(InitialString);
		// reads the characters one by one
		for (int i=0; i<InitialString.length(); i++) {
			try { 
				int a= reader.read();
				b[i]=a;
				}
			catch(IOException e){
				e.printStackTrace();
				}
		}

		int numOfOpenParen=0;
		int numOfClosedParen=0;
		ArrayList<String> allTokens = new ArrayList<String>();
		StringBuffer TokenInstance=new StringBuffer();


		for (int j=0; j<b.length; j++){
			// checks for unbalanced Paren//
			if (numOfClosedParen>numOfOpenParen)
			{ System.out.println("Stop");
			throw new RuntimeException ("Unbalanced (more closed than open)");
			}
			// creates a Token depending on the character
			else if (b[j]==' ')
			{ if (TokenInstance.length()!=0) 
				allTokens.add(TokenInstance.toString());
			TokenInstance=new StringBuffer();

			}
			else if (b[j]=='(')
			{ numOfOpenParen++;
			if (TokenInstance.length()!=0)
				allTokens.add(TokenInstance.toString());
			TokenInstance=new StringBuffer();
			allTokens.add("(");
			}
			else if (b[j]==')')
			{ numOfClosedParen++;
			if (TokenInstance.length()!=0)
				allTokens.add(TokenInstance.toString());
			TokenInstance=new StringBuffer();
			allTokens.add(")");
			}
			else if (b[j]=='+')
			{ if (TokenInstance.length()!=0)
				allTokens.add(TokenInstance.toString());
			TokenInstance=new StringBuffer();
			allTokens.add("+");
			}
			else if (b[j]=='*')
			{ if (TokenInstance.length()!=0)
				allTokens.add(TokenInstance.toString());
			TokenInstance=new StringBuffer();
			allTokens.add("*");
			}
			else if (Character.isDigit(b[j])){
				TokenInstance.append(Character.toChars(b[j])[0]);
				if(j==b.length-1)
					allTokens.add(TokenInstance.toString());
			}
			else if (Character.isLetter(b[j])){
				TokenInstance.append(Character.toChars(b[j])[0]);
				if(j==b.length-1)
					allTokens.add(TokenInstance.toString());
			}
			else if (b[j]=='.'){
				TokenInstance.append(Character.toChars(b[j])[0]);
			}
			else {
				throw new RuntimeException("Invalid Character");
			}

		}
		// checks for unbalanced Paren
		if (numOfOpenParen!=numOfClosedParen) 
		{
			throw new RuntimeException ("Unbalanced (more open than closed)");
		}
		return allTokens;


	}
	
    /**
     * Looks for a digit within a string.
     * @param s The String to be searched.
     * @return The result of the search in boolean form.
     */
	public final boolean containsDigit(final String s){  
		boolean containsDigit = false;

		if(s != null){
			for(char c : s.toCharArray()){
				if(Character.isDigit(c)){
					containsDigit = true;
					break;
				}
			}
		}

		return containsDigit;
	}
	
    /**
     * Looks for a Letter within a string.
     * @param s The String to be searched.
     * @return The result of the search in boolean form.
     */
	public final boolean containsLetter(final String s){  
		boolean containsLetter = false;

		if(s != null){
			for(char c : s.toCharArray()){
				if(Character.isLetter(c)){
					containsLetter = true;
					break;
				}
			}
		}

		return containsLetter;
	}
	
    /**
     * Assigns a type to each token .  If the token is invalid, throws an exception. 
     * @param UnitsArray The ArrayList<String> of tokens returned by createEntities(InitialString).
     * @return Token[]  that only includes valid tokens.
     */ 
	public Token[] createTokens(ArrayList<String> UnitsArray)  {
		Token[] finalTokens= new Token[UnitsArray.size()];
		for (int i=0; i<UnitsArray.size(); i++){
			if (UnitsArray.get(i)=="("){
				Token temp =new Token(Token.Type.OPEN_PAREN,UnitsArray.get(i));
				finalTokens[i]=temp;
			}
			else if (UnitsArray.get(i)==")"){
				Token temp =new Token(Token.Type.CLOSE_PAREN,UnitsArray.get(i));
				finalTokens[i]=temp;
			}
			else if (UnitsArray.get(i)=="+"){
				Token temp =new Token(Token.Type.PLUS,UnitsArray.get(i));
				finalTokens[i]=temp;
			}
			else if (UnitsArray.get(i)=="*"){
				Token temp =new Token(Token.Type.MULTIPLY,UnitsArray.get(i));
				finalTokens[i]=temp;
			}
			else if (containsLetter(UnitsArray.get(i)) && !containsDigit(UnitsArray.get(i))){
				Token temp =new Token(Token.Type.VARIABLE,UnitsArray.get(i));
				finalTokens[i]=temp;
			}
			else if (!containsLetter(UnitsArray.get(i)) && containsDigit(UnitsArray.get(i))){
				Token temp =new Token(Token.Type.NUMBER,UnitsArray.get(i));
				finalTokens[i]=temp;
			}
			// if none of the previous cases is true, then Input is badly formed . eg : a3
			else {
				throw new RuntimeException("Badly formed Input with valid Characters");
			}
		}
		//for (int i=0; i<finalTokens.length; i++){
			//System.out.println(finalTokens[i].type + "Value : "  + finalTokens[i].text);
		//}
		return finalTokens;
	}



}

