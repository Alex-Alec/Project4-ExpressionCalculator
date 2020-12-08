/**
 * SingleChildExpression class for single child expressions, start and parenthetical expressions
 */
public class SingleChildExpression implements Expression{
    private Expression child1;
    private boolean start = false;

    /**
     * Constructor for start expression
     * @param child1
     */
    public SingleChildExpression(Expression child1){
        this.child1 = child1;
    }

    /**
     * Constructor for parenthetical expression
     * @param child1
     * @param start
     */
    public SingleChildExpression(Expression child1, boolean start){
        this.child1 = child1;
        this.start = start;
    }

    /**
     * Evaluate
     * @param x the given value of x
     * @return
     */
    @Override
    public double evaluate(double x) {
        return child1.evaluate(x);
    }

    /**
     * Convert to String
     * @param stringBuilder the StringBuilder to use for building the String representation
     * @param indentLevel the indentation level (number of tabs from the left margin) at which to start
     */
    @Override
    public void convertToString(StringBuilder stringBuilder, int indentLevel) {
        if (!start) { // For Parenthetical Expression
            for (int i = 0; i < indentLevel; i++) {
                stringBuilder.append('\t');
            }
            stringBuilder.append("()");
            stringBuilder.append("\n");
            child1.convertToString(stringBuilder, indentLevel + 1);
        } else { // For Start Expression, add new line at the very end
            child1.convertToString(stringBuilder, indentLevel);
            stringBuilder.append("\n");
        }
    }
}
