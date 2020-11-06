package Model.Statement;

import Exceptions.GeneralException;
import Model.ProgramState;
import Model.ADT.StackInterface;

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
    public ProgramState execute(ProgramState state) throws GeneralException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);

        return state;
    }

    public String toString() {
        return "(" + first.toString() + " | " + second.toString() + ")";
    }
}
