package Model;

import Exceptions.GeneralException;

public class VariableExpression implements GeneralExpression {
    private String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        return table.lookup(id);
    }

    public String toString() {
        return "@"+id;
    }
}
