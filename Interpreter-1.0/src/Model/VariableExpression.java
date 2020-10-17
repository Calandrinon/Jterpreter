package Model;

import Exceptions.GeneralException;

public class VariableExpression implements GeneralExpression {
    private String id;

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        return table.lookup(id);
    }
}
