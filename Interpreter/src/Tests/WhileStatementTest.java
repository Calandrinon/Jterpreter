package Tests;

import Controller.Controller;
import Model.ADT.*;
import Model.Expression.ArithmeticExpression;
import Model.Expression.LogicExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IntValue;
import Model.Value.Value;
import Repository.Repository;
import Repository.RepositoryInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

public class WhileStatementTest {
    @Test
    void testWhileStatement() throws IOException {
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        CompoundStatement statement = new CompoundStatement(
        new VariableDeclarationStatement("v", new IntType()),
        new CompoundStatement(
        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
        new CompoundStatement(
        new WhileStatement(new LogicExpression(new VariableExpression("v"), ">", new ValueExpression(new IntValue(0))),
        new CompoundStatement(
        new PrintStatement(new VariableExpression("v")),
        new AssignmentStatement("v", new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
        new PrintStatement(new VariableExpression("v")))));

        ProgramState state = new ProgramState(executionStack, symbolTable, fileTable, heap, output, statement);
        RepositoryInterface repository = new Repository("whilestatementest.txt");
        Controller controller = new Controller(repository, state);
        controller.completeExecution();
        Assertions.assertEquals(output.toString(), "4\n3\n2\n1\n0\n");
    }
}
