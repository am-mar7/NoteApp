package org.example.noteapplication;

import FileManagement.FileManager;
import FileManagement.User;
import My_Lib.valdation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField username_txt;
    @FXML
    private PasswordField password_txt;
    @FXML
    private PasswordField confirmpassword_txt;
    @FXML
    private Button SignUp_Btn;
    @FXML
    private Button Login_Btn;
    @FXML
    private Label Sign_Msg;
    HashMap<String,String> map ;
    public void try_Sign(ActionEvent e) throws IOException {
        User user = new User(username_txt.getText(),password_txt.getText());
        if(map.get(user.UserName()) == null && valdation.checkPassword(password_txt.getText(), confirmpassword_txt.getText())){
            FileManager.addUserInSystem(user);
            FileManager.setCurrentUser(user);
            // go to UserHomePage
            LocalController lc =  new LocalController();
            lc.SwitchPage(e,"HomePage.fxml");
            return;
        }
        // the signing up failed
        if(user.UserName().isEmpty() || password_txt.getText().isEmpty() || confirmpassword_txt.getText().isEmpty())
            Sign_Msg.setText("Please fill all the fields");
        else if(map.get(user.UserName()) == null && password_txt.getText().equals(confirmpassword_txt.getText()))
            Sign_Msg.setText("The password is not valid");
        else if(map.get(user.UserName()) != null)
            Sign_Msg.setText("User Name is taken");
        else
            Sign_Msg.setText("password dose not match");
    }
    public void goToLoginPage(ActionEvent e) throws IOException {
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"LoginScreen.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LinkedList<User> data = FileManager.UploadAllData();
        map = new HashMap<String,String>();
        int idx = 0;
        while(idx < data.size()) {
            map.put(data.get(idx).UserName(),data.get(idx).Password());// coping data to a map for better Time complexity
            idx++;
        }
    }
}