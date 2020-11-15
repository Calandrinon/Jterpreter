package Model.Expression;

import Exceptions.GeneralException;
import Exceptions.HeapException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.Value;

public class HeapReadExpression extends GeneralExpression {
    private VariableExpression expression;

    public HeapReadExpression(VariableExpression expression) {
        this.expression = expression;
    }

    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws GeneralException {
        Value expressionValue = expression.evaluate(table, heap);

        if (!(expressionValue.getType() instanceof RefType))
            throw new HeapException("The sub-expression in the HeapReadExpression is not a variable of type RefType.");

        RefValue refValue = (RefValue)expressionValue;
        int refAddress = refValue.getAddress();
        System.out.println("REFADDRESS: " + refAddress);

        System.out.println(heap.toString());

        if (!heap.isDefined(refAddress))
            throw new HeapException("The address is not a valid key in the heap table.");

        return heap.lookup(refAddress);
    }

    public String toString() {
        return "*" + expression.getId();
    }
}
