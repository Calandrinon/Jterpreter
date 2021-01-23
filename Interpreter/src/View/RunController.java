package View;

import Controller.Controller;
import Model.ProgramState;
import Repository.Repository;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class RunController implements Initializable {
    private Repository repository;
    private Controller controller;
    private ProgramState programState;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> heapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> heapAddressColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> heapValueColumn;

    public RunController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heapAddressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapValueColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());


    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
