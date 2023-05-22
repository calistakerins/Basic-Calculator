package calculator.project2calculatorgui;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PrefixParser implements Parser {
    private Queue<String> expressionQueue;

    /**
     * Constructor for PrefixParser class
     */
    public PrefixParser() {
        expressionQueue = null;
    }

    @Override
    public Expression parseInput(String [] userInput, VariableBindings variableBindings) {
        expressionQueue = new LinkedList<>(Arrays.asList(userInput));
        return buildTree(variableBindings);
    }

    private Expression buildTree(VariableBindings variableBindings){
        if(expressionQueue.isEmpty()){
            return null;
        }

        String current = expressionQueue.remove();

        if(isOperand(current, variableBindings) && variableBindings.containsVariable(current)) {
            return new VariableExpression(current, variableBindings.getVariableValue(current));
        }else if (isOperator(current)){
            Expression left = buildTree(variableBindings);
            Expression right = buildTree(variableBindings);
            if(current.equals(Operators.ADDITION.getOperator())){
                return new AdditionExpression(left, right);
            }
            else if(current.equals(Operators.SUBTRACTION.getOperator())){
                return new SubtractionExpression(left, right);
            }
            else if(current.equals(Operators.MULTIPLICATION.getOperator())){
                return new MultiplicationExpression(left, right);
            }
            else if (current.equals(Operators.DIVISION.getOperator())){
                return new DivisionExpression(left, right);
            }
            else if (current.equals(Operators.MODULUS.getOperator())) {
                return new ModuloExpression(left, right);
            }
            else{
                return new ExponentiationExpression(left, right);
            }
        }else {
            return new Constant(Double.parseDouble(current));
        }
    }

    @Override
    public boolean isValidInput(String [] inputTokens, VariableBindings variableBindings) {
        //Check for unary operand
        if(inputTokens.length == 1){
            return isOperand(inputTokens[0], variableBindings);
        }

        // Input must have at least 3 tokens
        if (inputTokens.length < 3) {
            return false;
        }

        if(!isOperand(inputTokens[inputTokens.length - 2], variableBindings) || !isOperand(inputTokens[inputTokens.length - 1], variableBindings)){
            return false;
        }

        int numOperators = 0;
        int numOperands = 0;
        for(String s : inputTokens){
            if(isOperand(s, variableBindings)){
                numOperands++;
            }
            else if(isOperator(s)){
                numOperators++;
            }
            else{
                return false;
            }
        }

        return numOperators == numOperands - 1;
    }

}
