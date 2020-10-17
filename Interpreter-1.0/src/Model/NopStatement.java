package Model;
import Exceptions.GeneralException;

public class NopStatement implements StatementInterface {
    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        return null;
    }
}
