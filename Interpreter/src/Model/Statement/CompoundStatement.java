package Model.Statement;

import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.ADT.StackInterface;
import Model.Type.Type;

public class CompoundStatement implements StatementInterface {
    StatementInterface first, second;

    public CompoundStatement(StatementInterface first, StatementInterface second) {
        this.first = first;
        this.second = second;
    }

    public CompoundStatement clone() {
        return new CompoundStatement(this.first, this.second);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        return second.typecheck(first.typecheck(typeEnvironment));
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);

        return null;
    }

    public String toString() {
        return "(" + first.toString() + " | " + second.toString() + ")";
    }
}
