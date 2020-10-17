package Model;

public interface DictionaryInterface<K, V> {
    void put(K key, V value);
    V lookup(K key);
    boolean isDefined(String id);
    String toString();
}
