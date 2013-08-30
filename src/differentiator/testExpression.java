package differentiator;

import static org.junit.Assert.*;

import org.junit.Test;

public class testExpression {

	@Test
	public void test() {
		// creates an expression
		Expression exy1= new Number("3");
		Expression exy2= new Variable("a");
		Expression exy = new Add(exy1,exy2);
		
		String output= "0+1";
		// calculates the derivative of the expression
		System.out.println(exy.calculate("a"));
		// compare the derivative to the output string 
		assertEquals(output,exy.calculate("a"));
		
		//test 2
		Expression exy3= new Variable("g");
		Expression exxy = new Add(exy,exy3);
		String output2= "0+1+0";
		System.out.println(exxy.calculate("a"));
		assertEquals(output2,exxy.calculate("a"));
		
		Expression exy4= new Number("5");
		Expression exxy2 = new Add(exy,exy4);
		String output3= "0+1+0";
		System.out.println(exxy2.calculate("a"));
		System.out.print(exxy2.display());
		assertEquals(output3,exxy2.calculate("a"));
		
		// test 3
		Expression onez= new Number("1");
		Expression xz= new Variable("x");
		Expression az= new Variable("a");

		Expression exz1 = new Add(onez,onez);
		Expression exz2 = new Add(xz,onez);
		Expression exz3 = new Add(onez,az);
		Expression exz4 = new Add(az,xz);
		Expression exz5 = new Add(xz,xz);
		Expression exxz1 = new Add(exz5,onez);
		Expression exxz2 = new Add(exz5,exz4);
		System.out.println(exz1.calculate("x"));
		System.out.println(exz2.calculate("x"));
		System.out.println(exz3.calculate("x'"));
		System.out.println(exz4.calculate("x"));
		System.out.println(exz5.calculate("x"));
		System.out.println(exxz1.calculate("x"));
		System.out.println(exxz2.calculate("x"));
		
		System.out.println(exxz1.display());
		System.out.println(exxz2.display());
		
		// test 4
		Expression one= new Number("1");
		Expression x= new Variable("x");
		Expression a= new Variable("a");

		Expression ex1 = new Multiply(one,one);
		Expression ex2 = new Multiply(x,one);
		Expression ex3 = new Multiply(one,a);
		Expression ex4 = new Multiply(a,x);
		Expression ex5 = new Multiply(x,x);
		Expression exx1 = new Multiply(ex5,one);
		Expression exx2 = new Multiply(ex5,ex4);
		System.out.println(ex1.calculate("x"));
		System.out.println(ex2.calculate("x"));
		System.out.println(ex3.calculate("x"));
		System.out.println(ex4.calculate("x"));
		System.out.println(ex5.calculate("x"));
		System.out.println(exx1.calculate("x"));
		System.out.println(exx2.calculate("x"));
		
		System.out.println(exx1.display());
		System.out.println(exx2.display());
		
		
		
		
	}

}
