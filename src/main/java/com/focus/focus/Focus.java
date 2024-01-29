package com.focus.focus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Focus extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Focus.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("focus");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            HomeController controller = new HomeController();
        } catch (IOException e) {
            System.out.println("IOException");
        }
        launch();
    }
}