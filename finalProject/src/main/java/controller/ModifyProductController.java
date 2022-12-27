package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import rains.finalproject.main;

import java.io.IOException;

public class ModifyProductController {
    public Button modProdCancelBut;
    @FXML
    private Label welcomeText;

    public void modProdCancelClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 450);
        stage.setTitle("second screen");
        stage.setScene(scene);
        stage.show();
    }
}
