public class MultiplicativeExpression implements Expression{
	private Expression child1, child2;
	private boolean isMultiplcation;

	public MultiplicativeExpression(Expression child1, Expression child2, boolean isMultiplication){
		this.child1 = child1;
		this.child2 = child2;
		this.isMultiplcation = isMultiplication;
	}

	public double evaluate (double x){
		if(isMultiplcation)
			return child1.evaluate(x) * child2.evaluate(x);
		return child1.evaluate(x) / child2.evaluate(x);
	}
	@Override
	public void convertToString(StringBuilder stringBuilder, int indentLevel) {

	}
}
