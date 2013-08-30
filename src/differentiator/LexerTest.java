package differentiator;
import java.util.ArrayList;  

import static org.junit.Assert.*;

import org.junit.Test;




import java.io.IOException;
public class LexerTest {

	@Test
	/**
     *Tests the Lexer class.If tests fails throw IOException. 
     */ 
	public void test() throws IOException {

	
		// creates the expected output
		String[] ExpectedOutput1= {"(","aa","+","22",")"};
		// creates a lexer over the input
		Lexer lex= new Lexer("(aa+22)");
		// creates the list of tokens
	    ArrayList<String> copyn = lex.createEntities("(aa + 22)");
	    //copy the ArrayList into an array
	    String[] stockArr = new String[copyn.size()];
	    stockArr = copyn.toArray(stockArr);
	    // check equality
		assertArrayEquals(ExpectedOutput1,stockArr);
		
		// The structure of each test is the same as the one mentioned above
		String[] ExpectedOutput2= {"(","a","+","*","(","a","+","222",")",")"};
		Lexer lex2= new Lexer("(a + *( a + 222))");
	    ArrayList<String> copyn2 = lex2.createEntities("(a + *( a + 222))");
	    String[] stockArr2 = new String[copyn2.size()];
	    stockArr2 = copyn2.toArray(stockArr2);
		assertArrayEquals(ExpectedOutput2,stockArr2);
		
		String[] ExpectedOutput3= {"(","4","+","(","7","+","6",")",")","8"};
		Lexer lex3= new Lexer("( 4 + (7+6)) 8");
	    ArrayList<String> copyn3 = lex3.createEntities("( 4 + (7+6)) 8");
	    String[] stockArr3 = new String[copyn3.size()];
	    stockArr3 = copyn3.toArray(stockArr3);
		assertArrayEquals(ExpectedOutput3,stockArr3);
		
		String[] ExpectedOutput4= {"(","x",")"};
		Lexer lex4= new Lexer("(x)");
	    ArrayList<String> copyn4 = lex4.createEntities("(x)");
	    String[] stockArr4 = new String[copyn4.size()];
	    stockArr4 = copyn4.toArray(stockArr4);
		assertArrayEquals(ExpectedOutput4,stockArr4);
		
		String[] ExpectedOutput5= {"(","x","x",")"};
		Lexer lex5= new Lexer(" ( x    x ) ");
	    ArrayList<String> copyn5 = lex5.createEntities(" ( x   x ) ");
	    String[] stockArr5 = new String[copyn5.size()];
	    stockArr5 = copyn5.toArray(stockArr5);
		assertArrayEquals(ExpectedOutput5,stockArr5);
		
		

	}
		
	// Testing Exceptions
	 @Test(expected= RuntimeException.class)
	    public void LexerExceptionTest() {

			// creates a lexer over the input
			Lexer lex= new Lexer("((a+22)");
			// creates the list of tokens
		    ArrayList<String> copyn = lex.createEntities("((a + 22)");
		    //copy the ArrayList into an array
		    
			assertEquals(RuntimeException.class,lex.createTokens(copyn));
			
	
}
}
