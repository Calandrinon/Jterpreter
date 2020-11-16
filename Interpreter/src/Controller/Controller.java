package Controller;
import Exceptions.GeneralException;
import Exceptions.ListException;
import Exceptions.StackException;
import Model.ADT.ListInterface;
import Model.ProgramState;
import Model.ADT.StackInterface;
import Model.Statement.StatementInterface;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.RepositoryInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private RepositoryInterface repository;

    public Controller(RepositoryInterface repository) {
        this.repository = repository;
    }

    public Map<Integer, Value> unsafeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e->symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public ProgramState oneStepExecution(ProgramState state) throws GeneralException, IOException {
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
            state.getHeap().setContent(this.unsafeGarbageCollector(this.getAddressesFromSymbolTable(state.getSymbolTable().getContent().values()), state.getHeap().getContent()));
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
