package calculator.project2calculatorgui;

public class ExponentiationExpression extends BinaryExpression {
    /**
     * @param lChild Expression to be raised to a power
     * @param rChild Expression to raise by
     */
    public ExponentiationExpression(Expression lChild, Expression rChild) {
        super(lChild, rChild, "^");
    }

    @Override
    public double evaluate() {
        return (int) Math.pow(lChild.evaluate(), rChild.evaluate());
    }
}
