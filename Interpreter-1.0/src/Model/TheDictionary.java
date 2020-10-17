package Model;

import java.util.Hashtable;

public class TheDictionary<K, V> implements DictionaryInterface<K, V> {
    Hashtable<K, V> table;

    public TheDictionary() {
        table = new Hashtable<K, V>();
    }

    @Override
    public void put(K key, V value) {
        table.put(key, value);
    }

    @Override
    public V lookup(K key) {
        return (V)table.get(key);
    }

    @Override
    public boolean isDefined(String id) {
        return table.containsKey(id);
    }
}
