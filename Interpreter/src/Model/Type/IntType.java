package Model.Type;

public class IntType implements Type {
    @Override
    public boolean equals(Object other) {
        return other instanceof IntType;
    }

    public String toString() {
        return "int";
    }
}
