package org.example.noteapplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root,1200,700);
        stage.setTitle("Nationary");
        stage.setScene(scene);
        stage.show();
        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}