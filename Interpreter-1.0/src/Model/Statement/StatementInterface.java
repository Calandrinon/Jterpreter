package Model.Statement;
import Exceptions.GeneralException;
import Model.ProgramState;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws GeneralException;
    StatementInterface clone();
    String toString();
}
