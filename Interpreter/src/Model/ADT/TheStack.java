package Model.ADT;
import Exceptions.StackException;
import Model.ADT.StackInterface;

import java.util.Stack;

public class TheStack<T> implements StackInterface<T> {
    private Stack<T> stack;

    public TheStack() {
        stack = new Stack<T>();
    }

    @Override
    public void push(T element) {
        stack.push(element);
    }

    @Override
    public T pop() throws StackException {
        if (stack.size() == 0)
            throw new StackException("Can't extract elements from an empty stack.");
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public String toString() {
        String text = "";
        Stack<T> stackClone = (Stack<T>)stack.clone();

        while (!stackClone.empty()) {
            text += stackClone.pop().toString();
            text += "\n";
        }

        return text;
    }
}
