package View;

import Controller.Controller;
import Exceptions.GeneralException;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.Repository;
import Repository.RepositoryInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectionController implements Initializable {
    @FXML
    private Repository repository1, repository2, repository3, repository4, repository5, repository6, repository7, repository8, repository9, repository10, repository11;
    @FXML
    private Controller controller1, controller2, controller3, controller4, controller5, controller6, controller7, controller8, controller9, controller10, controller11;
    @FXML
    private ProgramState program1, program2, program3, program4, program5, program6, program7, program8, program9, program10, program11;
    @FXML
    private ListView<Controller> programList;
    @FXML
    private ObservableList<Controller> items = FXCollections.observableArrayList();
    @FXML
    private Button runButton;
    private StatementInterface statement1, statement2, statement3, statement4, statement5, statement6, statement7, statement8, statement9, statement10, statement11;

    public void someButtonAction() throws IOException {
        System.out.println("button pressed");
        int index = programList.getSelectionModel().getSelectedIndex();
        System.out.println("Selected index is " + index);
        System.out.println(items.get(index));

        RunController runController = new RunController(items.get(index));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        fxmlLoader.setController(runController);

        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void createStatements() throws IOException {

        ///--------------------------------------------------------------------------------------------
        statement1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v")))
        );
        statement1.typecheck(new TheDictionary<>());

        program1 = ProgramState.createNewProgramState(statement1);
        repository1 = new Repository("log1.txt");
        controller1 = new Controller(repository1, program1);
        ///--------------------------------------------------------------------------------------------
        statement2 = new CompoundStatement(
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
        statement2.typecheck(new TheDictionary<>());

        program2= ProgramState.createNewProgramState(statement2);
        repository2 = new Repository("log2.txt");
        controller2 = new Controller(repository2, program2);
        ///--------------------------------------------------------------------------------------------
        statement3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                VariableExpression("v"))))));

        statement3.typecheck(new TheDictionary<>());

        program3= ProgramState.createNewProgramState(statement3);
        repository3 = new Repository("log3.txt");
        controller3 = new Controller(repository3, program3);
        ///--------------------------------------------------------------------------------------------
        statement4 = new CompoundStatement(
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
        statement4.typecheck(new TheDictionary<>());

        program4= ProgramState.createNewProgramState(statement4);
        repository4 = new Repository("log4.txt");
        controller4 = new Controller(repository4, program4);
        ///--------------------------------------------------------------------------------------------
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("test.in", true)));
        writer.println("15");
        writer.println("50");
        writer.close();

        statement5 = new CompoundStatement(
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
        statement5.typecheck(new TheDictionary<>());

        program5= ProgramState.createNewProgramState(statement5);
        repository5 = new Repository("log5.txt");
        controller5 = new Controller(repository5, program5);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack6 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable6 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable6 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output6 = new TheList<Value>();
        HeapInterface heap6 = new Heap();

        statement6 = new CompoundStatement(
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

        program6 = new ProgramState(executionStack6, symbolTable6, fileTable6, heap6, output6, statement6);
        repository6 = new Repository("log6.txt");
        controller6 = new Controller(repository6, program6);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack7 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable7 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable7 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output7 = new TheList<Value>();
        HeapInterface heap7 = new Heap();

        statement7 = new CompoundStatement(
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

        program7 = new ProgramState(executionStack7, symbolTable7, fileTable7, heap7, output7, statement7);
        repository7 = new Repository("log7.txt");
        controller7 = new Controller(repository7, program7);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack8 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable8 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable8 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output8 = new TheList<Value>();
        HeapInterface heap8 = new Heap();

        statement8 = new CompoundStatement(
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

        program8 = new ProgramState(executionStack8, symbolTable8, fileTable8, heap8, output8, statement8);
        repository8 = new Repository("log8.txt");
        controller8 = new Controller(repository8, program8);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack9 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable9 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable9 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output9 = new TheList<Value>();
        HeapInterface heap9 = new Heap();

        statement9 = new CompoundStatement(
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

        program9 = new ProgramState(executionStack9, symbolTable9, fileTable9, heap9, output9, statement9);
        repository9 = new Repository("log9.txt");
        controller9 = new Controller(repository9, program9);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack10 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable10 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable10 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output10 = new TheList<Value>();
        HeapInterface heap10 = new Heap();

        statement10 = new CompoundStatement(
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

        program10 = new ProgramState(executionStack10, symbolTable10, fileTable10, heap10, output10, statement10);
        repository10 = new Repository("log10.txt");
        controller10 = new Controller(repository10, program10);
        ///--------------------------------------------------------------------------------------------
        StackInterface<StatementInterface> executionStack11 = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable11 = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable11 = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output11 = new TheList<Value>();
        HeapInterface heap11 = new Heap();

        statement11 = new CompoundStatement(
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

        program11 = new ProgramState(executionStack11, symbolTable11, fileTable11, heap11, output11, statement11);
        repository11 = new Repository("log11.txt");
        controller11 = new Controller(repository11, program11);
        System.out.println("Statements created.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            createStatements();
        } catch (IOException e) {
            e.printStackTrace();
        }

        items.add(controller1);
        items.add(controller2);
        items.add(controller3);
        items.add(controller4);
        items.add(controller5);
        items.add(controller6);
        items.add(controller7);
        items.add(controller8);
        items.add(controller9);
        items.add(controller10);
        items.add(controller11);

        programList.setItems(items);
        System.out.println("Initialized items[]");
    }

}
