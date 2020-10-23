package Controller;
import Exceptions.GeneralException;
import Exceptions.ListException;
import Exceptions.StackException;
import Model.ProgramState;
import Model.StackInterface;
import Model.StatementInterface;
import Model.TheStack;
import Repository.RepositoryInterface;

public class Controller {
    private RepositoryInterface repository;

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
    }

    public ProgramState oneStepExecution(ProgramState state) throws GeneralException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();

        if (stack.isEmpty())
            throw new StackException("Can't execute instruction: Program state stack is empty.");

        StatementInterface currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void completeExecution() throws GeneralException {
        ProgramState state = repository.getCurrentProgramState();
        StackInterface<StatementInterface> stack = state.getExecutionStack();

        while (!stack.isEmpty()) {
            this.oneStepExecution(state);
            stack = state.getExecutionStack();

            System.out.println(state.toString());
        }
    }

    public void addProgramState(ProgramState state) {
        this.repository.add(state);
    }

    public ProgramState getCurrentProgramState() throws ListException {
        return repository.getCurrentProgramState();
    }
}
