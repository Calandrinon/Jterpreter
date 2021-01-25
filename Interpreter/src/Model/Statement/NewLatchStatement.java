package Model.Statement;

import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Expression.GeneralExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements StatementInterface {
    private VariableExpression var;
    private GeneralExpression expression;

    private static Lock lock = new ReentrantLock();

    public NewLatchStatement(VariableExpression var, GeneralExpression expression){
        this.var = var;
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        lock.lock();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heapTable = state.getHeap();
        DictionaryInterface<Integer, Integer> latchTable = state.getLatchTable();

        int latchAddress = state.getLatchTableAddress();
        latchTable.put(latchAddress, ((IntValue)expression.evaluate(symbolTable, heapTable)).getValue());
        symbolTable.put(var.getId(), new IntValue(latchAddress));
        state.setSymbolTable(symbolTable);
        state.setHeap(heapTable);
        lock.unlock();

        return null;
    }

    @Override
    public StatementInterface clone() {
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type varType = var.typecheck(typeEnvironment);
        Type expressionType = expression.typecheck(typeEnvironment);

        if (!varType.equals(new IntType()))
            throw new TypeException("The variable in a NewLatchStatement should have the type int.");

        if (!expressionType.equals(new IntType()))
            throw new TypeException("The result of the expression in a NewLatchStatement should have the type int.");

        return typeEnvironment;
    }
    @Override
    public String toString(){
        return "newLatch( " + var + ", " + expression.toString() + " )";
    }
}
