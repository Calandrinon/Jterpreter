package Model;

public interface ListInterface<T> {
    void add(T element);
    void clear();
    void remove(int index);
    void set(int index, T element);
}
