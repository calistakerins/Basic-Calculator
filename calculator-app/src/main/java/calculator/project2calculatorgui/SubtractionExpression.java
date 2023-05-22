package calculator.project2calculatorgui;

public class SubtractionExpression extends BinaryExpression {

    /**
     * @param lChild Expression to be subtracted
     * @param rChild Expression to subtract by
     */
    public SubtractionExpression(Expression lChild, Expression rChild) {
        super(lChild, rChild, "-");
    }

    @Override
    public double evaluate() {
        return lChild.evaluate() - rChild.evaluate();
    }
}
