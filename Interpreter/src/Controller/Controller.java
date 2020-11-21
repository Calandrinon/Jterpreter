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
    private ProgramState state;

    public Controller(RepositoryInterface repository, ProgramState state) {
        this.repository = repository;
        this.state = state;
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

    public void completeExecution() throws GeneralException, IOException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();

        this.repository.logProgramState(state);
        while (!stack.isEmpty()) {
            state = state.oneStepExecution();
            //this.repository.pushFront(state);
            stack = state.getExecutionStack();
            this.repository.logProgramState(state);
            state.getHeap().setContent(this.unsafeGarbageCollector(this.getAddressesFromSymbolTable(state.getSymbolTable().getContent().values()), state.getHeap().getContent()));
            System.out.println(state.toString());
        }

        this.repository.clear();
    }
}
