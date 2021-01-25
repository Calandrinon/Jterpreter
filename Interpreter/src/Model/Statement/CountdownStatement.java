package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountdownStatement implements StatementInterface {
    private VariableExpression var;
    private static Lock lock = new ReentrantLock();

    public CountdownStatement(VariableExpression var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        lock.lock();
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        DictionaryInterface<Integer, Integer> latchTable = state.getLatchTable();

        try {
            Integer index = ((IntValue) symbolTable.lookup(var.getId())).getValue();
            try {
                Integer latchValue = latchTable.lookup(index);
                if (latchValue > 0) {
                    latchTable.put(index, latchValue - 1);
                    stack.push(new PrintStatement(new ValueExpression(new StringValue(String.valueOf(state.getId())))));
                    state.setExecutionStack(stack);
                    state.setLatchTable(latchTable);
                }
            } catch (DictionaryException de) {
                throw new DictionaryException("The index cannot be found in the LatchTable.");
            }
        } catch (DictionaryException de) {
            throw new DictionaryException("The variable cannot be found in the SymbolTable.");
        }
        lock.unlock();
        return null;
    }


    @Override
    public String toString(){
        return "countdown( " + var + " )";
    }


    @Override
    public StatementInterface clone() {
        return new CountdownStatement(var);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type varType = var.typecheck(typeEnvironment);

        if (!varType.equals(new IntType()))
            throw new TypeException("The variable in a CountdownStatement should have the type int.");

        return typeEnvironment;
    }
}
