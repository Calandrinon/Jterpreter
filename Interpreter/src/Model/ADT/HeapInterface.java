package Model.ADT;

import Exceptions.DictionaryException;
import Exceptions.HeapException;
import Model.Value.Value;

import java.util.HashMap;
import java.util.Map;

public interface HeapInterface {
    void put(Value value);
    Value lookup(int key) throws HeapException;
    void remove(int key);
    boolean isDefined(int key);
    void setContent(Map<Integer, Value> map);
    public int getCurrentMaximumKey();
    String toString();
    Map<Integer, Value> getContent();

}
