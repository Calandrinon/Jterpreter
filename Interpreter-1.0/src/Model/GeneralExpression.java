package Model;
import Exceptions.GeneralException;

public abstract class GeneralExpression {
    Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        return null;
    }
}
