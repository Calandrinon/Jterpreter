package Model;

import java.util.Stack;

public interface StackInterface<T> {
    void push(T element);
    T pop();
    boolean isEmpty();
    String toString();
}
