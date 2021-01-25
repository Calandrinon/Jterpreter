package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStatement implements StatementInterface {
    private VariableExpression var;
    private static Lock lock = new ReentrantLock();

    public LockStatement(VariableExpression var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        int index;

        lock.lock();
        try {
            index = ((IntValue)symbolTable.lookup(var.getId())).getValue();
            DictionaryInterface<Integer,Integer> lockTable = state.getLockTable();
            try {
                Integer lockValue = lockTable.lookup(index);

                if (lockValue == -1) {
                    lockTable.put(index, state.getId());
                    state.setLockTable(lockTable);
                } else {
                    StackInterface<StatementInterface> executionStack = state.getExecutionStack();
                    executionStack.push(this);
                    state.setExecutionStack(executionStack);
                }
            } catch (DictionaryException de) {
                throw new DictionaryException("The index does not exist in the lockTable.");
            }
        } catch (DictionaryException de) {
            throw new DictionaryException("The variable cannot be found in the SymbolTable.");
        }


        lock.unlock();
        return null;
    }

    @Override
    public StatementInterface clone() {
        return new LockStatement(var);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type varType = var.typecheck(typeEnvironment);

        if (!varType.equals(new IntType()))
            throw new TypeException("The variable in a LockStatement should have the type int.");

        return typeEnvironment;
    }

    public String toString() {
        return "lock(" + var.toString() + ")";
    }
}
