package Model.Statement;

import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ADT.TheDictionary;
import Model.ADT.TheStack;
import Model.ProgramState;
import Model.Value.Value;

import java.io.IOException;

public class ForkStatement implements StatementInterface {
    private StatementInterface statement;

    public ForkStatement(StatementInterface statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        TheStack<StatementInterface> clonedStateStack = new TheStack<>();
        clonedStateStack.push(statement);
        DictionaryInterface<String, Value> clonedSymbolTable = state.getSymbolTable().clone();

        ProgramState clonedState = new ProgramState(clonedStateStack, clonedSymbolTable, state.getFileTable(),
                state.getHeap(), state.getOutput(), state.getOriginalProgram());
        return clonedState;
    }

    @Override
    public StatementInterface clone() {
        return new ForkStatement(statement);
    }
}
