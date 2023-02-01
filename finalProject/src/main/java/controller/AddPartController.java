package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import rains.finalproject.main;

import java.io.IOException;

/**
 * Add part controller
 * @author  Nicholas Rains
 */

public class AddPartController {
    /**
     * Add part screens cancel button
     */
    public Button addPartCancelBut;
    /**
     * Add part screens save button
     */
    public Button addPartSaveBut;
    /**
     * Add part screen Part ID text field
     */
    public TextField addPartId;
    /**
     * Add part screen Part name text field
     */
    public TextField addPartName;
    /**
     * Add part screen part inventory text field
     */
    public TextField addPartInv;
    /**
     * Add part screen part price text field
     */
    public TextField addPartPrice;
    /**
     * Add part screen part Maximum amount text field
     */
    public TextField addPartMax;
    /**
     * Add part screen part machine id text field
     */
    public TextField addPartMachineId;
    /**
     * Add part screen part minimum text field
     */
    public TextField addPartMin;
    /**
     * Add part screen InHouse radio button
     */
    public RadioButton addInHouseRadioBut;
    /**
     * Add part screen OutSourced radio button
     */
    public RadioButton addOutSourcedRadioBut;
    /**
     * Add part radio buttons toggle group
     */
    public ToggleGroup addPartRadios;
    /**
     * Add part label
     */
    public Label addPartLabel;
    /**
     * When the add part cancel button is clicked send the user back to the main screen
     *
     * @param actionEvent cancel button is clicked
     * @throws IOException for loading
     */
    public void addPartCancelClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 450);
        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * When the InHouse radio button is selected set the add part label to Machine ID
     * @param actionEvent in house radio button selected
     */
    public void addInHouseRadioButSelected(ActionEvent actionEvent) {
        addPartLabel.setText("Machine ID");
        addPartLabel.fireEvent(new ActionEvent());
    }

    /**
     * When the OutSourced radio button is selected set the add part label to Company Name
     * @param actionEvent outsourced radio button is selected
     */
    public void addOutSourceRadioButtonSelected(ActionEvent actionEvent) {
        addPartLabel.setText("Company Name");
        addPartLabel.fireEvent(new ActionEvent());


    }

    /**
     * When the add part save button is clicked save the part and add it to the inventory
     * Sends the user back to the main screen
     *
     * @param actionEvent save button is clicked
     * @throws IOException for loading and if incorrect data types are populated in the input fields
     */
    public void onAddPartSave(ActionEvent actionEvent) throws IOException {
        try{
            if(addOutSourcedRadioBut.isSelected()){

                String name = addPartName.getText();
                Double cost = Double.valueOf(addPartPrice.getText());
                int inv = Integer.parseInt(addPartInv.getText());
                int max = Integer.parseInt(addPartMax.getText());
                int min = Integer.parseInt(addPartMin.getText());
                if(max < min){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("The min value should be less than the max value");
                    alert.showAndWait();
                    return;
                }
                if(inv > max || inv < min){
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setContentText("The inventory level should be between the min and max value");
                    alert2.showAndWait();
                    return;
                }
                String machineId = addPartMachineId.getText();
                if(machineId.equals("")){
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setTitle("Error");
                    alert3.setContentText("Please enter a company name");
                    alert3.showAndWait();
                    return;
                }

                Outsourced p = new Outsourced(Inventory.nextId++, name, cost, inv, min, max, machineId);
                Inventory.addPart(p);
            }
            if(addInHouseRadioBut.isSelected()){

                String name = addPartName.getText();
                Double cost = Double.valueOf(addPartPrice.getText());
                int inv = Integer.parseInt(addPartInv.getText());
                int max = Integer.parseInt(addPartMax.getText());
                int min = Integer.parseInt(addPartMin.getText());
                if(max < min){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("The min value should be less than the max value");
                    alert.showAndWait();
                    return;
                }
                if(inv > max || inv < min){
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setContentText("The inventory level should be between the min and max value");
                    alert2.showAndWait();
                    return;
                }
                int machineId = Integer.parseInt(addPartMachineId.getText());
                InHouse i = new InHouse(Inventory.nextId++, name, cost, inv, min, max, machineId);
                Inventory.addPart(i);
            }

            Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 450);
            stage.setTitle("Inventory Management");
            stage.setScene(scene);
            stage.show();
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter valid value into all of the text fields");
            alert.showAndWait();
        }
    }

}