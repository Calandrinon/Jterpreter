package Model.Statement;
import Exceptions.GeneralException;
import Model.ProgramState;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws GeneralException, IOException;
    StatementInterface clone();
    String toString();
}
