package Repository;
import Model.ProgramState;

public interface RepositoryInterface {
    void add(ProgramState state);
    ProgramState getCurrentProgramState();
}
