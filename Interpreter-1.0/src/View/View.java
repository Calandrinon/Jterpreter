package View;
import Controller.Controller;
import Model.*;

import javax.swing.plaf.nimbus.State;
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

    public void firstExampleExecution() {
        StatementInterface example = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v")))
        );
    }

    public void secondExampleExecution() {
        StatementInterface example = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                    new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression("*", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                        new AssignmentStatement("b",
                                                new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new IntValue(1)))))
                        )
        );
    }

    public void thirdExampleExecution() {

    }

    public void executeProgram() {
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
            case 3:
                this.thirdExampleExecution();
            default:
                this.firstExampleExecution();
        }
    }

    public void run() {
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
