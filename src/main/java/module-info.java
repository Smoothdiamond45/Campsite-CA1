module org.example.campsiteca1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.campsiteca1 to javafx.fxml;
    exports org.example.campsiteca1;
}