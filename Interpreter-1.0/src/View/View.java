package View;
import Controller.Controller;
import Exceptions.GeneralException;
import Model.*;
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
        System.out.println("1. Run the first example.");
        System.out.println("2. Run the second example.");
        System.out.println("3. Run the third example.");
    }

    public void firstExampleExecution() throws GeneralException {
        /**
         * int v; v=2;Print(v);
         * **/

        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        ListInterface<Value> output = new TheList<Value>();

        StatementInterface example = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v")))
        );

        ProgramState first_program = new ProgramState(executionStack, symbolTable, output, example);
        this.controller.addProgramState(first_program);
        this.controller.completeExecution();
    }

    public void secondExampleExecution() throws GeneralException {
        /**
         * int a;int b; a=2+3*5;b=a+1;Print(b);
         * **/

        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        ListInterface<Value> output = new TheList<Value>();

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


        ProgramState second_program = new ProgramState(executionStack, symbolTable, output, example);
        this.controller.addProgramState(second_program);
        this.controller.completeExecution();
    }

    public void thirdExampleExecution() throws GeneralException {
        /**
        bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
         **/
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        ListInterface<Value> output = new TheList<Value>();

        StatementInterface example = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                VariableExpression("v"))))));

        ProgramState third_program = new ProgramState(executionStack, symbolTable, output, example);
        this.controller.addProgramState(third_program);
        this.controller.completeExecution();
    }

    public void executeProgram() throws GeneralException {
        Scanner scanner = new Scanner(System.in);
        this.printProgramMenu();
        System.out.print("Enter the option number: ");
        String option_as_string = scanner.next();
        int option = Integer.parseInt(option_as_string);

        switch(option) {
            case 1:
                this.firstExampleExecution();
                break;
            case 2:
                this.secondExampleExecution();
                break;
            case 3:
                this.thirdExampleExecution();
                break;
            default:
                this.firstExampleExecution();
        }
    }

    public void run() throws GeneralException {
        Scanner scanner = new Scanner(System.in);

        while (this.running) {
            this.printMenu();
            System.out.print("Enter the option number: ");
            String input = scanner.next();
            int option = Integer.parseInt(input);

            switch (option) {
                case 0:
                    this.running = false;
                    break;
                case 1:
                    this.executeProgram();
                    break;
            }
        }

    }
}
