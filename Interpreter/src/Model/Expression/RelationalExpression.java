package Model.Expression;

import Exceptions.GeneralException;
import Exceptions.LogicException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class RelationalExpression extends GeneralExpression {
    private GeneralExpression first_expression, second_expression;
    private String operation;

    public RelationalExpression(GeneralExpression first_expression, String operation, GeneralExpression second_expression) {
        String[] allowed_operators = new String[]{"==", "!=", "<", ">", "<=", ">="};
        boolean is_operator_valid = false;

        for (String operator: allowed_operators) {
            if (operator == operation) {
                is_operator_valid = true;
                break;
            }
        }

        if (!is_operator_valid) {
            throw new LogicException("The relational expression contains invalid operators.");
        }

        this.operation = operation;
        this.first_expression = first_expression;
        this.second_expression = second_expression;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws GeneralException {
        Value first_expression_result = first_expression.evaluate(table, heap);
        Value second_expression_result = second_expression.evaluate(table, heap);

        switch (operation) {
            case "==":
                return new BoolValue(first_expression_result.equals(second_expression_result));
            case "!=":
                return new BoolValue(!first_expression_result.equals(second_expression_result));
            case "<":
                return new BoolValue(((IntValue) first_expression_result).getValue()
                        < ((IntValue) second_expression_result).getValue());
            case "<=":
                return new BoolValue(((IntValue) first_expression_result).getValue()
                        <= ((IntValue) second_expression_result).getValue());
            case ">":
                return new BoolValue(((IntValue) first_expression_result).getValue()
                        > ((IntValue) second_expression_result).getValue());
            case ">=":
                return new BoolValue(((IntValue) first_expression_result).getValue()
                        >= ((IntValue) second_expression_result).getValue());
        }

        throw new LogicException("Unrecognized operator in expression.");
    }

    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws TypeException {
        Type first_type, second_type;
        first_type = first_expression.typecheck(typeEnvironment);
        second_type = second_expression.typecheck(typeEnvironment);

        if (!this.operation.equals("==") && !this.operation.equals("!=")) {
            if (!first_type.equals(new IntType()))
                throw new LogicException("The first expression should evaluate to an integer.");

            if (!second_type.equals(new IntType()))
                throw new LogicException("The second expression should evaluate to an integer.");
        } else {
            if (!first_type.equals(new BoolType()))
                throw new LogicException("The first expression should evaluate to a boolean.");

            if (!second_type.equals(new BoolType()))
                throw new LogicException("The second expression should evaluate to a boolean.");
        }

        return new BoolType();
    }

    public String toString() {
        return first_expression.toString() + " " + operation + " " + second_expression.toString();
    }
}
