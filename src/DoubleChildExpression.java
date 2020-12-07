public class DoubleChildExpression implements Expression{
    //Additive, Multiplicative, Exponential
    private Expression child1, child2;
    private char operation;

    public DoubleChildExpression (Expression child1, Expression child2, char operation) {
        this.child1 = child1;
        this.child2 = child2;
        this.operation = operation;
    }

    @Override
    public double evaluate (double x) {
        if (operation == '+') {
            return child1.evaluate(x) + child2.evaluate(x);
        } else if (operation == '-') {
            return child1.evaluate(x) - child2.evaluate(x);
        } else if (operation == '*') {
            return child1.evaluate(x) * child2.evaluate(x);
        } else if (operation == '/') {
            return child1.evaluate(x) / child2.evaluate(x);
        } else {
            return Math.pow(child1.evaluate(x),child2.evaluate(x));
        }
    }

    @Override
    public void convertToString (StringBuilder stringBuilder, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
			stringBuilder.append('\t');
		}
        stringBuilder.append(operation);
        stringBuilder.append("\n");
        child1.convertToString(stringBuilder, indentLevel + 1);
        stringBuilder.append("\n");
        child2.convertToString(stringBuilder, indentLevel + 1);
    }
}
