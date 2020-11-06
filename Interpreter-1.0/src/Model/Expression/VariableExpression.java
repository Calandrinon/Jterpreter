package Model.Expression;

import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.Value.Value;

public class VariableExpression extends GeneralExpression {
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
