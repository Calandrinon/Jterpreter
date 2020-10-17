package Model;
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
    public void set(int index, T element) {
        list.set(index, element);
    }
}
