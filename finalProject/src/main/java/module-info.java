/**
 * Module for the Inventory Management Program
 * @author Nicholas Rains
 */
module rains.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens rains.finalproject to javafx.fxml;
    opens controller to javafx.fxml;
    opens model to javafx.fxml;

    exports rains.finalproject;
    exports controller;
    exports model;

    }