package calculator.project2calculatorgui;

import javafx.beans.binding.BooleanExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    //FXML variables
    @FXML
    private TextField expressionText;
    @FXML
    private ComboBox<String> variableComboBox;
    @FXML
    private ComboBox<String> historyComboBox;
    @FXML
    private RadioButton prefixRadioButton;
    @FXML
    private TextField valueField;
    @FXML
    private TextField nameField;
    @FXML
    private Label errorMessage;
    @FXML
    private Label variableErrorMessage;
    @FXML
    private Button addVariableButton;

    //Controller variables
    private static final String NAME_REGEX = "^[a-zA-Z]\\w*$";
    private ObservableList<String> observableHistory;
    private ObservableList<String> currentVariables;
    private Parser parser;
    private History history;
    private VariableBindings variableBindings;
    private NotationType notationType;
    private Timer timer;

    private final EventHandler<ActionEvent> handleButtonClick= e -> {
        errorMessage.setVisible(false);
        Button clicked = (Button) e.getSource();
        String buttonText = clicked.getText();
        //Changing the if statement to switch statement as SonarLint recommends ends up producing even more SonarLint errors, so I am deciding to leave as is.
        if(buttonText.equals("C/E")) {
            expressionText.setText("");
        }else if(buttonText.equals("<")) {
            String currentText = expressionText.getText();
            if(currentText.length() > 0) {
                expressionText.setText(currentText.substring(0, currentText.length() - 1));
            }
        }else if(buttonText.equals("space")) {
            expressionText.setText(expressionText.getText() + " ");
        }else if(buttonText.equals("=")) {
            String currentText = expressionText.getText();
            String [] inputTokens = currentText.split(" ");
            if(parser.isValidInput(inputTokens, variableBindings)) {
                Expression expression = parser.parseInput(inputTokens, variableBindings);
                try {
                    expressionText.setText(String.valueOf(expression.evaluate()));
                    history.updateHistory(expression);
                    observableHistory.setAll(history.getHistoryList(notationType));
                    historyComboBox.setItems(observableHistory);
                } catch (ArithmeticException arithmeticException) {
                    errorMessage.setText("Error: Cannot divide by zero");
                    errorMessage.setVisible(true);
                }
            }else{
                errorMessage.setText("Error: Invalid expression");
                errorMessage.setVisible(true);
            }
        }else {
            expressionText.setText(expressionText.getText() + buttonText);
        }
    };

    /**
     * Function to initialize the controller & GUI
     */
    @FXML
    public void initialize() {
        parser = new PrefixParser();
        notationType = NotationType.PREFIX;
        history = new History();
        variableBindings = new VariableBindings();
        observableHistory = FXCollections.observableList(new ArrayList<>());
        currentVariables = FXCollections.observableList(new ArrayList<>());
        timer = new Timer();

        this.addVariableButton.disableProperty().bind(BooleanExpression.booleanExpression(
                this.valueField.textProperty().isEmpty().or(this.nameField.textProperty().isEmpty())
        ));
    }

    /**
     * Function to handle button clicks within the keypad
     * @param actionEvent the event that triggered the function
     */
    public void handleButtonClick(ActionEvent actionEvent) {
        handleButtonClick.handle(actionEvent);
    }

    /**
     * Function to handle the switch between prefix and postfix notation
     */
    @FXML
    public void handleNotationSwitch() {
        if (prefixRadioButton.isSelected()) {
            parser = new PrefixParser();
            notationType = NotationType.PREFIX;
        } else {
            parser = new PostfixParser();
            notationType = NotationType.POSTFIX;
        }
        observableHistory.setAll(history.getHistoryList(notationType));
    }

    /**
     * Function to handle selection of history item from the combo box
     */
    @FXML
    public void handleHistorySelection() {
        String selected = historyComboBox.getSelectionModel().getSelectedItem();
        int index = historyComboBox.getSelectionModel().getSelectedIndex();
        if(selected != null) {
            expressionText.setText(expressionText.getText() + selected);
        }
        history.mostRecentToTop(index);
        observableHistory.setAll(history.getHistoryList(notationType));
    }

    /**
     * Function to handle the creation of a new variable
     */
    @FXML
    public void handleNewVariable() {
        variableErrorMessage.setVisible(false);
        String name = nameField.getText();
        String value = valueField.getText();
        if(name.length() > 0 && value.length() > 0 && name.matches(NAME_REGEX) && value.matches(Parser.DOUBLE_REGEX)) {
            variableBindings.addVariable(name, Double.parseDouble(value));
            currentVariables.setAll(variableBindings.getVariableList());
            variableComboBox.setItems(currentVariables);

            nameField.clear();
            valueField.clear();
        }else{
            variableErrorMessage.setText("Error: Invalid variable");
            variableErrorMessage.setVisible(true);
            timer = new Timer();
            TimerTask removeMessage = new TimerTask() {
                @Override
                public void run() {
                    variableErrorMessage.setVisible(false);
                }
            };
            timer.schedule(removeMessage, 5000);
        }
    }

    /**
     * Function to handle selection of variable from the combo box
     */
    @FXML
    public void handleVariableSelection() {
        String [] selected = variableComboBox.getSelectionModel().getSelectedItem().split(" ");
        if(selected.length > 0) {
            expressionText.setText(expressionText.getText() + selected[0]);
        }
    }
}