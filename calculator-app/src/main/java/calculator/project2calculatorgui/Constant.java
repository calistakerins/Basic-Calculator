package calculator.project2calculatorgui;

public record Constant(double value) implements Expression {

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public String toStringPostfix() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Constant c) {
            return value == c.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
