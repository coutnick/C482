module rains.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens rains.finalproject to javafx.fxml;
    exports rains.finalproject;
}