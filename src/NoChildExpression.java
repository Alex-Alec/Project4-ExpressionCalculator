/**
 * NoChildExpression class are for expressions that have no children (Literal, variable)
 */
public class NoChildExpression implements Expression{
    private boolean var;
    private double literal;

    /**
     * Constructor for variable expression
     */
    public NoChildExpression() {
        var = true;
        literal = 0;
    }

    /**
     * Constructor for literal expression
     * @param str
     */
    public NoChildExpression(String str){
        var = false;
        literal = Double.parseDouble(str);
    }

    /**
     * Evaluate
     * @param x the given value of x
     * @return
     */
    @Override
    public double evaluate(double x){
        if (var) {
            return x;
        } else {
            return literal;
        }
    }

    /**
     * Convert to string
     * @param stringBuilder the StringBuilder to use for building the String representation
     * @param indentLevel the indentation level (number of tabs from the left margin) at which to start
     */
    @Override
    public void convertToString(StringBuilder stringBuilder, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            stringBuilder.append('\t');
        }
        if (var) {
            stringBuilder.append("x");
        } else {
            stringBuilder.append(literal);
        }
    }
}
