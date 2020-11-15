package Model.Expression;
import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Value.Value;

public abstract class GeneralExpression {
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heapInterface) throws GeneralException {
        return null;
    }
}
