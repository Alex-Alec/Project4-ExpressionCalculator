//public class AdditiveExpression implements Expression {
//	private Expression child1, child2;
//	private boolean isAddition;
//
//	public AdditiveExpression(Expression child1, Expression child2, boolean isAddition){
//		this.child1 = child1;
//		this.child2 = child2;
//		this.isAddition = isAddition;
//	}
//
//	public double evaluate (double x){
//		if(isAddition)
//			return child1.evaluate(x) + child2.evaluate(x);
//		return child1.evaluate(x) - child2.evaluate(x);
//	}
//
//	@Override
//	public void convertToString(StringBuilder stringBuilder, int indentLevel) {
//		for (int i = 0; i < indentLevel; i++) {
//			stringBuilder.append('\t');
//		}
//		stringBuilder.append("+");
//		stringBuilder.append("\n");
//		child1.convertToString(stringBuilder, indentLevel + 1);
//		stringBuilder.append("\n");
//		child2.convertToString(stringBuilder, indentLevel + 1);
//	}
//}
