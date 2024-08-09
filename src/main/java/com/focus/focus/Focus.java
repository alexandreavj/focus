package com.focus.focus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Focus desktop app.
 * @author Alexandre Jacob
 * @version 1.0
 */
public class Focus extends Application {
    /**
     * Start focus desktop app.
     * @param stage JavaFX stage.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Focus.class.getResource("home_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("focus: home");
        stage.setScene(scene);

        // set taskbar and window icons
        try {
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));
            Taskbar.getTaskbar().setIconImage(Toolkit.getDefaultToolkit().getImage(Objects.requireNonNull(getClass().getResource("logo.png"))));
        } catch (Exception e) {
            System.out.println("Could not set taskbar and window icons: " + e);
        }

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