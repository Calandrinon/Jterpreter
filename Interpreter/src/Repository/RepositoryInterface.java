package Repository;
import Exceptions.GeneralException;
import Exceptions.ListException;
import Model.ProgramState;

import java.io.IOException;

public interface RepositoryInterface {
    void add(ProgramState state);
    ProgramState getCurrentProgramState() throws ListException;
    void logProgramState() throws GeneralException, IOException;
    int getSize();
    void clear();
    void pushFront(ProgramState state);
}
