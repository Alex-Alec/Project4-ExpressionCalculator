public class LiteralExpression implements Expression{
	private double value;

	public double evaluate(double x){
		return value;
	}

	@Override
	public void convertToString(StringBuilder stringBuilder, int indentLevel) {

	}
}
