package calculator.project2calculatorgui;

/**
 * Enum for the different operators that can be used in the calculator.
 */
public enum Operators {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    MODULUS("%");

    private String operator;

    Operators(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
