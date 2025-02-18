package org.example.noteapplication;

import FileManagement.FileManager;
import FileManagement.User;
import NotePackge.Image;
import NotePackge.Secured_Note;
import NotePackge.Sketch;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CreationPageController implements Initializable {
    @FXML
    private TextField noteTitle_txt ;
    @FXML
    private PasswordField password_txt;
    @FXML
    private TextArea noteContent_txt;
    @FXML
    private Label saveMsg = new Label();
    @FXML
    private ListView<ImageView> imagesListView= new ListView<ImageView>();
    private LinkedList<Image> images = new LinkedList<Image>();
    private Sketch sketch ;
    private User currentUser;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = FileManager.get_CurrentUser();
    }
    public void Try_Save(ActionEvent e) throws IOException{
        int idx = 0;
        while (idx < currentUser.getFolder().getAllNotes().size()){
            if(currentUser.getFolder().getAllNotes().get(idx++).getName().equals(noteTitle_txt.getText())){
                saveMsg.setText("This note is already existed");
                return;
            }
         } // chicking that note title is not used already
        if(noteTitle_txt.getText().isEmpty())
            saveMsg.setText("The note title can not be empty");
        if(noteContent_txt.getText().isEmpty())
            saveMsg.setText("The note content can not be empty");
        if(noteContent_txt.getText().isEmpty() || noteTitle_txt.getText().isEmpty())
            return;
        // do save
        Secured_Note note = new Secured_Note(noteTitle_txt.getText(), password_txt.getText());
        Scanner scanner = new Scanner(noteContent_txt.getText());
        idx = 0;
        while (scanner.hasNext())
            note.addContent(scanner.nextLine());
        idx = 0;
        while(idx < images.size())
            note.insertImage(images.get(idx++));

        if(sketch != null)note.attachSketch(sketch);

        currentUser.getFolder().add(note);
        FileManager.addNoteINSystem(note,currentUser.UserName());
        FileManager.setCurrentUser(currentUser);
        // go back to home
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"HomePage.fxml");
    }
    public void Do_Cancel(ActionEvent e) throws IOException {
        // go back home without saving
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"HomePage.fxml");
    }
    public void selectImage(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file == null) return;

        Image image = new Image(file.getAbsolutePath());
        images.add(image);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            javafx.scene.image.Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);

            ImageView imageView = new ImageView(fxImage);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(imagesListView.getWidth());
            imageView.setFitHeight(160);
            imagesListView.getItems().add(imageView);
            imagesListView.setCellFactory(param -> new ListCell<ImageView>() {
                @Override
                protected void updateItem(ImageView item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        setGraphic(item);
                    }
                }
            });
            imagesListView.setVisible(true);
            imagesListView.refresh();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //image list view controller
    public void view_Image(ActionEvent e) throws IOException {
        if(imagesListView.getSelectionModel().getSelectedItem() == null)return;
        int index = imagesListView.getSelectionModel().getSelectedIndex();
        Image.ShowImage(images.get(index));
    }
    public void delete_Image(ActionEvent e) throws IOException {
        if(imagesListView.getSelectionModel().getSelectedItem() == null)return;
        int index = imagesListView.getSelectionModel().getSelectedIndex();
        images.remove(index);
        imagesListView.getItems().remove(index);
    }
    public void draw_Sketch(ActionEvent event) throws IOException {
        if(sketch == null)sketch = new Sketch();
        sketch.draw();
    }

    // Save canvas to a static path

}
