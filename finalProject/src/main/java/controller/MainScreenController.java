package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Main screen controller
 * @author Nicholas Rains
 */

public class MainScreenController implements Initializable {
    /**
     * The main screens part ID table column
     */
    public TableColumn<Object, Object> mainPartId;
    /**
     * The main screens part name table column
     */
    public TableColumn<Object, Object> mainPartName;
    /**
     * The main screens part inventory table column
     */
    public TableColumn mainPartInvLevel;
    /**
     * The main screens part Price table Column
     */
    public TableColumn mainPartPrice;
    /**
     * The main screens product ID table column
     */
    public TableColumn mainProdId;
    /**
     * The main screens product name table column
     */
    public TableColumn mainProdName;
    /**
     * The main screens product inventory table column
     */
    public TableColumn mainProdInvLevel;
    /**
     * The main screens product price table column
     */
    public TableColumn mainProdPrice;
    /**
     * The main screens add part button
     */
    public Button mainAddPartBut;
    /**
     * The main screens modify part button
     */
    public Button mainModifyPartBut;
    /**
     * The main screens part delete button
     */
    public Button mainDeleteBut;
    /**
     * The main screens product add button
     */
    public Button mainProdAddBut;
    /**
     * The main screens product modify button
     */
    public Button mainProdModifyBut;
    /**
     * The main screens product delete button
     */
    public Button mainProdDeleteBut;
    /**
     * The main screens part search field
     */
    public TextField mainPartSearch;
    /**
     * The main screens product search field
     */
    public TextField mainProdSearch;
    /**
     * The main screens exit button
     */
    public Button mainExtBut;
    /**
     * The main screens part table
     */
    public TableView<Part> partTable;
    /**
     * The main screens product table
     */
    public TableView<Product> prodTable;
    /**
     * Index of the inventory
     */
    public static int index;
    /**
     * Obserablelist of associated parts
     */
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**
     * A product
     */
    public Product p;

    /**
     * When the main screens add part button is clicked send the user to the add part screen
     *
     * @param actionEvent add part button clicked
     * @throws IOException for the load
     */
    public void mainPartAddClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("AddPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * When the main screens modify part button is clicked send the user to the modify part screen to modify
     * The index is used for updating a part
     *
     * @param actionEvent modify part button clicked
     * @throws IOException null point exception if no items selected
     */
    public void mainPartModClick(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("ModifyPart.fxml"));
            loader.load();

            ModifyPartController MpcController = loader.getController();
            MpcController.sendPart(partTable.getSelectionModel().getSelectedItem());
            index = partTable.getSelectionModel().getSelectedIndex();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.setTitle("Modify Part");
            stage.show();
        } catch (NullPointerException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a part");
            alert.showAndWait();
        }
    }
    /**
     * When the main screens add product button is clicked send the the user to the add product screen
     *
     * @param actionEvent add product button clicked
     * @throws IOException
     */
    public void mainProdAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("AddProduct.fxml"));
        loader.load();

        AddProductController ApcController = loader.getController();
        ApcController.setAddProdPartTable(partTable.getItems());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Product");
        stage.show();


    }

    /**
     * When the main screens product button is clicked this event sends the user to the modify product screen
     * The index is used for updating and deleting the product
     *
     * @param actionEvent modify product button clicked
     * @throws IOException null point exception if no items are clicked
     */
    public void mainProdModClick(ActionEvent actionEvent) throws IOException {
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("ModifyProduct.fxml"));
        loader.load();

        ModifyProductController modProdController = loader.getController();
        modProdController.sendProduct(prodTable.getSelectionModel().getSelectedItem());
        index = prodTable.getSelectionModel().getSelectedIndex();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.setTitle("Modify Product");
        stage.show();
    } catch (NullPointerException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a product");
            alert.showAndWait();

        }
    }
        /**
     * Sets the table with parts and products from the inventory when the application is started
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Started");

        partTable.setItems(Inventory.getsAllParts());
        prodTable.setItems(Inventory.getAllProducts());

        mainPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        mainPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));

        mainProdId.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainProdPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        mainProdName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainProdInvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    /**
     * Lets the user search for a part by name or product id. Contains all parts in inventory
     * when the textfield is empty
     *
     * @param actionEvent searching for parts and enter button pushed
     */
    public void partSearched(ActionEvent actionEvent) {
        String searchedName = mainPartSearch.getText();
        ObservableList<Part> part = Inventory.lookupPart(searchedName);
        if (part.size() == 0) {
            try {
                int searchedID = Integer.parseInt(searchedName);
                Part idPart = Inventory.lookupPart(searchedID);
                if (idPart != null)
                    part.add(idPart);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        partTable.setItems(part);
    }

    /**
     * Lets the user search for a product in the inventory by name or id. Shows all products in inventory when
     * the textfield is empty
     *
     * @param actionEvent searching for product and enter button pushed
     */

    public void prodSearched(ActionEvent actionEvent) {
        String searchedName = mainProdSearch.getText();
        ObservableList<Product> product = Inventory.lookupProduct(searchedName);
        if (product.size() == 0) {
            try {
                int searchedID = Integer.parseInt(searchedName);
                Product idProd = Inventory.lookupProduct(searchedID);
                if (idProd != null)
                    product.add(idProd);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        prodTable.setItems(product);
    }

    /**
     * When the user selects a part and clicks the delete button delete the part from the inventory
     *
     * @param actionEvent delete button clicked
     */

    public void mainPartDeleteButclick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This part will be deleted from the inventory!");

        alert.setTitle("Confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() ==  ButtonType.OK) {
            Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());
        }

    }

    /**
     * When the user selects a product and clicks the delete button the product is deleted from the inventory
     *
     * @param actionEvent product delete button clicked
     */
    public void mainProdDeleteButClick(ActionEvent actionEvent) {

        p = prodTable.getSelectionModel().getSelectedItem();
                
        if (p.getAssociatedParts().size() >= 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("All associated parts must be removed from the product before it can be deleted");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This product will be deleted from the inventory!");

        alert.setTitle("Confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() ==  ButtonType.OK) {
            Inventory.deleteProduct(p);
            System.out.println("Product Deleted");
        }

    }

    /**
     * Closes the application when the user clicks the exit button
     *
     * @param actionEvent exit button clicked
     */
    public void MainExtButClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear added, updated, or deleted " +
                "from the Inventory");

        alert.setTitle("Confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() ==  ButtonType.OK) {
            Platform.exit();
        }

    }
}

