package Repository;

import Model.ProgramState;
import Model.TheList;

public class Repository implements RepositoryInterface {
    private TheList<ProgramState> container;

    public Repository() {
        container = new TheList<ProgramState>();
    }

    @Override
    public void add(ProgramState state) {
        container.add(state);
    }

    @Override
    public ProgramState getCurrentProgramState() {
        ProgramState state = container.get(0);
        container.remove(0);
        return state;
    }
}
