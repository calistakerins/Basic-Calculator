package calculator.project2calculatorgui;

import java.util.ArrayList;
import java.util.List;

public class History {
    private static final int HISTORY_SIZE = 26;
    private final List<Expression> historyList;

    public History() {
        historyList = new ArrayList<>();
    }

    /**
     * Function to get the expression at the character index
     * @param index int representation of the index
     * @return Expression at the index
     */
    public Expression getHistoryAt(int index) {
        return historyList.get(index);
    }

    /**
     * Function to add an expression to the history
     * @param e Expression to add to the history
     */
    public void updateHistory(Expression e) {
        historyList.add(0, e);
        if(historyList.size() > HISTORY_SIZE){
            historyList.remove(historyList.size() - 1);
        }
    }

    /**
     * Function to move the selected expression at the index to the top of the history
     * @param index int representation of the index
     */
    public void mostRecentToTop(int index) {
        Expression temp = historyList.get(index);
        historyList.remove(index);
        historyList.add(0, temp);
    }

    /**
     * Function to get the history list
     * @param notation NotationType to get the history in
     * @return List<String> representing the history
     */
    public List<String> getHistoryList(NotationType notation) {
        List<String> stringList = new ArrayList<>();
        for (Expression expression : this.historyList) {
            if (notation.equals(NotationType.POSTFIX)) {
                stringList.add(expression.toStringPostfix());
            } else {
                stringList.add(expression.toString());
            }
        }
        return stringList;
    }
}
