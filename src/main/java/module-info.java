module com.focus.focus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires javafx.media;


    opens com.focus.focus to javafx.fxml;
    exports com.focus.focus;
}