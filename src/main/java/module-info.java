module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.graph.gui to javafx.fxml;
    exports com.graph.gui;
}