public class ParentheticalExpression implements Expression{
	private Expression child1;

	public ParentheticalExpression(Expression child1){
		this.child1 = child1;
	}

	public double evaluate (double x){
		return child1.evaluate(x);
	}

	@Override
	public void convertToString(StringBuilder stringBuilder, int indentLevel) {
		for (int i = 0; i < indentLevel; i++) {
			stringBuilder.append('\t');
		}
		stringBuilder.append("()");
		stringBuilder.append("\n");
		child1.convertToString(stringBuilder, indentLevel + 1);
	}
}
