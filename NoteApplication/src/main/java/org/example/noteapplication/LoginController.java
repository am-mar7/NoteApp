package org.example.noteapplication;

import FileManagement.FileManager;
import FileManagement.User;
import NotePackge.Encryption;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.LinkedList;

public class LoginController {
    @FXML
    private TextField username_txt;
    @FXML
    private PasswordField password_txt;
    @FXML
    private Label login_Msg;

    public void try_Login(ActionEvent e) throws IOException {
        String userName = username_txt.getText();
        String password = Encryption.hash(password_txt.getText());
        LinkedList<User> data = FileManager.UploadAllData();
        if(data.isEmpty() || data == null) {
            login_Msg.setText("There are no Users in System,Please Sign Up");
            return;
        }
        if(userName.isEmpty() || password.isEmpty()) {
            login_Msg.setText("Please Enter Username and Password");
            return;
        }
        int idx = 0;
        while (idx < data.size()) {
            User currentUser = data.get(idx);
            if(currentUser.UserName().equals(userName) && currentUser.Password().equals(password)) {
                FileManager.setCurrentUser(currentUser);
//                 do login
                LocalController lc =  new LocalController();
                lc.SwitchPage(e,"HomePage.fxml");
                return;
            }
            idx++;
        }
        login_Msg.setText("Wrong Username or Password ,Please try again");
    }
    public void goToSignPage(ActionEvent e) throws IOException {
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"SignupScreen.fxml");
    }
}