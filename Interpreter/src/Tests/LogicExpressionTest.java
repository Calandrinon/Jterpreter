package Tests;

import Model.ADT.DictionaryInterface;
import Model.ADT.TheDictionary;
import Model.Expression.ArithmeticExpression;
import Model.Expression.LogicExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogicExpressionTest {
    @Test
    void testAndOperator() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();

        symbolTable.put("a", new BoolValue(true));
        symbolTable.put("b", new BoolValue(true));
        LogicExpression logicExpression = new LogicExpression(
                new VariableExpression("a"), "&&", new VariableExpression("b"));
        BoolValue value = (BoolValue) logicExpression.evaluate(symbolTable);
        Assertions.assertEquals(value.getValue(), true);
    }

    @Test
    void testOrOperator() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();

        symbolTable.put("a", new BoolValue(true));
        symbolTable.put("b", new BoolValue(false));
        LogicExpression logicExpression = new LogicExpression(
                new VariableExpression("a"), "||", new VariableExpression("b"));
        BoolValue value = (BoolValue) logicExpression.evaluate(symbolTable);
        Assertions.assertEquals(value.getValue(), true);
    }

    @Test
    void testEqualityOperator() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();
        Value arithmeticExpressionResult = new ArithmeticExpression("+",
                new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(1))).evaluate(symbolTable);
        symbolTable.put("a",arithmeticExpressionResult);
        symbolTable.put("b", new IntValue(3));
        LogicExpression logicExpression = new LogicExpression(
                new VariableExpression("a"), "==", new VariableExpression("b"));
        BoolValue value = (BoolValue) logicExpression.evaluate(symbolTable);
        Assertions.assertEquals(value.getValue(), true);
    }

    @Test
    void testNonEqualityOperator() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();
        Value arithmeticExpressionResult = new ArithmeticExpression("+",
                new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(1))).evaluate(symbolTable);
        symbolTable.put("a",arithmeticExpressionResult);
        symbolTable.put("b", new IntValue(3));
        LogicExpression logicExpression = new LogicExpression(
                new VariableExpression("a"), "!=", new VariableExpression("b"));
        BoolValue value = (BoolValue) logicExpression.evaluate(symbolTable);
        Assertions.assertEquals(value.getValue(), false);
    }

    @Test
    void testLessThanOperator() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();
        Value arithmeticExpressionResult = new ArithmeticExpression("+",
                new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(1))).evaluate(symbolTable);
        symbolTable.put("a",arithmeticExpressionResult);
        symbolTable.put("b", new IntValue(9000));
        LogicExpression logicExpression = new LogicExpression(
                new VariableExpression("a"), "<", new VariableExpression("b"));
        BoolValue value = (BoolValue) logicExpression.evaluate(symbolTable);
        Assertions.assertEquals(value.getValue(), true);
    }

    @Test
    void testLessThanOrEqualToOperator() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();
        Value arithmeticExpressionResult = new ArithmeticExpression("+",
                new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(1))).evaluate(symbolTable);
        symbolTable.put("a",arithmeticExpressionResult);
        symbolTable.put("b", new IntValue(3));
        LogicExpression logicExpression = new LogicExpression(
                new VariableExpression("a"), "<=", new VariableExpression("b"));
        BoolValue value = (BoolValue) logicExpression.evaluate(symbolTable);
        Assertions.assertEquals(value.getValue(), true);
    }

    @Test
    void testGreaterThanOperator() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();
        Value arithmeticExpressionResult = new ArithmeticExpression("+",
                new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(1))).evaluate(symbolTable);
        symbolTable.put("a",arithmeticExpressionResult);
        symbolTable.put("b", new IntValue(3));
        LogicExpression logicExpression = new LogicExpression(
                new VariableExpression("b"), ">", new VariableExpression("a"));
        BoolValue value = (BoolValue) logicExpression.evaluate(symbolTable);
        Assertions.assertEquals(value.getValue(), false);
    }

    @Test
    void testGreaterThanOrEqualToOperator() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();
        Value arithmeticExpressionResult = new ArithmeticExpression("+",
                new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(1))).evaluate(symbolTable);
        symbolTable.put("a",arithmeticExpressionResult);
        symbolTable.put("b", new IntValue(3));
        LogicExpression logicExpression = new LogicExpression(
                new VariableExpression("b"), ">=", new VariableExpression("a"));
        BoolValue value = (BoolValue) logicExpression.evaluate(symbolTable);
        Assertions.assertEquals(value.getValue(), true);
    }

    @Test
    void testToString() {
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<>();
        LogicExpression logicExpression = new LogicExpression(
                new ValueExpression(new IntValue(0)),
                "==",
                new ValueExpression(new IntValue(0))
        );
        Assertions.assertEquals(logicExpression.toString(), "0 == 0");
    }
}