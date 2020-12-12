package Model.Statement;

import Exceptions.DictionaryException;
import Exceptions.FileException;
import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.TheDictionary;
import Model.Expression.GeneralExpression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStatement implements StatementInterface {
    private GeneralExpression expression;

    public OpenFileStatement(GeneralExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, FileNotFoundException {
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        DictionaryInterface<String, BufferedReader> fileTable = state.getFileTable();
        Value value = this.expression.evaluate(symbolTable, heap);

        if (!value.getType().equals(new StringType()))
            throw new FileException("The expression in the file opening statement should be a string.");

        StringValue result = (StringValue)value;

        if (fileTable.isDefined(result.getValue()))
            throw new FileException("The file already exists in the file table.");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(result.getValue()));
            fileTable.put(result.getValue(), reader);
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }

        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public OpenFileStatement clone() {
        return new OpenFileStatement(this.expression);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type expressionType = expression.typecheck(typeEnvironment);
        if (!expressionType.equals(new StringType()))
            throw new FileException("The argument of a file-opening statement should be a string.");
        return typeEnvironment;
    }

    public String toString() {
        return "OpenFile(" + this.expression.toString() + ")";
    }
}
