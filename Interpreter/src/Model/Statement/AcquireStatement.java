package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.*;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import javafx.util.Pair;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements StatementInterface {
    private VariableExpression var;
    private static Lock lock = new ReentrantLock();

    public AcquireStatement(VariableExpression var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        lock.lock();
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        DictionaryInterface<Integer, Pair<Integer, ListInterface<Integer>>> semaphoreTable = state.getSemaphoreTable().getSemaphore();

        try {
            Integer index = ((IntValue) symbolTable.lookup(var.getId())).getValue();
            try {
                Pair<Integer, ListInterface<Integer>> semaphoreValue = semaphoreTable.lookup(index);
                ListInterface<Integer> threads = semaphoreValue.getValue();
                Integer n = semaphoreValue.getKey();
                if(n != threads.size()) {
                    if(!threads.contains(state.getId())) {
                        threads.add(state.getId());
                        state.getSemaphoreTable().put(index, new Pair<>(n, threads));
                    }
                }else
                {
                    stack.push(this);
                    state.setExecutionStack(stack);
                }
            } catch (DictionaryException de) {
                throw new DictionaryException("The index cannot be found in the SemaphoreTable");
            }

        } catch (DictionaryException de) {
            throw new DictionaryException("The variable cannot be found in the SymbolTable");
        } finally {
            lock.unlock();
        }

        return null;
    }

    @Override
    public StatementInterface clone() {
        return new AcquireStatement(var);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type varType = var.typecheck(typeEnvironment);

        if (!varType.equals(new IntType()))
            throw new TypeException("The variable type in an AcquireStatement should be int.");

        return typeEnvironment;
    }
}
