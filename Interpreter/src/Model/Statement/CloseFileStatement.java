package Model.Statement;

import Exceptions.FileException;
import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Expression.GeneralExpression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements StatementInterface {
    private GeneralExpression expression;

    public CloseFileStatement(GeneralExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        DictionaryInterface<String, BufferedReader> fileTable = state.getFileTable();
        Value value = expression.evaluate(symbolTable, heap);

        if (!value.getType().equals(new StringType()))
            throw new FileException("The expression representing the name of the file to be closed must be a string.");

        StringValue fileName = (StringValue) value;
        if (!fileTable.isDefined(fileName.getValue()))
            throw new FileException("The file " + fileName.getValue() + " might not exist. A non-existent file cannot be closed.");

        BufferedReader reader = fileTable.lookup(fileName.getValue());
        reader.close();
        fileTable.remove(fileName.getValue());

        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public CloseFileStatement clone() {
        return new CloseFileStatement(this.expression);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type expressionType = expression.typecheck(typeEnvironment);

        if (!expressionType.equals(new StringType()))
            throw new FileException("The parameter of the file-closing statement should be a string.");

        return typeEnvironment;
    }

    public String toString() {
        return "CloseFile(" + this.expression.toString() + ")";
    }
}
