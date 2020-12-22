package Controller;
import Exceptions.GeneralException;
import Exceptions.ListException;
import Exceptions.StackException;
import Model.ADT.ListInterface;
import Model.ADT.TheList;
import Model.ProgramState;
import Model.ADT.StackInterface;
import Model.Statement.StatementInterface;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.Repository;
import Repository.RepositoryInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller {
    private RepositoryInterface repository;
    private ProgramState state;
    private ExecutorService executor;

    public Controller(RepositoryInterface repository, ProgramState state) {
        this.repository = repository;
        this.state = state;
        this.repository.add(state);
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer, Value> heap) {
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

    public TheList<ProgramState> removeCompletedStates(TheList<ProgramState> programList) {
        List<ProgramState> list = programList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());

        TheList<ProgramState> theList = new TheList<>();
        theList.setContainer(list);
        return theList;
    }

    public void completeExecution() throws GeneralException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        TheList<ProgramState> programList = removeCompletedStates(repository.getProgramList());

        while (programList.size() > 0) {
            programList.get(0).getHeap().setContent(this.safeGarbageCollector(this.getAddressesFromSymbolTable(state.getSymbolTable().getContent().values()), state.getHeap().getContent()));
            oneStepForAllStates(programList.getContainer());
            programList = removeCompletedStates(repository.getProgramList());
        }

        executor.shutdownNow();
        repository.setProgramList(programList.getContainer());
    }

    public void oneStepForAllStates(List<ProgramState> list) throws InterruptedException {
        list.forEach(state -> repository.logProgramState(state));

        List<Callable<ProgramState>> callList = list.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(()->{return p.oneStepExecution();}))
                .collect(Collectors.toList());


        List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                            try {
                                return future.get();
                            } catch (InterruptedException | ExecutionException | StackException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                )
                .filter(p -> p != null)
                .collect(Collectors.toList());

        list.addAll(newProgramList);
        list.forEach(state -> repository.logProgramState(state));

        repository.setProgramList(list);
    }

    public String toString() {
        return state.getOriginalProgram().toString();
    }

    public ProgramState getProgramState() {
        return state;
    }

    public Repository getRepository() {
        return (Repository) repository;
    }
}
