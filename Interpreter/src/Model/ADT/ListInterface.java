package Model.ADT;

import Exceptions.ListException;

import java.util.List;

public interface ListInterface<T> {
    void add(T element);
    void addAll(List<T> list);
    void clear();
    void remove(int index);
    T get(int index) throws ListException;
    void set(int index, T element);
    String toString();
}
