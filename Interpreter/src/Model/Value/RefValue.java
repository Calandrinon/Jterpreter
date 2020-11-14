package Model.Value;
import Model.Type.BoolType;
import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value {
    Type locationType;
    int address;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String toString() {
        return "(" + String.valueOf(address) + ", " + locationType.toString()  + ")";
    }
}
