package Model.Expression;
import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.Value.Value;

public abstract class GeneralExpression {
    public Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        return null;
    }
}
