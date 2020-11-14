package Model.ADT;
import Exceptions.HeapException;
import Model.Value.Value;
import java.util.HashMap;
import java.util.Set;

public class Heap implements HeapInterface {
    private HashMap<Integer, Value> table;
    private int currentMaximumKey;

    public Heap() {
        this.table = new HashMap<>();
        this.currentMaximumKey = 0;
    }

    @Override
    public void put(Value value) {
        this.currentMaximumKey++;
        table.put(currentMaximumKey, value);
    }

    @Override
    public Value lookup(int key) throws HeapException {
        Value value = table.get(key);
        if (value == null)
            throw new HeapException("The specified address does not have any content.");
        return value;
    }

    @Override
    public void remove(int key) {
        table.remove(key);
    }

    @Override
    public boolean isDefined(int id) {
        return table.containsKey(id);
    }

    public int getCurrentMaximumKey() {
        return this.currentMaximumKey;
    }

    public String toString() {
        StringBuilder text = new StringBuilder();
        Set<Integer> keys = table.keySet();

        for (int key : keys) {
            text.append(key).append(" --> ").append(table.get(key));
            text.append("\n");
        }

        return text.toString();
    }
}
