package differentiator;

// Interface for Add, Multiply, Variable, Number
public interface Expression {
	
	// methods signature for the two methods present in the Add, Multiply, Variable and Number classes
	public String calculate(String diffVar);
	public String display();

}
