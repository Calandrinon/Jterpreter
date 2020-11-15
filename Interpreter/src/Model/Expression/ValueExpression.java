package Model.Expression;

import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Value.Value;

public class ValueExpression extends GeneralExpression {
    private Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws GeneralException {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
