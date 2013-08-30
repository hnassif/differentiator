package differentiator;

/**
 * A token is a lexical item that the parser uses.
 */
public class Token {
    /**
     * All the types of tokens that can be made.
     */
    public static enum Type {
    	NUMBER, VARIABLE, PLUS , MULTIPLY, OPEN_PAREN, CLOSE_PAREN;
        
    }
   
        
        private final Type type;
        private final String text;
        
   	 /**
 	    * Constructs the Token  
 	    * @param type The type of the token
 	    */
        public Token(Type type) {
            this(type, "");
        }
        
      	 /**
  	    * Constructs the Token  
  	    * @param type The type of the token
  	    * @param text The string content of the Token
  	    */
        public Token(Type type, String text) {
            this.type = type;
            this.text = text;
        }
        
   	 /**
 	    * Displays the Token as a String  .
 	    * @return String representing The token.
 	    */
        @Override public String toString() {
            return "Token<" + type + ":" + text + ">";
        }
    
        public Type getType(){
        	return this.type;}
        public String getText() {
        	return this.text;}
        
        
        }
