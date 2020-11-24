package Model.ADT;
import Exceptions.ListException;
import Model.ADT.ListInterface;
import Model.ProgramState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    public void addAll(List<T> list) {
        this.list.addAll(list);
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

    public void setContainer(List<T> newContainer) {
        this.list = newContainer;
    }

    public List<T> getContainer() {
        return list;
    }

    public String toString() {
        String text = "";

        for (int i = 0; i < list.size(); i++) {
            text += list.get(i);
            text += "\n";
        }

        return text;
    }

    public Stream<T> stream() {
        return list.stream();
    }

    public int size() {
        return list.size();
    }

    public void pushFront(T state) {
        list.add(0, state);
    }
}
