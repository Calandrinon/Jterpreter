package Model.Statement;

import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStatement implements StatementInterface {
    private VariableExpression var;
    private static Lock lock = new ReentrantLock();

    public NewLockStatement(VariableExpression var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        lock.lock();
        DictionaryInterface<Integer, Integer> lockTable = state.getLockTable();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();

        int address = state.getLockTableAddress();

        lockTable.put(address, -1);
        symbolTable.put(var.getId(), new IntValue(address));

        state.setSymbolTable(symbolTable);
        state.setLockTable(lockTable);
        lock.unlock();
        return null;
    }

    @Override
    public StatementInterface clone() {
        return new NewLockStatement(var);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type varType = var.typecheck(typeEnvironment);

        if (!varType.equals(new IntType()))
            throw new TypeException("The variable in a newLockStatement should have the type int.");

        return typeEnvironment;
    }

    public String toString() {
        return "newLock(" + var.toString() + ")";
    }
}
