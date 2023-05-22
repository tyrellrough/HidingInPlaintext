package com.example.hidinginplaintextjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class for starting the application and graphical user interface.
 * @author Tyrell R
 * @version 1.0
 */
public class Application extends javafx.application.Application {
    /**
     * JavaFX starting method. It sets up window then displays it.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main_form.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Hiding in Plaintext");
        stage.getIcons().add(new Image("file:icon.jpg"));
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}