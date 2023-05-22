import calculator.project2calculatorgui.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPrefixParser {
    private Parser parser;
    private VariableBindings bindings;

    @BeforeEach
    public void setup() {
        parser = new PrefixParser();
        bindings = new VariableBindings();
    }

    @Test
    void testIsOperator() {
        Assertions.assertTrue(parser.isOperator("+"));
    }

    @Test
    void testIsOperator2() {
        Assertions.assertTrue(parser.isOperator("^"));
    }

    @Test
    void testIsOperatorFalse() {
        Assertions.assertFalse(parser.isOperator("5"));
    }

    @Test
    void testParseAdditionInput(){
        String [] input = {"+", "5", "6"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new AdditionExpression(new Constant(5), new Constant(6)), parser.parseInput(input, bindings));
    }

    @Test
    void testParseSubtractionInput(){
        String [] input = {"-", "5", "6"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new SubtractionExpression(new Constant(5), new Constant(6)), parser.parseInput(input, bindings));
    }

    @Test
    void testParseMultiplicationInput(){
        String [] input = {"*", "5", "6"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new MultiplicationExpression(new Constant(5), new Constant(6)), parser.parseInput(input, bindings));
    }

    @Test
    void testParseDivisionInput(){
        String [] input = {"/", "5", "6"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new DivisionExpression(new Constant(5), new Constant(6)), parser.parseInput(input, bindings));
    }

    @Test
    void testParseModulusInput(){
        String [] input = {"%", "5", "6"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new ModuloExpression(new Constant(5), new Constant(6)), parser.parseInput(input, bindings));
    }

    @Test
    void testParseExponentiationInput(){
        String [] input = {"^", "5", "6"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new ExponentiationExpression(new Constant(5), new Constant(6)), parser.parseInput(input, bindings));
    }

    @Test
    void testParseNullInput(){
        String [] input = {};
        Assertions.assertNull(parser.parseInput(input, bindings));
    }

    @Test
    void testParseVariable(){
        bindings.addVariable("x", 5);
        String [] input = {"+", "5", "x"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new AdditionExpression(new Constant(5), new VariableExpression("x",5)), parser.parseInput(input, bindings));
    }

    @Test
    void testParseVariableUnaryOperand(){
        bindings.addVariable("x", 5);
        String [] input = {"x"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new VariableExpression("x",5), parser.parseInput(input, bindings));
    }

    @Test
    void testUnaryOperand(){
        String [] input = {"-5"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new Constant(-5), parser.parseInput(input, bindings));
    }

    @Test
    void testNegativeNumbers(){
        String [] input = {"+", "-5", "-6"};
        Assertions.assertTrue(parser.isValidInput(input, bindings));
        Assertions.assertEquals(new AdditionExpression(new Constant(-5), new Constant(-6)), parser.parseInput(input, bindings));
    }

    @Test
    void testParseInvalidInput(){
        String [] input = {"+", "5", "6", "7"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }

    @Test
    void testParseInvalidInput2(){
        String [] input = {"5", "6", "+"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }

    @Test
    void testParseInvalidInput3(){
        String [] input = {"5", "+", "6"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }

    @Test
    void testParseInvalidInput4(){
        String [] input = {"+", "5"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }

    @Test
    void testParseInvalidInput5(){
        String [] input = {"+"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }

    @Test
    void testParseInvalidInput6(){
        String [] input = {"5", "6", "7"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }

    @Test
    void testParseInvalidInput7(){
        String [] input = {"&"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }

    @Test
    void testParseInvalidInput8(){
        String [] input = {"*", "5", "6", "@"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }

    @Test
    void testParseInvalidInput9(){
        String [] input = {"+", "3", "^", "5", "@"};
        Assertions.assertFalse(parser.isValidInput(input, bindings));
    }
}
