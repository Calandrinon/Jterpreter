package Model;
import Exceptions.GeneralException;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws GeneralException;
    StatementInterface clone();
    String toString();
}
