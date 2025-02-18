package org.example.noteapplication;

import FileManagement.FileManager;
import FileManagement.User;
import NotePackge.Encryption;
import NotePackge.Note;
import NotePackge.Secured_Note;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import org.jetbrains.annotations.NotNull;
import javafx.collections.ObservableList;
import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    Label search_Msg = new Label();
    @FXML
    private Label emptyLabel = new Label();
    @FXML
    private Label usernameLabel = new Label();
    @FXML
    private TextField SearchBar_txt = new TextField();
    @FXML
    private ListView<Note> noteListView = new ListView<Note>();
    @FXML
    PasswordField displayPassword_txt = new PasswordField();
    @FXML
    Button enterBtn = new Button();
    private ObservableList<Note> noteList;
    private User currentUser;
    private LinkedList<Note> notes;
    public void do_Logout(@NotNull ActionEvent e) throws IOException {
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"LoginScreen.fxml");
    }
    public void do_Exit(ActionEvent e) throws IOException {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayPassword_txt.setVisible(false);
        enterBtn.setVisible(false);
        currentUser = FileManager.get_CurrentUser();
        usernameLabel.setText("Hello "+currentUser.UserName());
        notes = currentUser.getFolder().getAllNotes();
        noteList = FXCollections.observableArrayList(currentUser.getFolder().getAllNotes());
        noteListView.getItems().addAll(noteList);
    }
    public void goToCreatePage(ActionEvent e) throws IOException {
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"CreationPage.fxml");

    }
    public void deleteNote(ActionEvent e) throws IOException {
        if(noteListView.getSelectionModel().getSelectedItem() == null)return;
        int index = noteListView.getSelectionModel().getSelectedIndex();
        noteList.remove(index);
        noteListView.getItems().remove(index);
        currentUser.getFolder().remove(index);
        FileManager.setCurrentUser(currentUser);
    }
    public void Go_Display(ActionEvent e) throws IOException {
        if(noteListView.getSelectionModel().getSelectedItem() == null) return;
        Secured_Note currentNote = (Secured_Note) noteListView.getSelectionModel().getSelectedItem();
        if(currentNote.getPassword().isEmpty() || currentNote.getPassword().equals(Encryption.hash(""))){
            displayPassword_txt.setVisible(false);
            enterBtn.setVisible(false);
            Do_Display(e,currentNote);
            return;
        }
        displayPassword_txt.setVisible(true);
        enterBtn.setVisible(true);
    }
    public void Do_Display(ActionEvent e , Secured_Note currentNote) throws IOException {
        FileManager.setCurrentNote(currentNote);
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"DisplayPage.fxml");
    }
    public void Do_Search(ActionEvent e) throws IOException {
        if(SearchBar_txt.getText().equals("") || SearchBar_txt.getText().isEmpty())return;
        int idx = 0;
        while(idx < notes.size()){
            Secured_Note note = (Secured_Note) notes.get(idx);
            System.out.println(note.getName());
            if(note.getName().equals(SearchBar_txt.getText())){
                noteListView.getSelectionModel().select(idx);
                Go_Display(e);
                return;
            }
            idx++;
        }
        search_Msg.setText("Can't find this note ,Please try again");
    }
    public void check_Note_Password (ActionEvent e) throws IOException {
        if(noteListView.getSelectionModel().getSelectedItem() == null) return;
        Secured_Note currentNote = (Secured_Note) noteListView.getSelectionModel().getSelectedItem();
        if(currentNote.getPassword().equals(Encryption.hash(displayPassword_txt.getText()))){
            displayPassword_txt.setVisible(false);
            Do_Display(e,currentNote);
        }
    }

}