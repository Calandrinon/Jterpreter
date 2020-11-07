package Repository;

import Exceptions.GeneralException;
import Exceptions.ListException;
import Model.ProgramState;
import Model.ADT.TheList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements RepositoryInterface {
    private TheList<ProgramState> container;
    private String logFilePath;

    public Repository(String logFilePath) {
        container = new TheList<ProgramState>();
        this.logFilePath = logFilePath;
    }

    @Override
    public void add(ProgramState state) {
        container.add(state);
    }
    public void addNewFile(String logFilePath) {
        this.logFilePath = logFilePath;
    }
    public void pushFront(ProgramState state) {container.pushFront(state);}
    public void clear() {
        container.clear();
    }

    @Override
    public ProgramState getCurrentProgramState() throws ListException {
        ProgramState state = container.get(0);
        container.remove(0);
        return state;
    }

    @Override
    public void logProgramState() throws GeneralException, IOException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(container.get(0).toString());
            logFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getSize() {
        return container.size();
    }
}
