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
import model.Part;
import rains.finalproject.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    public void mainPartModClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("ModifyPart.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("second screen");
        stage.setScene(scene);
        stage.show();
    }

    public void mainProdAddClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("AddProduct.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 550);
        stage.setTitle("second screen");
        stage.setScene(scene);
        stage.show();
    }

    public void mainProdModClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("ModifyProduct.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 550);
        stage.setTitle("second screen");
        stage.setScene(scene);
        stage.show();
    }
}
