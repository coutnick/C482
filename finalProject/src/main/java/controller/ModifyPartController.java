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
import model.Part;
import rains.finalproject.main;

import java.io.IOException;


/**
 * Modify Part controller
 * @author Nicholas Rains
 */

public class ModifyPartController {
    /**
     * Modify Part screen cancel button
     */
    public Button modPartCancelBut;
    /**
     * Modify part screen save button
     */
    public Button modPartSaveBut;
    /**
     * Modify part screen part ID text field
     */
    public TextField modPartIDTf;
    /**
     * Modify part screen part name text field
     */
    public TextField modPartNameTf;
    /**
     * Modify part screen part inventory text field
     */
    public TextField modPartInvTf;
    /**
     * Modify part screen part price text field
     */
    public TextField modPartPriceTf;
    /**
     * Modify part screen part max text field
     */
    public TextField modPartMaxTf;
    /**
     * Modify part screen machine ID or Company name text field
     */
    public TextField modPartMachineIdTf;
    /**
     * Modify part screen min text field
     */
    public TextField modPartMinTf;
    /**
     * Modify part screen radio toggle group
     */
    public ToggleGroup modPartRadios;
    /**
     * Modify part outsourced radio button
     */
    public RadioButton partModOutsourcedRadioBut;
    /**
     * Modify part in house radio button
     */
    public RadioButton partModInHouseRadio;
    /**
     * Modify part machine ID label
     */
    public Label modPartMachineIdLbl;

    /**
     * This sends the user back to the main screen when the cancel button is clicked
     * @param actionEvent part cancel button clicked
     * @throws IOException for loading
     */
    public void modPartCancelClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 450);
        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This sets the Modify part screens text fields from the part select on the Main Screen
     * @param p a part
     */
    public void sendPart(Part p){
        modPartIDTf.setText(String.valueOf(p.getId()));
        modPartNameTf.setText(p.getName());
        modPartInvTf.setText(String.valueOf(p.getStock()));
        modPartPriceTf.setText(String.valueOf(p.getPrice()));
        modPartMaxTf.setText(String.valueOf(p.getMax()));
        modPartMinTf.setText(String.valueOf(p.getMin()));
        if(p instanceof Outsourced){
            modPartMachineIdTf.setText(((Outsourced) p).getCompanyName());
            modPartMachineIdLbl.setText("Company Name");
            modPartRadios.selectToggle(partModOutsourcedRadioBut);
        }
        else if(p instanceof InHouse){
            modPartMachineIdTf.setText(String.valueOf(((InHouse) p).getMachineId()));
            modPartRadios.selectToggle(partModInHouseRadio);
        }

    }

    /**
     * RUNTIME ERROR I received a runtime error the first time I wrote this method becuase I was trying to use the
     *  partID to locate the index. This was giving me an out-of-bounds error due to me going over the size of the
     *  observable list
     *
     * This updates the part when the save button is clicked and sends the user back to the main screen
     * @param actionEvent save button clicked
     * @throws IOException number format exception if fields are not populated with the correct data types
     */

    public void modPartSaveButClick(ActionEvent actionEvent) throws IOException {
        try {
            if (partModInHouseRadio.isSelected()) {
                int id = Integer.parseInt(modPartIDTf.getText());
                String name = modPartNameTf.getText();
                int inv = Integer.parseInt(modPartInvTf.getText());
                double cost = Double.parseDouble(modPartPriceTf.getText());
                int max = Integer.parseInt(modPartMaxTf.getText());
                int min = Integer.parseInt(modPartMinTf.getText());
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

                int machineId = Integer.parseInt(modPartMachineIdTf.getText());



                InHouse i = new InHouse(id, name, cost, inv, min, max, machineId);
                Inventory.updatePart(MainScreenController.index, i);
            }
            if (partModOutsourcedRadioBut.isSelected()) {
                int id = Integer.parseInt(modPartIDTf.getText());
                String name = modPartNameTf.getText();
                int inv = Integer.parseInt(modPartInvTf.getText());
                double cost = Double.parseDouble(modPartPriceTf.getText());
                int max = Integer.parseInt(modPartMaxTf.getText());
                int min = Integer.parseInt(modPartMinTf.getText());
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
                String companyName = modPartMachineIdTf.getText();
                if(companyName == ""){
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setTitle("Error");
                    alert3.setContentText("Please enter a company name");
                    alert3.showAndWait();
                    return;
                }

                Outsourced o = new Outsourced(id, name, cost, inv, min, max, companyName);
                Inventory.updatePart(MainScreenController.index, o);
            }

            Parent root = FXMLLoader.load(main.class.getResource("MainScreen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 450);
            stage.setTitle("Inventory Management");
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
     * Sets the modPartMachineIdLabel to Company Name if the OutSourced radio button is selected
     * @param actionEvent out source toggle button selected
     */
    public void modPartOutSourcedSelected(ActionEvent actionEvent) {
        modPartMachineIdLbl.setText("Company Name");
        modPartMachineIdLbl.fireEvent(actionEvent);

    }

    /**
     * Sets the modPartMachineIdLabel to Machine ID if the InHouse radio button is selected
     * @param actionEvent in house toggle button selected
     */
    public void modPartInHouseSelected(ActionEvent actionEvent) {
        modPartMachineIdLbl.setText("Machine ID");
        modPartMachineIdLbl.fireEvent(actionEvent);
    }
}

