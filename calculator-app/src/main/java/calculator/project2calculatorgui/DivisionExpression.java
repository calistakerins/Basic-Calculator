package calculator.project2calculatorgui;

public class DivisionExpression extends BinaryExpression {

    /**
     * @param lChild Expression to be divided
     * @param rChild Expression to divide by
     */
    public DivisionExpression(Expression lChild, Expression rChild) {
        super(lChild, rChild, "/");
    }

    @Override
    public double evaluate() {
        if(rChild.evaluate() == 0){
            throw new ArithmeticException("Cannot divide by zero");
        }
        return lChild.evaluate() / rChild.evaluate();
    }
}
