package Model.Statement;

import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.ArithmeticExpression;
import Model.Expression.GeneralExpression;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.io.IOException;

public class SleepStatement implements StatementInterface {
    private GeneralExpression duration;

    public SleepStatement(GeneralExpression duration) {
        this.duration = duration;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        Value number = duration.evaluate(symbolTable, heap);
        IntValue actualNumber = (IntValue) number;

        if (!number.equals(new IntValue(0)))
            stack.push(new SleepStatement(new ArithmeticExpression("-", new ValueExpression(actualNumber), new ValueExpression(new IntValue(1)))));

        return null;
    }

    @Override
    public StatementInterface clone() {
        return new SleepStatement(duration);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        /**
        Type durationType = duration.typecheck(typeEnvironment);

        if (!durationType.equals(new IntType()))
            throw new TypeException("The duration parameter in a SleepStatement should be an IntValue.");
        **/
        return typeEnvironment;
    }

    public String toString() {
        return "sleep(" + duration.toString() + ")";
    }
}
