package Model.Statement;
import Exceptions.GeneralException;
import Model.ProgramState;

import java.io.FileNotFoundException;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws GeneralException, FileNotFoundException;
    StatementInterface clone();
    String toString();
}
