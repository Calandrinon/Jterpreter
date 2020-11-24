package Model.Statement;
import Exceptions.GeneralException;
import Model.ProgramState;

public class NopStatement implements StatementInterface {
    private ProgramState state;

    public NopStatement(ProgramState state) {
        this.state = state;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        return null;
    }

    @Override
    public StatementInterface clone() {
        return new NopStatement(state);
    }

    public String toString() {
        return "NOP";
    }
}
