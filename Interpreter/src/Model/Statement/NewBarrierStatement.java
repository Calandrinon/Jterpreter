package Model.Statement;

import Exceptions.GeneralException;
import Model.ADT.*;
import Model.Expression.GeneralExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import javafx.util.Pair;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements StatementInterface {
    private VariableExpression var;
    private GeneralExpression expression;
    private static Lock lock = new ReentrantLock();

    public NewBarrierStatement(VariableExpression var, GeneralExpression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        lock.lock();
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        DictionaryInterface<Integer, Pair<Integer, ListInterface<Integer>>> barrierTable = state.getBarrierTable();

        Integer number = ((IntValue)expression.evaluate(symbolTable, heap)).getValue();
        Integer location = state.getBarrierAddress();
        barrierTable.put(location, new Pair<>(number, new TheList<>()));
        symbolTable.put(var.getId(), new IntValue(location));

        state.setBarrierTable(barrierTable);
        state.setSymbolTable(symbolTable);
        lock.unlock();
        return null;
    }

    @Override
    public StatementInterface clone() {
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        return null;
    }

    public String toString() {
        return "newBarrier(" + var.toString() + ", " + expression.toString() + ")";
    }
}
