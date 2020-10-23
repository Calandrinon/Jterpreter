package Model;

import Exceptions.GeneralException;

public class VariableDeclarationStatement implements StatementInterface {
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public VariableDeclarationStatement clone() {
        return new VariableDeclarationStatement(this.name, this.type);
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        DictionaryInterface<String, Value> table = state.getSymbolTable();
        table.put(name, new UnknownValue(this.type));
        state.setSymbolTable(table);
        return state;
    }

    public String toString() {
        return "varDec(" + name + "=" + type.toString() + ")";
    }
}
