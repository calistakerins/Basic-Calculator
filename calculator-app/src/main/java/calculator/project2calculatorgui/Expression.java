package calculator.project2calculatorgui;

public interface Expression {
    /**
     * @return double value of the expression
     * @throws ArithmeticException if the expression is invalid (e.g. division by zero)
     */
    double evaluate();

    /**
     * @return String representation of the expression in postfix notation
     */
    String toStringPostfix();
}
