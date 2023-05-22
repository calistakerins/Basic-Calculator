package calculator.project2calculatorgui;

public class VariableExpression implements Expression {
    private final String variableName;
    private final double value;

    /**
     * Constructor for VariableExpression class
     * @param variableName String: name of the variable
     * @param value double: value of the variable
     */
    public VariableExpression(String variableName, double value){
        this.variableName = variableName;
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public String toStringPostfix() {
        return variableName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VariableExpression other) {
            return this.variableName.equals(other.variableName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return variableName.hashCode();
    }
}
