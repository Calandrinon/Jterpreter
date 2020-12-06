package Model.Statement;

import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.UnknownValue;
import Model.Value.Value;

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
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        typeEnvironment.put(name, type);
        return typeEnvironment;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        DictionaryInterface<String, Value> table = state.getSymbolTable();
        table.put(name, this.type.defaultValue());
        state.setSymbolTable(table);
        return null;
    }

    public String toString() {
        return "varDec(" + name + "=" + type.toString() + ")";
    }
}
