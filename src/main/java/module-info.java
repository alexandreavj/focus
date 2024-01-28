module com.focus.focus {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.focus.focus to javafx.fxml;
    exports com.focus.focus;
}