module com.example.memoria_jatek {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires  javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;


    //opens com.example.memoria_jatek to javafx.fxml;

    exports com.example.memoria_jatek.ViewController;
    exports com.example.memoria_jatek.Controller;
    exports com.example.memoria_jatek.Model;
    opens com.example.memoria_jatek.Controller to javafx.fxml;
}