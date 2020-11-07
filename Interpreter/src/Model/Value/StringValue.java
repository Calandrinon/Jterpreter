package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    private String content;

    public StringValue(String content) {
        this.content = content;
    }

    public String getValue() {
        return content;
    }

    public String toString() {
        return this.getValue();
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof StringValue && this.content.equals(((StringValue) other).getValue());
    }
}
