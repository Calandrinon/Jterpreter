package Model.ADT;

import Exceptions.DictionaryException;
import Model.ADT.DictionaryInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TheDictionary<K, V> implements DictionaryInterface<K, V> {
    HashMap<K, V> table;

    public TheDictionary() {
        table = new HashMap<K, V>();
    }

    @Override
    public void put(K key, V value) {
        table.put(key, value);
    }

    @Override
    public synchronized V lookup(K key) throws DictionaryException {
        V value = (V)table.get(key);
        if (value == null)
            throw new DictionaryException("The key does not exist in the table.");
        return value;
    }

    @Override
    public void remove(K key) {
        table.remove(key);
    }

    @Override
    public HashMap<K, V> getContent() {
        return table;
    }

    public synchronized void setContent(HashMap<K, V> newTable) {
        table = newTable;
    }

    @Override
    public boolean isDefined(String id) {
        return table.containsKey(id);
    }

    public TheDictionary<K, V> clone() {
        TheDictionary<K, V> clonedDictionary = new TheDictionary<>();
        clonedDictionary.setContent((HashMap<K, V>) table.clone());
        return clonedDictionary;
    }

    public String toString() {
        StringBuilder text = new StringBuilder();
        Set<String> keys = (Set<String>) table.keySet();

        for (String str : keys) {
            text.append(str).append(" --> ").append(table.get(str));
            text.append("\n");
        }

        return text.toString();
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return table.entrySet();
    }

}
