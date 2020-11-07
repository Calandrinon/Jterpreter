package Controller;
import Exceptions.GeneralException;
import Exceptions.ListException;
import Exceptions.StackException;
import Model.ProgramState;
import Model.ADT.StackInterface;
import Model.Statement.StatementInterface;
import Repository.RepositoryInterface;

import java.io.IOException;

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

    public void completeExecution() throws GeneralException, IOException {
        ProgramState state = repository.getCurrentProgramState();
        StackInterface<StatementInterface> stack = state.getExecutionStack();

        this.repository.logProgramState();
        while (!stack.isEmpty()) {
            state = this.oneStepExecution(state);
            this.repository.pushFront(state);
            stack = state.getExecutionStack();
            this.repository.logProgramState();
            System.out.println(state.toString());
        }

        this.repository.clear();
    }

    public void addProgramState(ProgramState state) {
        this.repository.add(state);
    }

    public ProgramState getCurrentProgramState() throws ListException {
        return repository.getCurrentProgramState();
    }
}
