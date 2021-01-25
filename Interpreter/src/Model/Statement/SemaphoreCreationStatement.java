package Model.Statement;

import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.*;
import Model.Expression.GeneralExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreCreationStatement implements StatementInterface {
    private static Lock lock = new ReentrantLock();
    private VariableExpression var;
    private GeneralExpression expression;

    public SemaphoreCreationStatement(VariableExpression var, GeneralExpression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        lock.lock();
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        SemaphoreInterface semaphoreTable = state.getSemaphoreTable();

        Integer number1 = ((IntValue)expression.evaluate(symbolTable, heap)).getValue();
        Integer location = state.getSemaphoreTable().getSemaphoreAddress(state);
        semaphoreTable.put(location, new Pair<>(number1, new TheList<Integer>()));
        symbolTable.put(var.getId(), new IntValue(location));

        state.setSemaphoreTable(semaphoreTable);
        state.setSymbolTable(symbolTable);
        lock.unlock();

        return null;
    }

    @Override
    public StatementInterface clone() {
        return new SemaphoreCreationStatement(var, expression);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type varType = var.typecheck(typeEnvironment);
        Type expType = expression.typecheck(typeEnvironment);

        if (!varType.equals(new IntType()))
            throw new TypeException("The variable type in a SemaphoreCreationStatement should be int.");

        if (!expType.equals(new IntType()))
            throw new TypeException("The type of the expression's result in a SemaphoreCreationStatement should be int.");

        return typeEnvironment;
    }
}
