package Model.ADT;
import Exceptions.ListException;
import Model.ADT.ListInterface;

import java.util.ArrayList;
import java.util.List;

public class TheList<T> implements ListInterface<T> {
    private List<T> list;

    public TheList() {
        list = new ArrayList<T>();
    }

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public T get(int index) throws ListException {
        if (index >= list.size())
            throw new ListException("Index is greater than or equal to the size of the list.");
        return list.get(index);
    }

    @Override
    public void set(int index, T element) {
        list.set(index, element);
    }

    public String toString() {
        String text = "";

        for (int i = 0; i < list.size(); i++) {
            if (i > 0)
                text += ", ";
            text += list.get(i);
        }

        return text;
    }
}
