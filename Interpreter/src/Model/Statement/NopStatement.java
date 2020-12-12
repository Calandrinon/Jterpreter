package Model.Statement;
import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Type.Type;

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

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        return typeEnvironment;
    }

    public String toString() {
        return "NOP";
    }
}
