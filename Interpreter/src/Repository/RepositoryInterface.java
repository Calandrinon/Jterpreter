package Repository;
import Exceptions.GeneralException;
import Exceptions.ListException;
import Model.ADT.TheList;
import Model.ProgramState;

import java.io.IOException;

public interface RepositoryInterface {
    void add(ProgramState state);
    void logProgramState(ProgramState state) throws GeneralException, IOException;
    int getSize();
    void clear();
    TheList<ProgramState> getProgramList();
    void setProgramList(TheList<ProgramState> newList);
}
