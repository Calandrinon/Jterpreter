package Model.Statement;

import Exceptions.FileException;
import Exceptions.GeneralException;
import Exceptions.UndefinedSymbolException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.Expression.GeneralExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFileStatement implements StatementInterface {
    private GeneralExpression expression, resultContainerVariable;

    public ReadFileStatement(GeneralExpression expression, GeneralExpression resultContainerVariable) {
        this.expression = expression;
        this.resultContainerVariable = resultContainerVariable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        DictionaryInterface<String, BufferedReader> fileTable = state.getFileTable();
        Value value = expression.evaluate(symbolTable, heap);
        Value variable = resultContainerVariable.evaluate(symbolTable, heap);
        String variableName = ((VariableExpression)resultContainerVariable).getId();

        if (!(symbolTable.isDefined(variableName) && symbolTable.lookup(variableName).getType().equals(new IntType())))
            throw new UndefinedSymbolException("The given variable \"" + variableName + "\" hasn't been defined.");

        if (!value.getType().equals(new StringType()))
            throw new FileException("The expression representing the name of the file to be read must be a string.");

        StringValue fileName = (StringValue) value;
        if (!fileTable.isDefined(fileName.getValue()))
            throw new FileException("The file " + fileName.getValue() + " might not exist. It should be created and then opened with an OpenFileStatement.");

        BufferedReader reader = fileTable.lookup(fileName.getValue());
        String line = reader.readLine();
        IntValue intValue = null;
        if (line.equals(""))
            intValue = new IntValue(0);
        else
            intValue = new IntValue(Integer.parseInt(line));

        symbolTable.put(variableName, intValue);
        state.setSymbolTable(symbolTable);
        return null;
    }

    @Override
    public ReadFileStatement clone() {
        return new ReadFileStatement(this.expression, this.resultContainerVariable);
    }

    public String toString() {
        return "ReadFile(" + this.expression.toString()  + ", " + this.resultContainerVariable.toString() + ")";
    }
}