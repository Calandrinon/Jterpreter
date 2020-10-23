package Repository;
import Exceptions.ListException;
import Model.ProgramState;

public interface RepositoryInterface {
    void add(ProgramState state);
    ProgramState getCurrentProgramState() throws ListException;
}
