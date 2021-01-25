package Model.ADT;

import Model.ProgramState;
import javafx.util.Pair;

public class Semaphore implements SemaphoreInterface {
    DictionaryInterface<Integer, Pair<Integer, ListInterface<Integer>>> semaphore;

    public Semaphore(){
        semaphore = new TheDictionary<>();
    }

    @Override
    public void setSemaphoreTable(DictionaryInterface<Integer, Pair<Integer, ListInterface<Integer>>> semaphoreTable) {
        this.semaphore = semaphore;
    }

    @Override
    public DictionaryInterface<Integer, Pair<Integer, ListInterface<Integer>>> getSemaphore() {
        return semaphore;
    }

    @Override
    public Integer getSemaphoreAddress(ProgramState state){
        return state.getSemaphoreAddress();
    }

    @Override
    public void put(Integer index, Pair<Integer, ListInterface<Integer>> integerListPair) {
        semaphore.put(index, integerListPair);
    }
}
