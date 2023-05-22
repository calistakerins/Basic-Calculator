package calculator.project2calculatorgui;

public class MultiplicationExpression extends BinaryExpression {

    /**
     * @param lChild Expression to be multiplied
     * @param rChild Expression to be multiplied
     */
    public MultiplicationExpression(Expression lChild, Expression rChild) {
        super(lChild, rChild, "*");
    }

    @Override
    public double evaluate() {
        return lChild.evaluate() * rChild.evaluate();
    }
}
