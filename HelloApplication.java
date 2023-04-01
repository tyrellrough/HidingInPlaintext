package com.example.testingproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BinaryOperator;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Grammar g2 = FileOperations.parseGrammarTextFile("lotofchoicestestgrammar.txt");
        g2.run("START", "AB");
        String textToEncode = "A";
        String binaryToEncode = StringOperations.convertStringToBinaryString(textToEncode);

        System.out.println(binaryToEncode);
        //System.out.println(g2);
        //System.out.println(g2.generatedText);

        Decoder d2 = new Decoder();
        d2.decode(g2.generatedText, g2);
        System.out.println(d2.bitsGenerated);
        String textGenerated = StringOperations.convertBinaryStringToString(d2.bitsGenerated);
        System.out.println(textGenerated);






        //launch();
    }
}