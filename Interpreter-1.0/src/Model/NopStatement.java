package Model;
import Exceptions.GeneralException;

public class NopStatement implements StatementInterface {
    private ProgramState state;

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        return null;
    }

    @Override
    public StatementInterface clone() {
        return null;
    }

    public String toString() {
        return "NOP";
    }
}
