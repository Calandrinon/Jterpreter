package Model.Statement;
import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Type.Type;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws GeneralException, IOException;
    StatementInterface clone();
    DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException;
    String toString();
}
