package Model.Statement;

import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ADT.TheDictionary;
import Model.ADT.TheStack;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

import java.io.IOException;

public class ForkStatement implements StatementInterface {
    private StatementInterface statement;

    public ForkStatement(StatementInterface statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        TheStack<StatementInterface> newStack = new TheStack<>();
        DictionaryInterface<String, Value> clonedSymbolTable = state.getSymbolTable().clone();

        return new ProgramState(newStack, clonedSymbolTable, state.getFileTable(),
                state.getHeap(), state.getOutput(), statement);
    }

    @Override
    public StatementInterface clone() {
        return new ForkStatement(statement);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        return statement.typecheck(typeEnvironment);
    }

    public String toString() {
        return "fork(" + statement.toString() + ")";
    }
}
