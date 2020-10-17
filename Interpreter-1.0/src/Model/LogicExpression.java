package Model;

import Exceptions.GeneralException;

public class LogicExpression implements GeneralExpression {
    private GeneralExpression first_expression, second_expression;
    private int operation;

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        return null;
    }
}
