package Model;

import Exceptions.GeneralException;

public class ValueExpression extends GeneralExpression {
    private Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
