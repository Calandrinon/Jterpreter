import Controller.Controller;
import Exceptions.GeneralException;
import Model.ADT.*;
import Model.Expression.ArithmeticExpression;
import Model.Expression.LogicExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.Repository;
import Repository.RepositoryInterface;
import View.View;
import View.TextMenu;
import View.ExitCommand;
import View.RunExample;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws GeneralException, IOException {
        ///--------------------------------------------------------------------------------------------
        StatementInterface example1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v")))
        );

        ProgramState program1= ProgramState.createNewProgramState(example1);
        RepositoryInterface repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1);
        controller1.addProgramState(program1);
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

        ProgramState program2= ProgramState.createNewProgramState(example2);
        RepositoryInterface repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2);
        controller2.addProgramState(program2);
        ///--------------------------------------------------------------------------------------------
        StatementInterface example3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                VariableExpression("v"))))));

        ProgramState program3= ProgramState.createNewProgramState(example3);
        RepositoryInterface repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3);
        controller3.addProgramState(program3);
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
                                                                new LogicExpression(new LogicExpression(new ArithmeticExpression("+", new VariableExpression("a"), new VariableExpression("b")), "==", new ValueExpression(new IntValue(5))), "&&", new VariableExpression("c")),
                                                                new PrintStatement(new ValueExpression(new IntValue(1))),
                                                                new PrintStatement(new ValueExpression(new IntValue(0)))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        ProgramState program4= ProgramState.createNewProgramState(example4);
        RepositoryInterface repository4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repository4);
        controller4.addProgramState(program4);
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
                                                new ReadFileStatement(new VariableExpression("varf"), new VariableExpression("varc")),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), new VariableExpression("varc")),
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

        ProgramState program5= ProgramState.createNewProgramState(example5);
        RepositoryInterface repository5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repository5);
        controller5.addProgramState(program5);


        TextMenu textMenu = new TextMenu();
        textMenu.addCommand(new ExitCommand("0", "exit"));
        textMenu.addCommand(new RunExample("1", example1.toString(), controller1));
        textMenu.addCommand(new RunExample("2", example2.toString(), controller2));
        textMenu.addCommand(new RunExample("3", example3.toString(), controller3));
        textMenu.addCommand(new RunExample("4", example4.toString(), controller4));
        textMenu.addCommand(new RunExample("5", example5.toString(), controller5));
        textMenu.show();
    }
}
