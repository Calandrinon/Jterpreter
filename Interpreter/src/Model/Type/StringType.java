package Model.Type;

import Model.Value.UnknownValue;
import Model.Value.Value;

public class StringType implements Type {
    @Override
    public boolean equals(Object other) {
        return other instanceof StringType;
    }

    @Override
    public Value defaultValue() {
        return new UnknownValue(new StringType());
    }

    public String toString() {
        return "string";
    }
}
