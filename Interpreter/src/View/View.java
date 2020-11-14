package View;
import Controller.Controller;
import Exceptions.GeneralException;
import Model.*;
import Model.ADT.*;
import Model.Expression.ArithmeticExpression;
import Model.Expression.LogicExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.*;
import java.util.Scanner;

public class View {
    private boolean running;
    private Controller controller;

    public View(Controller controller) {
        this.running = true;
        this.controller = controller;
    }

    public void printMenu() {
        System.out.println("1. Run a program");
        System.out.println("0. Exit");
    }

    public void printProgramMenu() {
        System.out.println("1.  int v; v=2;Print(v);");
        System.out.println("2.  int a;int b; a=2+3*5;b=a+1;Print(b);");
        System.out.println("3.  bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v);");
        System.out.println("4.  int a, b; bool c; a = 2, b = 3; c = true; (If (a + b == 5 && c) Then Print(1) Else Print(0))");
        System.out.println("5.  string varf; varf=\"test.in\"; openRFile(varf); int varc; readFile(varf,varc);print(varc); readFile(varf,varc);print(varc);closeRFile(varf);");
    }

    public void firstExampleExecution() throws GeneralException, IOException {
        /**
         * int v; v=2;Print(v);
         * **/

        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        StatementInterface example = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v")))
        );

        ProgramState first_program = new ProgramState(executionStack, symbolTable, fileTable, heap, output, example);
        this.controller.addProgramState(first_program);
        this.controller.completeExecution();
    }

    public void secondExampleExecution() throws GeneralException, IOException {
        /**
         * int a;int b; a=2+3*5;b=a+1;Print(b);
         * **/

        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        StatementInterface example = new CompoundStatement(
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


        ProgramState second_program = new ProgramState(executionStack, symbolTable, fileTable, heap, output, example);
        this.controller.addProgramState(second_program);
        this.controller.completeExecution();
    }

    public void thirdExampleExecution() throws GeneralException, IOException {
        /**
        bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
         **/
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        StatementInterface example = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                VariableExpression("v"))))));

        ProgramState third_program = new ProgramState(executionStack, symbolTable, fileTable, heap, output, example);
        this.controller.addProgramState(third_program);
        this.controller.completeExecution();
    }

    public void fourthExampleExecution() throws GeneralException, IOException {
        /**
         int a, b; bool c; a = 2, b = 3; c = true; (If (a + b == 5 && c) Then Print(1) Else Print(0))
         **/
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        StatementInterface example = new CompoundStatement(
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

        ProgramState fourth_program = new ProgramState(executionStack, symbolTable, fileTable, heap, output, example);
        this.controller.addProgramState(fourth_program);
        this.controller.completeExecution();
    }

    public void fifthExampleExecution() throws GeneralException, IOException {
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();

        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("test.in", true)));
        writer.println("15");
        writer.println("50");
        writer.close();

        StatementInterface example = new CompoundStatement(
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

        ProgramState fifth_program = new ProgramState(executionStack, symbolTable, fileTable, heap, output, example);
        this.controller.addProgramState(fifth_program);
        this.controller.completeExecution();
    }

    public void executeProgram() throws GeneralException, IOException {
        Scanner scanner = new Scanner(System.in);
        this.printProgramMenu();
        System.out.print("Enter the option number: ");
        String option_as_string = scanner.next();
        int option = Integer.parseInt(option_as_string);

        switch (option) {
            case 1 -> this.firstExampleExecution();
            case 2 -> this.secondExampleExecution();
            case 3 -> this.thirdExampleExecution();
            case 4 -> this.fourthExampleExecution();
            case 5 -> this.fifthExampleExecution();
            default -> System.out.println("Enter an option between 1 and 5.");
        }
    }

    public void run() throws GeneralException, IOException {
        Scanner scanner = new Scanner(System.in);

        while (this.running) {
            this.printMenu();
            System.out.print("Enter the option number: ");
            String input = scanner.next();
            int option = Integer.parseInt(input);

            switch (option) {
                case 0 -> this.running = false;
                case 1 -> this.executeProgram();
            }
        }

    }
}
