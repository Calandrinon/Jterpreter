package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectionController implements Initializable {
    @FXML
    private ListView<String> programList;
    private ObservableList<String> items = FXCollections.observableArrayList();
    private Button runButton;

    public void someButtonAction() throws IOException {
        System.out.println("button pressed");
        int index = programList.getSelectionModel().getSelectedIndex();
        System.out.println("Selected index is " + index);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items.add("abc");
        items.add("def");
        programList.setItems(items);
    }

}
