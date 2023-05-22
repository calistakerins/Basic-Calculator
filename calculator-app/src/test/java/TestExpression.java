import calculator.project2calculatorgui.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class TestExpression {

    @Test
    void testAdditionExpression() {
        Expression e = new AdditionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(10, e.evaluate());
    }

    @Test
    void testSubtractionExpression() {
        Expression e = new SubtractionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(0, e.evaluate());
    }

    @Test
    void testMultiplicationExpression() {
        Expression e = new MultiplicationExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(25, e.evaluate());
    }

    @Test
    void testDivisionExpression() {
        Expression e = new DivisionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(1, e.evaluate());
    }

    @Test
    void testDivisionExpressionByZero() {
        Expression e = new DivisionExpression(new Constant(5), new Constant(0));
        Assertions.assertThrows(ArithmeticException.class, e::evaluate);
    }

    @Test
    void testExponentiationExpression() {
        Expression e = new ExponentiationExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(3125, e.evaluate());
    }

    @Test
    void testModuloExpression() {
        Expression e = new ModuloExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(0, e.evaluate());
    }

    @Test
    void testModuloExpressionByZero() {
        Expression e = new ModuloExpression(new Constant(5), new Constant(0));
        Assertions.assertThrows(ArithmeticException.class, e::evaluate);
    }

    @Test
    void testAdditionEqualsSubtraction(){
        Expression e1 = new AdditionExpression(new Constant(5), new Constant(5));
        Expression e2 = new SubtractionExpression(new Constant(10), new Constant(0));
        Assertions.assertNotEquals(e1, e2);
    }

    @Test
    void testAdditionEqualsAddition(){
        Expression e1 = new AdditionExpression(new Constant(5), new Constant(5));
        Expression e2 = new AdditionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(e1, e2);
    }

    @Test
    void testAdditionEqualsConstant(){
        Expression e1 = new AdditionExpression(new Constant(5), new Constant(5));
        Expression e2 = new Constant(5);
        Assertions.assertNotEquals(e1, e2);
    }

    @Test
    void testBinaryHashCode1(){
        Expression e1 = new AdditionExpression(new Constant(5), new Constant(5));
        Expression e2 = new AdditionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void testBinaryHashCode2(){
        Expression e1 = new AdditionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(e1.hashCode(), e1.hashCode());
    }

    @Test
    void testConstantEqualsBinaryExp(){
        Expression e1 = new Constant(5);
        Expression e2 = new AdditionExpression(new Constant(5), new Constant(0));
        Assertions.assertNotEquals(e1, e2);
    }

    @Test
    void testBinaryEvaluate(){
        BinaryExpression e1 = new AdditionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals(10, e1.evaluate());
    }

    @Test
    void testVariableEvaluate(){
        BinaryExpression e1 = new AdditionExpression(new Constant(5), new VariableExpression("a",5));
        Assertions.assertEquals(10, e1.evaluate());
    }

    @Test
    void testExpressionToString(){
        Expression e1 = new AdditionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals("+ 5.0 5.0", e1.toString());
    }

    @Test
    void testExpressionToStringPostfix(){
        Expression e1 = new AdditionExpression(new Constant(5), new Constant(5));
        Assertions.assertEquals("5.0 5.0 +", e1.toStringPostfix());
    }
}
