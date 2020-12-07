public class SingleChildExpression implements Expression{
    private Expression child1;
    private boolean start = false;

    public SingleChildExpression(Expression child1){
        this.child1 = child1;
    }

    public SingleChildExpression(Expression child1, boolean start){
        this.child1 = child1;
        this.start = start;
    }

    @Override
    public double evaluate(double x) {
        return child1.evaluate(x);
    }

    @Override
    public void convertToString(StringBuilder stringBuilder, int indentLevel) {
        if (!start) {
            for (int i = 0; i < indentLevel; i++) {
                stringBuilder.append('\t');
            }
            stringBuilder.append("()");
            stringBuilder.append("\n");
            child1.convertToString(stringBuilder, indentLevel + 1);
        } else {
            child1.convertToString(stringBuilder, indentLevel);
            stringBuilder.append("\n");
        }
    }
}
