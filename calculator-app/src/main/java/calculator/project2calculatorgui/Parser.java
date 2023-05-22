package calculator.project2calculatorgui;

import java.util.Set;

public interface Parser {
    Set<String> OPERATORS = Set.of("+", "-", "*", "/", "^", "%");
    String DOUBLE_REGEX = "^-?\\d+(\\.\\d{1,7})?$";

    /**
     * Function that parses the validated user input and builds an expression tree
     * @param userInput the user input to be parsed
     * @param variableBindings VariableBindings to check if the string is a variable
     * @return Expression that represents the user input
     */
    Expression parseInput(String [] userInput, VariableBindings variableBindings);

    /**
     * @param userInput the user input to be validated
     * @return true if the user input is in valid postfix notation, false otherwise
     */
    boolean isValidInput(String [] userInput, VariableBindings variableBindings);

    /**
     * @param s String to be checked if it is an operand
     * @param variableBindings VariableBindings to check if the string is a variable
     * @return true if the string is an operand, false otherwise
     */
    default boolean isOperand(String s, VariableBindings variableBindings){
        return s.matches(DOUBLE_REGEX) || variableBindings.containsVariable(s);
    }

    /**
     * @param s String to be checked if it is an operator
     * @return true if the string is an operator, false otherwise
     */
    default boolean isOperator(String s){
        return OPERATORS.contains(s);
    }
}
