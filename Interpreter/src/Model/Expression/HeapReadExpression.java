package Model.Expression;

import Exceptions.GeneralException;
import Exceptions.HeapException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.RefValue;
import Model.Value.Value;

public class HeapReadExpression extends GeneralExpression {
    private GeneralExpression expression;

    public HeapReadExpression(GeneralExpression expression) {
        this.expression = expression;
    }

    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws GeneralException {
        Value expressionValue = expression.evaluate(table, heap);

        if (!(expressionValue.getType() instanceof RefType))
            throw new HeapException("The sub-expression in the HeapReadExpression is not a variable of type RefType.");

        RefValue refValue = (RefValue)expressionValue;
        int refAddress = refValue.getAddress();

        if (!heap.isDefined(refAddress))
            throw new HeapException("The address is not a valid key in the heap table.");

        return heap.lookup(refAddress);
    }

    public Type typecheck(DictionaryInterface<String, Type> typeEnvironment) throws TypeException {
        Type type = expression.typecheck(typeEnvironment);
        if (!(type instanceof RefType))
            throw new HeapException("The argument of the heapReadExpression is not a RefType.");

        RefType refType = (RefType)type;
        return refType.getReferredType();
    }

    public String toString() {
        return "rh(" + expression.toString() + ")";
    }
}
