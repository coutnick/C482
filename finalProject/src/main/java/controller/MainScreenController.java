package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rains.finalproject.main;

import java.io.IOException;
import java.util.Objects;

public class MainScreenController {
    public TableColumn mainPartId;
    public TableColumn mainPartName;
    public TableColumn mainPartInvLevel;
    public TableColumn mainPartPrice;
    public TableColumn mainProdId;
    public TableColumn mainProdName;
    public TableColumn mainProdInvLevel;
    public TableColumn mainProdPrice;
    public Button mainAddPartBut;
    public Button mainModifyPartBut;
    public Button mainDeleteBut;
    public Button mainProdAddBut;
    public Button mainProdModifyBut;
    public Button mainProdDeleteBut;
    public TextField mainPartSearch;
    public TextField mainProdSearch;
    public Button mainExtBut;

    public void mainPartAddClick(ActionEvent actionEvent) throws IOException {
        //load widget hierarchy of the next screen
        Parent root = FXMLLoader.load(main.class.getResource("AddPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("second screen");
        stage.setScene(scene);
        stage.show();
    }
}
