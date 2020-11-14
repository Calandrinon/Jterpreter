package Model.Type;

import Model.Value.RefValue;
import Model.Value.Value;

public class RefType implements Type {
    Type referredType;

    public RefType(Type referredType) {
        this.referredType = referredType;
    }

    Type getReferredType() {
        return referredType;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0, referredType);
    }

    public boolean equals(Object other) {
        return other instanceof RefType && referredType.equals(((RefType) other).getReferredType());
    }

    public String toString() {
        return "Ref(" + referredType.toString() + ")";
    }
}
