import Controller.Controller;
import Exceptions.GeneralException;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.Repository;
import Repository.RepositoryInterface;
import View.TextMenu;
import View.ExitCommand;
import View.RunExample;

import java.io.*;

public class Main {
    public static void main(String[] args) throws GeneralException, IOException {
        ///--------------------------------------------------------------------------------------------
        StatementInterface example1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v")))
        );
        example1.typecheck(new TheDictionary<>());

        ProgramState program1= ProgramState.createNewProgramState(example1);
        RepositoryInterface repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1, program1);
        ///--------------------------------------------------------------------------------------------
        StatementInterface example2 = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression("*", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(
                                        new AssignmentStatement("b",
                                                new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );
        example2.typecheck(new TheDictionary<>());

        ProgramState program2= ProgramState.createNewProgramState(example2);
        RepositoryInterface repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2, program2);
        ///--------------------------------------------------------------------------------------------
        StatementInterface example3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                VariableExpression("v"))))));

        example3.typecheck(new TheDictionary<>());

        ProgramState program3= ProgramState.createNewProgramState(example3);
        RepositoryInterface repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3, program3);
        ///--------------------------------------------------------------------------------------------
        StatementInterface example4 = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("c", new BoolType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a", new ValueExpression(new IntValue(2))),
                                        new CompoundStatement(
                                                new AssignmentStatement("b", new ValueExpression(new IntValue(3))),
                                                new CompoundStatement(
                                                        new AssignmentStatement("c", new ValueExpression(new BoolValue(true))),
                                                        new IfStatement(
                                                                new LogicExpression(new RelationalExpression(new ArithmeticExpression("+", new VariableExpression("a"), new VariableExpression("b")), "==", new ValueExpression(new IntValue(5))), "&&", new VariableExpression("c")),
                                                                new PrintStatement(new ValueExpression(new IntValue(1))),
                                                                new PrintStatement(new ValueExpression(new IntValue(0)))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        example4.typecheck(new TheDictionary<>());

        ProgramState program4= ProgramState.createNewProgramState(example4);
        RepositoryInterface repository4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repository4, program4);
        ///--------------------------------------------------------------------------------------------
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("test.in", true)));
        writer.println("15");
        writer.println("50");
        writer.close();

        StatementInterface example5 = new CompoundStatement(
                new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseFileStatement(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        example5.typecheck(new TheDictionary<>());

        ProgramState program5= ProgramState.createNewProgramState(example5);
        RepositoryInterface repository5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repository5, program5);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack6 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable6 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable6 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output6 = new TheList<Value>();
        HeapInterface heap6 = new Heap();

        CompoundStatement statement6 = new CompoundStatement(
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

        statement6.typecheck(new TheDictionary<>());

        ProgramState state6 = new ProgramState(executionStack6, symbolTable6, fileTable6, heap6, output6, statement6);
        RepositoryInterface repository6 = new Repository("log6.txt");
        Controller controller6 = new Controller(repository6, state6);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack7 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable7 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable7 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output7 = new TheList<Value>();
        HeapInterface heap7 = new Heap();

        CompoundStatement statement7 = new CompoundStatement(
        new VariableDeclarationStatement("v", new RefType(new IntType())),
        new CompoundStatement(
        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new CompoundStatement(
        new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
        new CompoundStatement(
        new HeapAllocationStatement("a", new VariableExpression("v")),
        new CompoundStatement(
        new PrintStatement(new HeapReadExpression(new VariableExpression("v"))),
        new PrintStatement(new ArithmeticExpression("+", new HeapReadExpression(new HeapReadExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));

        statement7.typecheck(new TheDictionary<>());

        ProgramState state7 = new ProgramState(executionStack7, symbolTable7, fileTable7, heap7, output7, statement7);
        RepositoryInterface repository7 = new Repository("log7.txt");
        Controller controller7 = new Controller(repository7, state7);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack8 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable8 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable8 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output8 = new TheList<Value>();
        HeapInterface heap8 = new Heap();

        CompoundStatement statement8 = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new HeapReadExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new HeapWriteStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+", new HeapReadExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))
                                ))));
        statement8.typecheck(new TheDictionary<>());

        ProgramState state8 = new ProgramState(executionStack8, symbolTable8, fileTable8, heap8, output8, statement8);
        RepositoryInterface repository8 = new Repository("log8.txt");
        Controller controller8 = new Controller(repository8, state8);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack9 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable9 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable9 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output9 = new TheList<Value>();
        HeapInterface heap9 = new Heap();

        CompoundStatement statement9 = new CompoundStatement(
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

        statement9.typecheck(new TheDictionary<>());

        ProgramState state9 = new ProgramState(executionStack9, symbolTable9, fileTable9, heap9, output9, statement9);
        RepositoryInterface repository9 = new Repository("log9.txt");
        Controller controller9 = new Controller(repository9, state9);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack10 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable10 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable10 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output10 = new TheList<Value>();
        HeapInterface heap10 = new Heap();

        CompoundStatement statement10 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(new RelationalExpression(new VariableExpression("v"), ">", new ValueExpression(new IntValue(0))),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement("v", new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        statement10.typecheck(new TheDictionary<>());

        ProgramState state10 = new ProgramState(executionStack10, symbolTable10, fileTable10, heap10, output10, statement10);
        RepositoryInterface repository10 = new Repository("log10.txt");
        Controller controller10 = new Controller(repository10, state10);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack11 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable11 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable11 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output11 = new TheList<Value>();
        HeapInterface heap11 = new Heap();

        CompoundStatement statement11 = new CompoundStatement(
        new VariableDeclarationStatement("v", new IntType()),
        new CompoundStatement(
        new VariableDeclarationStatement("a", new RefType(new IntType())),
        new CompoundStatement(
        new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
        new CompoundStatement(
        new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
        new CompoundStatement(
        new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
        new CompoundStatement(
        new ForkStatement(new CompoundStatement(
        new HeapWriteStatement("a", new ValueExpression(new IntValue(30))),
        new CompoundStatement(
        new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
        new CompoundStatement(
        new PrintStatement(new VariableExpression("v")),
        new PrintStatement(new HeapReadExpression(new VariableExpression("a")))
        )
        )
        )),
        new CompoundStatement(
        new PrintStatement(new VariableExpression("v")),
        new PrintStatement(new HeapReadExpression(new VariableExpression("a")))
        )
        )
        )
        ))));

        statement11.typecheck(new TheDictionary<>());

        ProgramState state11 = new ProgramState(executionStack11, symbolTable11, fileTable11, heap11, output11, statement11);
        RepositoryInterface repository11 = new Repository("log11.txt");
        Controller controller11 = new Controller(repository11, state11);
        ///-------------------------------------------------------------------------------------------------------------

        TextMenu textMenu = new TextMenu();
        textMenu.addCommand(new ExitCommand("0", "exit"));
        textMenu.addCommand(new RunExample("1", example1.toString(), controller1));
        textMenu.addCommand(new RunExample("2", example2.toString(), controller2));
        textMenu.addCommand(new RunExample("3", example3.toString(), controller3));
        textMenu.addCommand(new RunExample("4", example4.toString(), controller4));
        textMenu.addCommand(new RunExample("5", example5.toString(), controller5));
        textMenu.addCommand(new RunExample("6", statement6.toString(), controller6));
        textMenu.addCommand(new RunExample("7", statement7.toString(), controller7));
        textMenu.addCommand(new RunExample("8", statement8.toString(), controller8));
        textMenu.addCommand(new RunExample("9", statement9.toString(), controller9));
        textMenu.addCommand(new RunExample("10", statement10.toString(), controller10));
        textMenu.addCommand(new RunExample("11", statement11.toString(), controller11));
        textMenu.show();
    }
}
