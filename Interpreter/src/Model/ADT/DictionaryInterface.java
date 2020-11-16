package Model.ADT;

import Exceptions.DictionaryException;

import java.util.HashMap;

public interface DictionaryInterface<K, V> {
    void put(K key, V value);
    V lookup(K key) throws DictionaryException;
    void remove(K key);
    HashMap<K, V> getContent();
    boolean isDefined(String id);
    String toString();
}
