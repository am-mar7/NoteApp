package org.example.noteapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LocalController {
    public void SwitchPage (ActionEvent e, String pageName) throws IOException {
        Node node = (Node)e.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(pageName));
        Scene scene = new Scene(root,1200,700);
        stage.setTitle("Nationary");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(true);
    }
}
