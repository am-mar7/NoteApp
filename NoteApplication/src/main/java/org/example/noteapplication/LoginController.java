package org.example.noteapplication;

import FileManagement.FileManager;
import FileManagement.User;
import NotePackge.Encryption;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField username_txt;
    @FXML
    private PasswordField password_txt;
    @FXML
    private Label login_Msg;
    HashMap<String,String> map ;
    HashMap<String, Integer> indices ;
    
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
        if(map.get(userName) == null || ! map.get(userName).equals(password))
            login_Msg.setText("Wrong User name or password");
        else{
            FileManager.setCurrentUser((User) data.get(indices.get(userName)));
            System.out.println((User) data.get(indices.get(userName)));
            LocalController lc = new LocalController();
            lc.SwitchPage(e, "HomePage.fxml");
        }
    }
    
    public void goToSignPage(ActionEvent e) throws IOException {
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"SignupScreen.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LinkedList<User> data = FileManager.UploadAllData();
        map = new HashMap<String,String>();
        indices = new HashMap<String , Integer>();
        int idx = 0;
        while(idx < data.size()) {
            // coping data to a map for better Time complexity
            map.put(data.get(idx).UserName(),data.get(idx).Password());
            // mapping the index of each user
            indices.put(data.get(idx).UserName() , idx);
            idx++;
        }
    }
}
