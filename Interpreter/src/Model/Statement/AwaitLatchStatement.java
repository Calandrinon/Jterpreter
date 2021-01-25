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

public class AwaitLatchStatement implements StatementInterface {
    private VariableExpression var;

    public AwaitLatchStatement(VariableExpression var){
        this.var = var;
    }


    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        DictionaryInterface<Integer, Integer> latchTable = state.getLatchTable();
        HeapInterface heap = state.getHeap();

        try {
            Integer index = ((IntValue)symbolTable.lookup(var.getId())).getValue();
            try {
                Integer latchValue = latchTable.lookup(index);
                if (latchValue != 0) {
                    stack.push(this);
                    state.setExecutionStack(stack);
                }
            } catch (DictionaryException de) {
                throw new DictionaryException("The index cannot be found in the LatchTable.");
            }

        } catch (DictionaryException de) {
            throw new DictionaryException("The variable cannot be found in the SymbolTable.");
        }

        return null;
    }

    @Override
    public String toString(){
        return "await( " + var + " )";
    }

    @Override
    public StatementInterface clone() {
        return new AwaitLatchStatement(var);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type varType = var.typecheck(typeEnvironment);

        if (!varType.equals(new IntType()))
            throw new TypeException("The variable in an AwaitStatement should have the type int.");

        return typeEnvironment;
    }
}
