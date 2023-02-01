package controller;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Part;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;
import rains.finalproject.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Add product controller
 * @author Nicholas Rains
 */

public class AddProductController implements Initializable {
    /**
     * Add product screen cancel button
     */
    public Button addProdCancelBut;
    /**
     * Add product screen remove associated part button
     */
    public Button addProdRemoveAssociatedBut;
    /**
     * Add product screen add product button
     */
    public Button addProdAddBut;
    /**
     * Add product screen modify part button
     */
    public Button modPartSaveBut;
    /**
     * Add product screen product ID text field
     */
    public TextField addProdIdTf;
    /**
     * Add product screen product name text field
     */
    public TextField addProdNameTf;
    /**
     * Add product screen product inventory text field
     */
    public TextField addProdInvTf;
    /**
     * Add product screen product price text field
     */
    public TextField addProdPriceTf;
    /**
     * Add product screen product max text field
     */
    public TextField addProdMaxTf;
    /**
     * Add product screen product min text field
     */
    public TextField addProdMinTf;
    /**
     * Add product screen  part table
     */
    public TableView addProdPartTable;
    /**
     * Add product screen part ID column
     */
    public TableColumn addProdPartIdCol;
    /**
     * Add product screen part name column
     */
    public TableColumn addProdPartNameCol;
    /**
     * Add product screen part inventory column
     */
    public TableColumn addProdPartInvCol;
    /**
     * Add product screen part cost column
     */
    public TableColumn addProdPartCostCol;
    /**
     * Add product screen associated part table
     */
    public TableView addProdAssociatedTable;
    /**
     * Add product screen associated part id column
     */
    public TableColumn addProdAssociatedPartIdCol;
    /**
     * Add product screen associated part name column
     */
    public TableColumn addProdAssociatedPartNameCol;
    /**
     * Add product screen associated part inventory column
     */
    public TableColumn addProdAssociatedInvCol;
    /**
     * Add product screen associated part cost column
     */
    public TableColumn addProdAssociatedCostCol;
    /**
     * Add product screen part search text field
     */
    public TextField addProdSearchTf;
    /**
     * An observable list of associated parts
     */
    public ObservableList<Part> associatedParts  = FXCollections.observableArrayList();
    /**
     * Product for adding to associated parts
     */
    public Product p;

    /**
     * Set the product part table
     *
     * @param p an observable list of parts
     */
    public void setAddProdPartTable(ObservableList<Part> p) {

        addProdPartTable.setItems(Inventory.getsAllParts());
        addProdPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProdPartCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProdPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProdPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    /**
     *
     * Sends the user back to the main screen when the cancel button is clicked
     *
     * @param actionEvent cancel button clicked
     * @throws IOException for loading
     */
    public void addProdCancelClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 450);
        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Saves a product to  the Inventory along with its associated parts
     *
     * @param actionEvent save button clicked
     * @throws IOException for loading
     */
    public void addProdSaveButClick(ActionEvent actionEvent) throws IOException {
        try {
            String name = addProdNameTf.getText();
            int stock = Integer.parseInt(addProdInvTf.getText());
            Double cost = Double.valueOf(addProdPriceTf.getText());
            int max = Integer.parseInt(addProdMaxTf.getText());
            int min = Integer.parseInt(addProdMinTf.getText());
            if(max < min){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("The min value should be less than the max value");
                alert.showAndWait();
                return;
            }
            if(stock > max || stock < min){
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setContentText("The inventory level should be between the min and max value");
                alert2.showAndWait();
                return;
            }

            p = new Product(Inventory.nextId++, name, cost, stock, min, max);
            for (Part part : associatedParts) {
                p.addAssociatedPart(part);
            }

            Inventory.addProduct(p);

            Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 450);
            stage.setTitle("Inventory management");
            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter valid value into all of the text fields");
            alert.showAndWait();
        }
    }

        /**
     * Searched for a part in the products when the name or id of the part is entered. Shows all parts if the
     * field is empty
     * @param actionEvent product searched by ID or name and enter key is pressed
     */
    public void addProdSearched(ActionEvent actionEvent) {
        String searchedName = addProdSearchTf.getText();
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
        addProdPartTable.setItems(part);
    }

    /**
     * Adds an associated part for the product
     * @param actionEvent add button clicked
     */
    public void addProdAddButClick(ActionEvent actionEvent) {
        Part p = (Part) addProdPartTable.getSelectionModel().getSelectedItem();
        associatedParts.add(p);
    }

    /**
     * When initialized this sets the associated parts table
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProdAssociatedTable.setItems(associatedParts);
        addProdAssociatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProdAssociatedCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProdAssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProdAssociatedInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    /**
     * This removes an associated part from a product
     * @param actionEvent remove associated button clicked
     */
    public void addProdRemoveAssocPartClick(ActionEvent actionEvent) {
         int i;
         i = addProdAssociatedTable.getSelectionModel().getSelectedIndex();
        associatedParts.remove(i);
    }
}
