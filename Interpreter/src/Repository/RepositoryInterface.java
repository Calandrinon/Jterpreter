package Repository;
import Exceptions.GeneralException;
import Exceptions.ListException;
import Model.ADT.TheList;
import Model.ProgramState;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface {
    void add(ProgramState state);
    void logProgramState(ProgramState state);
    int getSize();
    void clear();
    TheList<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> newList);
}
