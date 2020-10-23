package Model;

public interface ListInterface<T> {
    void add(T element);
    void clear();
    void remove(int index);
    T get(int index);
    void set(int index, T element);
    String toString();
}
