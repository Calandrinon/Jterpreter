package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.ListInterface;
import Model.ADT.StackInterface;
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

public class AwaitBarrierStatement implements StatementInterface {
    private VariableExpression var;
    private Lock lock = new ReentrantLock();

    public AwaitBarrierStatement(VariableExpression var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        lock.lock();
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        DictionaryInterface<Integer, Pair<Integer, ListInterface<Integer>>> barrierTable = state.getBarrierTable();

        try {
            int index = ((IntValue)symbolTable.lookup(var.getId())).getValue();
            try {
                Pair<Integer, ListInterface<Integer>> barrierValue = barrierTable.lookup(index);
                ListInterface<Integer> threads = barrierValue.getValue();
                Integer n1 = barrierValue.getKey();
                Integer nl = threads.size();

                if (n1 > nl) {
                    if (barrierValue.getValue().contains(state.getId())) {
                        stack.push(this);
                    } else {
                        threads.add(state.getId());
                        state.getBarrierTable().put(index, new Pair<>(n1, threads));
                    }
                }
            } catch (DictionaryException de) {
                throw new DictionaryException("The index cannot be found in the barrier table.");
            }
        } catch (DictionaryException de){
            throw new DictionaryException("The variable cannot be found in the symbol table.");
        } finally {
            lock.unlock();
        }

        return null;
    }

    @Override
    public StatementInterface clone() {
        return new AwaitBarrierStatement(var);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type varType = var.typecheck(typeEnvironment);

        if (!varType.equals(new IntType()))
            throw new TypeException("The variable in an AwaitBarrierStatement should be an integer.");

        return typeEnvironment;
    }

    public String toString() {
        return "awaitB(" + var.toString() + ")";
    }
}
