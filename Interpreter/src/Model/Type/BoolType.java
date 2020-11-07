package Model.Type;

import Model.Value.UnknownValue;
import Model.Value.Value;

public class BoolType implements Type {
    @Override
    public boolean equals(Object other) {
        return other instanceof BoolType;
    }

    @Override
    public Value defaultValue() {
        return new UnknownValue(new BoolType());
    }

    public String toString() {
        return "bool";
    }
}
