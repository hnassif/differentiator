package differentiator;

import static org.junit.Assert.*; 

import org.junit.Test;

//import differentiator.Token.Type;

public class ParserTest {

	@Test
	/**
     *Tests the Parser class.If tests fails throw IOException. 
     */ 
	public void test() {
	
		// creates separate independent tokens
		Token t1= new Token(Token.Type.OPEN_PAREN, "(");
		Token t2= new Token(Token.Type.NUMBER, "5");
		Token t3= new Token(Token.Type.PLUS, "+");
		Token t4= new Token(Token.Type.VARIABLE, "x");
		Token t5 = new Token(Token.Type.CLOSE_PAREN, ")");
		
		// populates an array with tokens
		Token[] allOfThem= new Token[5];
		allOfThem[0]=t1;
		allOfThem[1]=t2;
		allOfThem[2]=t3;
		allOfThem[3]=t4;
		allOfThem[4]=t5;
		
		// creates expressions
		Expression ex1= new Number("5");
		Expression ex2= new Variable("x");
		Expression ex = new Add(ex1,ex2);
		
		// creates a lexer
		Lexer lex= new Lexer("(aa+22)");
		// creates a parser over the lexer
		Parser parse = new Parser(lex);
		// calculate the derivative 
		String answer= ex.calculate("x");
		// calculate the output from the lexer
		String output = parse.createADS(allOfThem).calculate("x");
		System.out.println(output);
		System.out.println(answer);
		//compare the lexer output to the derivative
		assertEquals(answer,output);
		
		Token tt1= new Token(Token.Type.OPEN_PAREN, "(");
		Token tt2= new Token(Token.Type.OPEN_PAREN, "(");
		Token tt3= new Token(Token.Type.NUMBER, "5");
		Token tt4= new Token(Token.Type.PLUS, "+");
		Token tt5= new Token(Token.Type.VARIABLE, "a");	
		Token tt6 = new Token(Token.Type.CLOSE_PAREN, ")");
		Token tt7= new Token(Token.Type.PLUS, "+");
		Token tt8= new Token(Token.Type.NUMBER, "4");
		Token tt9 = new Token(Token.Type.CLOSE_PAREN, ")");
		
		Token[] allOfThem2= new Token[9];
		allOfThem2[0]=tt1;
		allOfThem2[1]=tt2;
		allOfThem2[2]=tt3;
		allOfThem2[3]=tt4;
		allOfThem2[4]=tt5;
		allOfThem2[5]=tt6;
		allOfThem2[6]=tt7;
		allOfThem2[7]=tt8;
		allOfThem2[8]=tt9;
		
		
		Expression ex11= new Number("5");
		Expression ex22= new Variable("a");
		Expression ex3= new Number("4");
		Expression add1 = new Add(ex11,ex22);
		Expression add2 = new Add(add1,ex3);
		
	
		Parser parse2 = new Parser(lex);
		String answer2= add2.calculate("x");
		String output2 = parse2.createADS(allOfThem2).calculate("x");
		System.out.println(output2);
		System.out.println(answer2);
		assertEquals(answer2,output2);
		
	
		Token ttt2= new Token(Token.Type.OPEN_PAREN, "(");
		Token ttt3= new Token(Token.Type.VARIABLE, "x");
		Token ttt4= new Token(Token.Type.CLOSE_PAREN, ")");
		
		
		Token[] allOfThem3= new Token[3];
		
		allOfThem3[0]=ttt2;
		allOfThem3[1]=ttt3;
		allOfThem3[2]=ttt4;
		
		
		
		Expression e= new Variable("x");

		
	
		Parser parse3 = new Parser(lex);
		String answer3= e.calculate("x");
		String output3 = parse3.createADS(allOfThem3).calculate("x");
		System.out.println(output3);
		System.out.println(answer3);
		assertEquals(answer3,output3);
	}

}
