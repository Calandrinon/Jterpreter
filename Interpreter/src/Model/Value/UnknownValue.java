package Model.Value;

import Model.Type.Type;
import Model.Value.Value;

public class UnknownValue implements Value {
    private final Type type;

    public UnknownValue(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    public String toString() {
        return "Unknown @" + type.toString();
    }
}
