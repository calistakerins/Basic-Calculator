package calculator.project2calculatorgui;

public abstract class BinaryExpression implements Expression {
    protected Expression lChild;
    protected Expression rChild;
    protected String operator;

    /**
     * @param lChild Expression child
     * @param rChild Expression child
     * @param operator String representation of the operator
     */
    protected BinaryExpression(Expression lChild, Expression rChild, String operator){
        this.lChild = lChild;
        this.rChild = rChild;
        this.operator = operator;
    }

    @Override
    public String toString(){
        return operator + " " + lChild.toString() + " "  + rChild.toString();
    }

    public String toStringPostfix(){
        return lChild.toStringPostfix() + " " + rChild.toStringPostfix() + " " + operator;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof BinaryExpression b){
            return lChild.equals(b.lChild) && rChild.equals(b.rChild) && operator.equals(b.operator);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return lChild.hashCode() + rChild.hashCode() + operator.hashCode();
    }
}
