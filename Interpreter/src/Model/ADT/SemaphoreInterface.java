package Model.ADT;

import Model.ProgramState;
import javafx.util.Pair;

public interface SemaphoreInterface {
    void setSemaphoreTable(DictionaryInterface<Integer, Pair<Integer, ListInterface<Integer>>> semaphoreTable);
    DictionaryInterface<Integer, Pair<Integer, ListInterface<Integer>>> getSemaphore();
    Integer getSemaphoreAddress(ProgramState state);
    void put(Integer index, Pair<Integer, ListInterface<Integer>> integerListPair);
}
