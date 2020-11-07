package Model.Type;

import Model.Value.UnknownValue;
import Model.Value.Value;

public class IntType implements Type {
    @Override
    public boolean equals(Object other) {
        return other instanceof IntType;
    }

    @Override
    public Value defaultValue() {
        return new UnknownValue(new IntType());
    }

    public String toString() {
        return "int";
    }
}
