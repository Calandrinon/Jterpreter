package Model.Expression;

import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
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

    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws TypeException {
        return value.getType();
    }

    public String toString() {
        return value.toString();
    }
}
