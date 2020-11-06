package Model.Expression;

import Exceptions.GeneralException;
import Exceptions.LogicException;
import Model.Value.BoolValue;
import Model.ADT.DictionaryInterface;
import Model.Value.IntValue;
import Model.Value.Value;

public class LogicExpression extends GeneralExpression {
    private GeneralExpression first_expression, second_expression;
    private String operation;

    public LogicExpression(GeneralExpression first_expression, String operation, GeneralExpression second_expression) {
        String[] allowed_operators = new String[]{"&&", "||", "==", "!=", "<", ">", "<=", ">="};
        boolean is_operator_valid = false;

        for (String operator: allowed_operators) {
            if (operator == operation) {
                is_operator_valid = true;
                break;
            }
        }

        if (!is_operator_valid) {
            throw new LogicException("The logical expression contains invalid operators.");
        }

        this.operation = operation;
        this.first_expression = first_expression;
        this.second_expression = second_expression;
    }

    private void checkTypeEquality(DictionaryInterface<String, Value> table) throws LogicException {
        Value first_expression_result = first_expression.evaluate(table);
        Value second_expression_result = second_expression.evaluate(table);

        if (first_expression_result.getType().equals(second_expression_result.getType()))
            throw new LogicException("The logic expression contains operands with distinct types.");
    }

    private void checkBooleanTypeEquality(DictionaryInterface<String, Value> table) throws LogicException {
        Value first_expression_result = first_expression.evaluate(table);
        Value second_expression_result = second_expression.evaluate(table);

        if (!(first_expression_result instanceof BoolValue && second_expression_result instanceof BoolValue))
            throw new LogicException("The result of one of the contained expressions is not an instance of the BoolValue class.");
    }

    private void checkIntegerTypeEquality(DictionaryInterface<String, Value> table) throws LogicException {
        Value first_expression_result = first_expression.evaluate(table);
        Value second_expression_result = second_expression.evaluate(table);

        if (!(first_expression_result instanceof IntValue && second_expression_result instanceof IntValue))
            throw new LogicException("The result of one of the contained expressions is not an instance of the IntValue class.");
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        Value first_expression_result = first_expression.evaluate(table);
        Value second_expression_result = second_expression.evaluate(table);

        switch (operation) {
            case "&&":
                this.checkBooleanTypeEquality(table);
                return new BoolValue(((BoolValue) first_expression_result).getValue()
                        && ((BoolValue) second_expression_result).getValue());
            case "||":
                this.checkBooleanTypeEquality(table);
                return new BoolValue(((BoolValue) first_expression_result).getValue()
                        || ((BoolValue) second_expression_result).getValue());
            case "==":
                return new BoolValue(first_expression_result.equals(second_expression_result));
            case "!=":
                return new BoolValue(!first_expression_result.equals(second_expression_result));
            case "<":
                this.checkIntegerTypeEquality(table);
                return new BoolValue(((IntValue) first_expression_result).getValue()
                        < ((IntValue) second_expression_result).getValue());
            case "<=":
                this.checkIntegerTypeEquality(table);
                return new BoolValue(((IntValue) first_expression_result).getValue()
                        <= ((IntValue) second_expression_result).getValue());
            case ">":
                this.checkIntegerTypeEquality(table);
                return new BoolValue(((IntValue) first_expression_result).getValue()
                        > ((IntValue) second_expression_result).getValue());
            case ">=":
                this.checkIntegerTypeEquality(table);
                return new BoolValue(((IntValue) first_expression_result).getValue()
                        >= ((IntValue) second_expression_result).getValue());
        }

        throw new LogicException("Unrecognized operator in expression.");
    }
}
