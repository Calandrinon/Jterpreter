package Model;

import Exceptions.DictionaryException;

public interface DictionaryInterface<K, V> {
    void put(K key, V value);
    V lookup(K key) throws DictionaryException;
    boolean isDefined(String id);
    String toString();
}
