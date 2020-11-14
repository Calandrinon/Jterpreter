package Tests;

import Model.ADT.*;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.AssignmentStatement;
import Model.Statement.HeapAllocationStatement;
import Model.Statement.StatementInterface;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IntValue;
import Model.Value.RefValue;
import Model.Value.UnknownValue;
import Model.Value.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

public class HeapAllocationTest  {
    @Test
    public void testExecution() throws IOException {
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        symbolTable.put("v", new UnknownValue(new RefType(new IntType())));
        VariableExpression variableExpression = new VariableExpression("v");
        ValueExpression generalExpression = new ValueExpression(new IntValue(20));
        HeapAllocationStatement statement = new HeapAllocationStatement(variableExpression, generalExpression);

        symbolTable.put("a", new UnknownValue(new RefType(new RefType(new IntType()))));
        VariableExpression variableExpression2 = new VariableExpression("a");
        ValueExpression generalExpression2 = new ValueExpression(new RefValue(heap.getCurrentMaximumKey(), new IntType()));
        HeapAllocationStatement statement2 = new HeapAllocationStatement(variableExpression2, variableExpression);

        ProgramState state = new ProgramState(executionStack, symbolTable, fileTable, heap, output, statement);
        statement.execute(state);
        statement2.execute(state);

        System.out.println("Symbol table:\n" + symbolTable.toString());
        System.out.println("Heap:\n" + heap.toString());
    }
}
