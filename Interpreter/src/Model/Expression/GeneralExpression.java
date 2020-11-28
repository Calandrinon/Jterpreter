package Model.Expression;
import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.Type;
import Model.Value.Value;

public abstract class GeneralExpression {
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heapInterface) throws GeneralException {
        return null;
    }

    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) {
        return null;
    }
}
