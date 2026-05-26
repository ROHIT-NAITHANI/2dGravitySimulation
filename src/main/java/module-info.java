module com.example.realphysics {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.almasb.fxgl.all;

    opens com.example.realphysics to javafx.fxml;
    exports com.example.realphysics;
}