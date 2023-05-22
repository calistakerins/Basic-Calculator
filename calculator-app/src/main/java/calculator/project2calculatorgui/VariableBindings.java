package calculator.project2calculatorgui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableBindings {
    private final HashMap<String, Double> bindings;

    /**
     * Constructor for VariableBindings
     */
    public VariableBindings(){
        bindings = new HashMap<>();
    }

    /**
     * Adds a new variable to the bindings
     * @param variableName name of the variable
     * @param value value of the variable
     */
    public void addVariable(String variableName, double value){
        bindings.put(variableName, value);
    }

    /**
     * @param variableName name of the variable
     * @return value of the variable matching the key name, or null if the variable does not exist
     */
    public double getVariableValue(String variableName){
        return bindings.get(variableName);
    }

    /**
     * Function to convert the bindings to a list of strings that can be used in an ObservableList
     * @return list of all variables in the bindings as Strings
     */
    public List<String> getVariableList(){
        List<String> stringList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : bindings.entrySet()){
            stringList.add(entry.getKey() + " = " + entry.getValue());
        }
        return stringList;
    }

    /**
     * @param variableName name of the variable
     * @return true if the variable exists in the bindings, false otherwise
     */
    public boolean containsVariable(String variableName){
        return bindings.containsKey(variableName);
    }
}
