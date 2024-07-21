module com.focus.focus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.focus.focus to javafx.fxml;
    exports com.focus.focus;
}