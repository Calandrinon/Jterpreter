package Model.ADT;

import Exceptions.DictionaryException;
import Exceptions.HeapException;
import Model.Value.Value;

public interface HeapInterface {
    void put(Value value);
    Value lookup(int key) throws HeapException;
    void remove(int key);
    boolean isDefined(int key);
    String toString();
}
