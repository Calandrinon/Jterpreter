package Model;
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
    public T pop() {
        return stack.pop();
    }
}
