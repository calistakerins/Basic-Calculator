package calculator.project2calculatorgui;

public class AdditionExpression extends BinaryExpression {

    /**
     * @param lChild Expression to be added
     * @param rChild Expression to be added
     */
    public AdditionExpression(Expression lChild, Expression rChild) {
        super(lChild, rChild, "+");
    }

    @Override
    public double evaluate() {
        return lChild.evaluate() + rChild.evaluate();
    }
}
