package Model;
import Exceptions.GeneralException;

public interface GeneralExpression {
    Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException;
}
