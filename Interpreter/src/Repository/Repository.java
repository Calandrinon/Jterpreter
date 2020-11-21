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

    public void clear() {
        container.clear();
    }


    @Override
    public TheList<ProgramState> getProgramList() {
        return container;
    }

    @Override
    public void setProgramList(TheList<ProgramState> newList) {
        container = newList;
    }

    @Override
    public void logProgramState(ProgramState state) throws GeneralException, IOException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println("ProgramState ID: " + state.getId());
            logFile.println(state.toString());
            logFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getSize() {
        return container.size();
    }
}
