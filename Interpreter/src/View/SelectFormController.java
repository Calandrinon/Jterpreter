package View;

import Model.ADT.TheDictionary;
import Model.Expression.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Repository.*;
import Controller.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Statement.CloseFileStatement;
import Model.Statement.OpenFileStatement;
import Model.Statement.ReadFileStatement;
import Model.Statement.HeapAllocationStatement;
import Model.Statement.HeapWriteStatement;
import Repository.RepositoryInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectFormController implements Initializable {
    private List<StatementInterface> programStatements;
    private RunFormController mainWindowController;

    @FXML
    private ListView<String> programListView;

    @FXML
    private Button executeButton;

    public void setMainWindowController(RunFormController mainWindowController){
        this.mainWindowController = mainWindowController;
    }

    private void buildProgramStatements()
    {
        /**
        StatementInterface ex1 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(2)),
                new PrintStatement(new VariableExpression("v")));

        StatementInterface ex2 =  new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new
                ArithmeticExpression(new ConstantExpression(3), new ConstantExpression(5), OperationEnum.MULTIPLY), OperationEnum.PLUS)),
                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new
                        ConstantExpression(1), OperationEnum.PLUS)), new PrintStatement(new VariableExpression("b"))));

        StatementInterface ex3 = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new ConstantExpression(2), OperationEnum.MINUS)),
                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ConstantExpression(2)),
                        new AssignStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));

        StatementInterface ex4 = new CompoundStatement(new OpenStatement("var_f", "test.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("var_c")),
                                new CompoundStatement(new IfStatement(new VariableExpression("var_c"),
                                        new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                                                new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0))),
                                        new CloseStatement(new VariableExpression("var_f"))))));


        StatementInterface ex5 = new CompoundStatement(new AssignStatement("v",
                new ConstantExpression(10)), new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new PrintStatement(new VariableExpression("v")))));


        StatementInterface ex6 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(22)),
                                new CompoundStatement(new PrintStatement(
                                        new ArithmeticExpression(new ConstantExpression(100), new HeapReadingExpression("v"), OperationEnum.PLUS)),
                                        new PrintStatement(new ArithmeticExpression(new ConstantExpression(100), new HeapReadingExpression("a"), OperationEnum.PLUS))))));

        StatementInterface ex7 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(20)),
                                new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new HeapReadingExpression("a")))))));

        StatementInterface ex8 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(20)),
                                new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                new CompoundStatement(new PrintStatement(new HeapReadingExpression("a")),
                                                        new AssignStatement("a", new ConstantExpression(0))))))));

        StatementInterface ex9 = new PrintStatement(new ArithmeticExpression(new ConstantExpression(10),
                new BooleanExpression(new ConstantExpression(2), new ConstantExpression(6), "<"), OperationEnum.PLUS));


        StatementInterface ex10 = new PrintStatement(new BooleanExpression(new ArithmeticExpression(new ConstantExpression(10), new ConstantExpression(2), OperationEnum.PLUS),
                new ConstantExpression(6), "<"));


        StatementInterface ex11 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(6)),
                new CompoundStatement(new WhileStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(4), OperationEnum.MINUS),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",
                                new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)))), new PrintStatement(new VariableExpression("v"))));

        StatementInterface ex12 = new CompoundStatement(new OpenStatement("var_f", "test2.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))));

        StatementInterface ex13 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("a", new ConstantExpression(22)),
                        new CompoundStatement(
                                new ForkStatement(new CompoundStatement(new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression("a"))))), new PrintStatement(new ArithmeticExpression(new ConstantExpression(23), new VariableExpression("c"), OperationEnum.PLUS)))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression("a"))))));

        StatementInterface ex14 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(0)),
                new CompoundStatement(new RepeatStatement(new CompoundStatement(new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)))), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.PLUS))), new BooleanExpression(new VariableExpression("v"), new ConstantExpression(3), "==")),
                        new CompoundStatement(new AssignStatement("x", new ConstantExpression(1)),
                                new CompoundStatement(new AssignStatement("y", new ConstantExpression(2)),
                                        new CompoundStatement(new AssignStatement("z", new ConstantExpression(3)),
                                                new CompoundStatement(new AssignStatement("w", new ConstantExpression(4)),
                                                        new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), OperationEnum.MULTIPLY))))))));

        programStatements = new ArrayList<>(Arrays.asList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14));
        **/

        StatementInterface example1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v")))
        );
        example1.typecheck(new TheDictionary<>());


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


        StatementInterface example3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                VariableExpression("v"))))));

        example3.typecheck(new TheDictionary<>());

        programStatements = new ArrayList<>(Arrays.asList(example1, example2, example3));
    }

    private List<String> getStringRepresentations(){
        return programStatements.stream().map(StatementInterface::toString).collect(Collectors.toList());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        buildProgramStatements();
        programListView.setItems(FXCollections.observableArrayList(getStringRepresentations()));

        executeButton.setOnAction(actionEvent -> {
            int index = programListView.getSelectionModel().getSelectedIndex();

            if(index < 0)
                return;

            ProgramState initialProgramState = ProgramState.createNewProgramState(programStatements.get(index));
            RepositoryInterface repository = new Repository("log" + index + ".txt");
            //repository.addProgramState(initialProgramState);
            Controller ctrl = new Controller(repository, initialProgramState);

            mainWindowController.setController(ctrl);
        });
    }
}
