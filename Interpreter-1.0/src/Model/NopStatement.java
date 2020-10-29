package Model;
import Exceptions.GeneralException;

import javax.swing.plaf.nimbus.State;

public class NopStatement implements StatementInterface {
    private ProgramState state;

    public NopStatement(ProgramState state) {
        this.state = state;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        return state;
    }

    @Override
    public StatementInterface clone() {
        return new NopStatement(state);
    }

    public String toString() {
        return "NOP";
    }
}
