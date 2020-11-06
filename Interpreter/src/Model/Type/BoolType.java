package Model.Type;

public class BoolType implements Type {
    @Override
    public boolean equals(Object other) {
        return other instanceof BoolType;
    }

    public String toString() {
        return "bool";
    }
}
