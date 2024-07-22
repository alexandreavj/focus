package com.focus.focus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Focus extends Application {
    /**
     * Start focus desktop app.
     * @param stage JavaFX stage.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Focus.class.getResource("home_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("focus: home");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launch focus desktop app.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}