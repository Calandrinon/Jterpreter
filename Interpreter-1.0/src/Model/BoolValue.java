package Model;

public class BoolValue implements Value {
    boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    public String toString() {
        return String.valueOf(value);
    }
}
