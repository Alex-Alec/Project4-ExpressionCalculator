public class ExponentialExpression implements Expression{
	private Expression child1, child2;

	public ExponentialExpression(Expression child1, Expression child2){
		this.child1 = child1;
		this.child2 = child2;
	}

	public double evaluate (double x){
		return Math.pow(child1.evaluate(x),child2.evaluate(x));
	}
	@Override
	public void convertToString(StringBuilder stringBuilder, int indentLevel) {
		for (int i = 0; i < indentLevel; i++) {
			stringBuilder.append('\t');
		}
		stringBuilder.append("^");
		stringBuilder.append("\n");
		child1.convertToString(stringBuilder, indentLevel + 1);
		stringBuilder.append("\n");
		child2.convertToString(stringBuilder, indentLevel + 1);
	}
}
