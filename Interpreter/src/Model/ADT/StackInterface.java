package Model.ADT;

import Exceptions.StackException;

import java.util.Stack;

public interface StackInterface<T> {
    void push(T element);
    T pop() throws StackException;
    boolean isEmpty();
    String toString();
}
