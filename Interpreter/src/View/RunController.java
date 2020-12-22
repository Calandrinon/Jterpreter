package View;

import Controller.Controller;
import Model.ProgramState;
import Repository.Repository;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RunController implements Initializable {
    private Repository repository;
    private Controller controller;
    private ProgramState programState;

    public RunController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            controller.completeExecution();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program execution ended successfully.");
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
