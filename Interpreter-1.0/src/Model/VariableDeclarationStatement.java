package Model;

import Exceptions.GeneralException;

public class VariableDeclarationStatement implements StatementInterface {
    String name;
    Type type;

    public VariableDeclarationStatement() {}

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        return null;
    }
}
