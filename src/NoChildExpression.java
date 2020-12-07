public class NoChildExpression implements Expression{
    private boolean var;
    private double literal;

    public NoChildExpression() {
        var = true;
        literal = 0;
    }

    public NoChildExpression(String str){
        var = false;
        literal = Double.parseDouble(str);
    }

    @Override
    public double evaluate(double x){
        if (var) {
            return x;
        } else {
            return literal;
        }
    }

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
