import calculator.project2calculatorgui.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TestHistory {
    private History history;

    @BeforeEach
    public void setUp() {
        history = new History();
    }

    @Test
    void testUpdateHistory() {
        Expression test = new AdditionExpression(new Constant(5), new Constant(5));
        history.updateHistory(test);
        Assertions.assertEquals(test, history.getHistoryAt(0));
    }

    @Test
    void testGetHistoryAt() {
        Expression test = new AdditionExpression(new Constant(5), new Constant(5));
        history.updateHistory(test);
        Assertions.assertEquals(test, history.getHistoryAt(0));
    }

    @Test
    void testMostRecentToTop() {
        Expression test1 = new AdditionExpression(new Constant(5), new Constant(5));
        Expression test2 = new AdditionExpression(new Constant(6), new Constant(6));
        history.updateHistory(test1);
        history.updateHistory(test2);
        history.mostRecentToTop(1);
        Assertions.assertEquals(test1, history.getHistoryAt(0));
    }

    @Test
    void testUpdateHistoryWhenFull(){
        history.updateHistory(new AdditionExpression(new Constant(1), new Constant(1)));
        history.updateHistory(new AdditionExpression(new Constant(2), new Constant(2)));
        history.updateHistory(new AdditionExpression(new Constant(3), new Constant(3)));
        history.updateHistory(new AdditionExpression(new Constant(4), new Constant(4)));
        history.updateHistory(new AdditionExpression(new Constant(5), new Constant(5)));
        history.updateHistory(new AdditionExpression(new Constant(6), new Constant(6)));
        history.updateHistory(new AdditionExpression(new Constant(7), new Constant(7)));
        history.updateHistory(new AdditionExpression(new Constant(8), new Constant(8)));
        history.updateHistory(new AdditionExpression(new Constant(9), new Constant(9)));
        history.updateHistory(new AdditionExpression(new Constant(10), new Constant(10)));
        history.updateHistory(new AdditionExpression(new Constant(11), new Constant(11)));
        history.updateHistory(new AdditionExpression(new Constant(12), new Constant(12)));
        history.updateHistory(new AdditionExpression(new Constant(13), new Constant(13)));
        history.updateHistory(new AdditionExpression(new Constant(14), new Constant(14)));
        history.updateHistory(new AdditionExpression(new Constant(15), new Constant(15)));
        history.updateHistory(new AdditionExpression(new Constant(16), new Constant(16)));
        history.updateHistory(new AdditionExpression(new Constant(17), new Constant(17)));
        history.updateHistory(new AdditionExpression(new Constant(18), new Constant(18)));
        history.updateHistory(new AdditionExpression(new Constant(19), new Constant(19)));
        history.updateHistory(new AdditionExpression(new Constant(20), new Constant(20)));
        history.updateHistory(new AdditionExpression(new Constant(21), new Constant(21)));
        history.updateHistory(new AdditionExpression(new Constant(22), new Constant(22)));
        history.updateHistory(new AdditionExpression(new Constant(23), new Constant(23)));
        history.updateHistory(new AdditionExpression(new Constant(24), new Constant(24)));
        history.updateHistory(new AdditionExpression(new Constant(25), new Constant(25)));
        history.updateHistory(new AdditionExpression(new Constant(26), new Constant(26)));
        history.updateHistory(new AdditionExpression(new Constant(27), new Constant(27)));
        Assertions.assertEquals(26, history.getHistoryList(NotationType.PREFIX).size());
        Assertions.assertEquals(new AdditionExpression(new Constant(27), new Constant(27)), history.getHistoryAt(0));
    }

    @Test
    void testGetHistoryListPrefix() {
        Expression test1 = new AdditionExpression(new Constant(5), new Constant(5));
        Expression test2 = new AdditionExpression(new Constant(6), new Constant(6));
        history.updateHistory(test1);
        history.updateHistory(test2);
        List<String> expected = new ArrayList<>();
        expected.add(test2.toString());
        expected.add(test1.toString());
        Assertions.assertEquals(expected, history.getHistoryList(NotationType.PREFIX));
    }

    @Test
    void testGetHistoryListPostfix() {
        Expression test1 = new AdditionExpression(new Constant(5), new Constant(5));
        Expression test2 = new AdditionExpression(new Constant(6), new Constant(6));
        history.updateHistory(test1);
        history.updateHistory(test2);
        List<String> expected = new ArrayList<>();
        expected.add(test2.toStringPostfix());
        expected.add(test1.toStringPostfix());
        Assertions.assertEquals(expected, history.getHistoryList(NotationType.POSTFIX));
    }

}
