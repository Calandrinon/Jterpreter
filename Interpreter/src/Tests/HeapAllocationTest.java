package Tests;

import Controller.Controller;
import Model.ADT.*;
import Model.Expression.ArithmeticExpression;
import Model.Expression.HeapReadExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.*;
import Repository.Repository;
import Repository.RepositoryInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.management.ValueExp;
import java.io.BufferedReader;
import java.io.IOException;

public class HeapAllocationTest  {
    @Test
    public void testSymbolTableAndHeapStructureAfterExecution() throws IOException, InterruptedException {
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        CompoundStatement statement = new CompoundStatement(
        new VariableDeclarationStatement("v", new RefType(new IntType())),
        new CompoundStatement(
        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new CompoundStatement(
        new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
        new CompoundStatement(
        new HeapAllocationStatement("a", new VariableExpression("v")),
        new CompoundStatement(
        new PrintStatement(new VariableExpression("v")),
        new PrintStatement(new VariableExpression("a"))
        )))));

        ProgramState state = new ProgramState(executionStack, symbolTable, fileTable, heap, output, statement);
        RepositoryInterface repository = new Repository("heaptest1.txt");
        Controller controller = new Controller(repository, state);
        controller.completeExecution();

        Assertions.assertEquals(heap.lookup(1), new IntValue(20));
    }

    @Test
    public void testHeapRead() throws IOException, InterruptedException {
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        CompoundStatement statement = new CompoundStatement(
        new VariableDeclarationStatement("v", new RefType(new IntType())),
        new CompoundStatement(
        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new PrintStatement(new HeapReadExpression(new VariableExpression("v")))
        ));

        ProgramState state = new ProgramState(executionStack, symbolTable, fileTable, heap, output, statement);
        RepositoryInterface repository = new Repository("heaptest2.txt");
        Controller controller = new Controller(repository, state);
        controller.completeExecution();

        Assertions.assertEquals(output.toString(), "20\n");
    }

    @Test
    public void testHeapWrite() throws IOException, InterruptedException {
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        CompoundStatement statement = new CompoundStatement(
        new VariableDeclarationStatement("v", new RefType(new IntType())),
        new CompoundStatement(
        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new CompoundStatement(
        new PrintStatement(new HeapReadExpression(new VariableExpression("v"))),
        new CompoundStatement(
        new HeapWriteStatement("v", new ValueExpression(new IntValue(30))),
        new PrintStatement(new ArithmeticExpression("+", new HeapReadExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))
        ))));

        ProgramState state = new ProgramState(executionStack, symbolTable, fileTable, heap, output, statement);
        RepositoryInterface repository = new Repository("heaptest3.txt");
        Controller controller = new Controller(repository, state);
        controller.completeExecution();

        Assertions.assertEquals(output.toString(), "20\n35\n");
    }

    @Test
    public void testGarbageCollection() throws IOException, InterruptedException {
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        CompoundStatement statement = new CompoundStatement(
        new VariableDeclarationStatement("v", new RefType(new IntType())),
        new CompoundStatement(
        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new CompoundStatement(
        new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
        new CompoundStatement(
        new HeapAllocationStatement("a", new VariableExpression("v")),
        new CompoundStatement(
        new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
        new PrintStatement(new HeapReadExpression(new HeapReadExpression(new VariableExpression("a"))))
        )))));

        ProgramState state = new ProgramState(executionStack, symbolTable, fileTable, heap, output, statement);
        RepositoryInterface repository = new Repository("heaptest4.txt");
        Controller controller = new Controller(repository, state);
        controller.completeExecution();

        Assertions.assertEquals(output.toString(), "30\n");
    }
}
