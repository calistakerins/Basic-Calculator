package calculator.project2calculatorgui;

import java.util.Stack;

public class PostfixParser implements Parser {
    private Stack<Expression> expressionStack;

    /**
     * Constructor for the PostfixParser class
     */
    public PostfixParser(){
        expressionStack = null;
    }

    @Override
    public Expression parseInput(String[] userInput, VariableBindings variableBindings) {
        expressionStack = new Stack<>();
        if(userInput.length >= 1){
            buildTree(userInput, variableBindings);
            return expressionStack.pop();
        }
        return null;
    }

    private void buildTree(String[] userInput, VariableBindings variableBindings){
        for(String s : userInput){
            if(isOperand(s, variableBindings) && variableBindings.containsVariable(s)) {
                expressionStack.push(new VariableExpression(s, variableBindings.getVariableValue(s)));
            }else if(isOperator(s)){
                Expression right = expressionStack.pop();
                Expression left = expressionStack.pop();
                if(s.equals(Operators.ADDITION.getOperator())){
                    expressionStack.push(new AdditionExpression(left, right));
                }
                else if(s.equals(Operators.SUBTRACTION.getOperator())){
                    expressionStack.push(new SubtractionExpression(left, right));
                }
                else if(s.equals(Operators.MULTIPLICATION.getOperator())){
                    expressionStack.push(new MultiplicationExpression(left, right));
                }
                else if (s.equals(Operators.DIVISION.getOperator())){
                    expressionStack.push(new DivisionExpression(left, right));
                }
                else if (s.equals(Operators.MODULUS.getOperator())) {
                    expressionStack.push(new ModuloExpression(left, right));
                }
                else{
                    expressionStack.push(new ExponentiationExpression(left, right));
                }
            }else {
                expressionStack.push(new Constant(Double.parseDouble(s)));
            }
        }
    }

    @Override
    public boolean isValidInput(String[] userInput, VariableBindings variableBindings) {
        //check unary operands
        //1) The first two elements are operands(values), and
        //2) The last element is an operator, and
        //3) For every n values there are n-1 operator(s)
        if(userInput.length == 1){
            return isOperand(userInput[0], variableBindings);
        }else if(userInput.length < 3 ||
                !isOperand(userInput[0], variableBindings) || !isOperand(userInput[1], variableBindings) ||
                isOperand(userInput[userInput.length - 1], variableBindings)){
            return false;
        }

        int operatorCount = 0;
        int operandCount = 0;

        for(String s : userInput){
            if(isOperand(s, variableBindings)){
                operandCount++;
            }else if(isOperator(s)){
                operatorCount++;
            }else{
                return false;
            }
        }

        return operandCount - 1 == operatorCount;
    }
}
