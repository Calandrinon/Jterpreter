package Model.ADT;

import Exceptions.ListException;

import java.util.List;
import java.util.stream.Stream;

public interface ListInterface<T> {
    void add(T element);
    void addAll(List<T> list);
    void clear();
    void remove(int index);
    void removeElement(Object object);
    T get(int index) throws ListException;
    void set(int index, T element);
    int size();
    boolean contains(T element);
    String toString();
}
