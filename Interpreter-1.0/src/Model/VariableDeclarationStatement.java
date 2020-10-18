package Model;

import Exceptions.GeneralException;

public class VariableDeclarationStatement implements StatementInterface {
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        return null;
    }
}
