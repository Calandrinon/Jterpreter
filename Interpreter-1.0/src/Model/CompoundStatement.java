package Model;

import Exceptions.GeneralException;

public class CompoundStatement implements StatementInterface {
    StatementInterface first, second;

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);

        return state;
    }

    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }
}
