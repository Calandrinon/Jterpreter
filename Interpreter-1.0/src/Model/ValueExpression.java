package Model;

import Exceptions.GeneralException;

public class ValueExpression implements GeneralExpression {
    private Value value;

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        return value;
    }
}
