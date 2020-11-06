package Model.ADT;

import Exceptions.DictionaryException;
import Model.ADT.DictionaryInterface;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

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
    public V lookup(K key) throws DictionaryException {
        V value = (V)table.get(key);
        if (value == null)
            throw new DictionaryException("The key does not exist in the table.");
        return value;
    }

    @Override
    public boolean isDefined(String id) {
        return table.containsKey(id);
    }

    public String toString() {
        StringBuilder text = new StringBuilder();

        Set<String> keys = (Set<String>) table.keySet();
        Iterator<String> itr = keys.iterator();

        int nameIndex = 0;
        while (itr.hasNext()) {
            if (nameIndex > 0) {
                text.append(", ");
            }
            String str = itr.next();
            text.append(str).append("=").append(table.get(str));
            nameIndex++;
        }

        return text.toString();
    }
}
