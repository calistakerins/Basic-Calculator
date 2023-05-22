import calculator.project2calculatorgui.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TestVariables {
    private VariableBindings bindings;
    private VariableExpression var;

    @BeforeEach
    public void setup() {
        bindings = new VariableBindings();
        var = new VariableExpression("x", 5);
    }

    @Test
    void testAddVariable() {
        bindings.addVariable("x", 5);
        Assertions.assertTrue(bindings.containsVariable("x"));
    }

    @Test
    void testUpdateVariable() {
        bindings.addVariable("x", 5);
        bindings.addVariable("x", 6);
        Assertions.assertEquals(6, bindings.getVariableValue("x"));
    }

    @Test
    void testGetVariableList(){
        bindings.addVariable("x", 5);
        bindings.addVariable("y", 6);
        List<String> expected = new ArrayList<>();
        expected.add("x = 5.0");
        expected.add("y = 6.0");
        Assertions.assertEquals(expected, bindings.getVariableList());
    }

    @Test
    void testVariableExpressionEvaluate(){
        Assertions.assertEquals(5, var.evaluate());
    }

    @Test
    void testVariableExpressionToString(){
        Assertions.assertEquals("x", var.toString());
    }

    @Test
    void testVariableExpressionToStringPostfix(){
        Assertions.assertEquals("x", var.toString());
    }

    @Test
    void testOverrideEquals(){
        VariableExpression var2 = new VariableExpression("x", 5);
        Assertions.assertEquals(var, var2);
    }

    @Test
    void testOverrideEqualsFalse(){
        VariableExpression var2 = new VariableExpression("y", 5);
        Assertions.assertNotEquals(var, var2);
    }

    @Test
    void testOverrideEqualsFalse2(){
        Expression var2 = new Constant(5);
        Assertions.assertNotEquals(var, var2);
    }

    @Test
    void testOverrideHashCode(){
        VariableExpression var2 = new VariableExpression("x", 5);
        Assertions.assertEquals(var.hashCode(), var2.hashCode());
    }
}
