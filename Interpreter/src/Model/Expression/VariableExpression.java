package Model.Expression;

import Exceptions.DictionaryException;
import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Value.Value;

public class VariableExpression extends GeneralExpression {
    private String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws DictionaryException {
        return table.lookup(id);
    }

    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws TypeException {
        return typeEnvironment.lookup(id);
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "@"+id;
    }
}
