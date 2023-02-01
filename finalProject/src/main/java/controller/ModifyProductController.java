package controller;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import rains.finalproject.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Modify Product controller
 * @author Nicholas Rains
 */

public class ModifyProductController implements Initializable {
    /**
     * Modify product screen cancel button
     */
    public Button modProdCancelBut;
    /**
     * Modify product screen add button
     */
    public Button modProdAddBut;
    /**
     * Modify product screen save button
     */
    public Button modSaveBut;
    /**
     * Modify product screen remove associated part button
     */
    public Button morRemoveAssocPartBut;
    /**
     * Modify product screen ID text field
     */
    public TextField modProdIdTf;
    /**
     * Modify product screen name text field
     */
    public TextField modProdNameTf;
    /**
     * Modify product screen inventory text field
     */
    public TextField modProdInvTf;
    /**
     * Modify product screen price text field
     */
    public TextField modProdPriceTf;
    /**
     * Modify product screen max text field
     */
    public TextField modProdMaxTf;
    /**
     * Modify product screen min text field
     */
    public TextField modProdMinTf;
    /**
     * Modify product screen part table
     */
    public TableView modProdPartTf;
    /**
     * Modify product screen product ID column
     */
    public TableColumn modProdPartIdCol;
    /**
     * Modify product screen part name column
     */
    public TableColumn modProdPartNameCol;
    /**
     * Modify product screen part inventory column
     */
    public TableColumn modProdPartInvCol;
    /**
     * Modify product screen part cost column
     */
    public TableColumn modProdPartCostCol;
    /**
     * Modify product screen associated part table
     */
    public TableView modProdAssociatedTf;
    /**
     * Modify product screen associated part ID column
     */
    public TableColumn modProdAssociatedPartIdCol;
    /**
     * Modify product screen associated part name column
     */
    public TableColumn modProdAssociatedPartNameCol;
    /**
     * Modify product screen associated part inventory column
     */
    public TableColumn modProdAssociatedInvCol;
    /**
     * Modify product screen associated part price column
     */
    public TableColumn modProdAssociatedPriceCol;
    /**
     * Modify product screen search text field
     */
    public TextField modProdSearchTf;
    /**
     * Product for updating product
     */
    public Product productOne;

    /**
     * Sends the user to the Main screen when the cancel button is clicked
     * @param actionEvent cancel button clicked
     * @throws IOException for loading
     */
    public void modProdCancelClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 450);
        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the product to the text fields on the screen
     * @param p a product
     */
    public void sendProduct(Product p){
        productOne = p;

        modProdIdTf.setText(String.valueOf(productOne.getId()));
        modProdNameTf.setText(productOne.getName());
        modProdInvTf.setText(String.valueOf(productOne.getStock()));
        modProdPriceTf.setText(String.valueOf(productOne.getPrice()));
        modProdMaxTf.setText(String.valueOf(productOne.getMax()));
        modProdMinTf.setText(String.valueOf(productOne.getMin()));

        modProdAssociatedTf.setItems(productOne.getAssociatedParts());
    }

    /**
     * Adds an associated part when the add button is clicked
     * @param actionEvent add associated product button clicked
     */
    public void modProdAddButClick(ActionEvent actionEvent) {
        Part p = (Part) modProdPartTf.getSelectionModel().getSelectedItem();
        productOne.addAssociatedPart(p);
        modProdAssociatedTf.setItems(productOne.getAssociatedParts());
    }

    /**
     * Sets the tables on the modify product screen when the screen is initialized
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modProdPartTf.setItems(Inventory.getsAllParts());

        modProdPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProdPartCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        modProdPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProdPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        modProdAssociatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProdAssociatedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        modProdAssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProdAssociatedInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    /**
     * Updates the product when the save button is clicked
     * @param actionEvent save button clicked
     * @throws IOException incorrect data types populated and for loading
     */
    public void ModSaveButClick(ActionEvent actionEvent) throws IOException {
        try {
            productOne.setId(Integer.parseInt(modProdIdTf.getText()));
            productOne.setName(modProdNameTf.getText());
            productOne.setPrice(Double.parseDouble(modProdPriceTf.getText()));
            productOne.setMax(Integer.parseInt(modProdMaxTf.getText()));
            productOne.setMin(Integer.parseInt(modProdMinTf.getText()));
            if(productOne.getMax() < productOne.getMin()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("The min value should be less than the max value");
                alert.showAndWait();
                return;
            }
            if(productOne.getStock() > productOne.getMax() || productOne.getStock() < productOne.getMin()){
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setContentText("The inventory level should be between the min and max value");
                alert2.showAndWait();
                return;
            }

            Inventory.updateProduct(MainScreenController.index, productOne);

            Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 450);
            stage.setTitle("Inventory Management");
            stage.setScene(scene);
            stage.show();
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter valid value into all of the text fields");
            alert.showAndWait();
        }
    }
    /**
     * This allows the user to search for a part in the part table by name or part ID. If the field is empty it will
     * show all the parts in the inventory.
     * @param actionEvent product searched by name or ID and the enter key pressed
     */
    public void modProdSearched(ActionEvent actionEvent) {
        String searchedName = modProdSearchTf.getText();
        ObservableList<Part> part = Inventory.lookupPart(searchedName);
        if(part.size() == 0){
            try{
                int searchedID = Integer.parseInt(searchedName);
                Part idPart = Inventory.lookupPart(searchedID);
                if(idPart != null)
                    part.add(idPart);
            }
            catch (NumberFormatException e) {
                //ignore
            }
        }
        modProdPartTf.setItems(part);
    }

    /**
     * Removes an associated part from the associated part list
     * @param actionEvent remove associated part clicked
     */
    public void modRemoveAssocButClick(ActionEvent actionEvent) {
        Part p = (Part) modProdAssociatedTf.getSelectionModel().getSelectedItem();
        productOne.deleteAssociatedPart(p);
    }
}
