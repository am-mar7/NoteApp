package org.example.noteapplication;

import FileManagement.FileManager;
import FileManagement.User;
import NotePackge.Image;
import NotePackge.Secured_Note;
import NotePackge.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

import NotePackge.Note;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class DisplayPageController  implements Initializable {
    private Secured_Note currentNote;
    @FXML
    Label Title_lbl = new Label();
    @FXML
    TextArea noteContent_txt = new TextArea();
    @FXML
    ListView<ImageView> noteImages_lv = new ListView<ImageView>();
    @FXML
    Label saveMsg = new Label();
    private User currentUser;

    private Sketch sketch ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = FileManager.get_CurrentUser();
        currentNote = FileManager.get_CurrentNote();
        sketch = currentNote.getSketch();
        Title_lbl.setText(currentNote.getName());
        int idx = 0;
        while (idx < currentNote.getContent().size()) {
            noteContent_txt.appendText(currentNote.getContent().get(idx) + "\n");
            idx++;
        }
        idx = 0;
        while (idx < currentNote.getImages().size()) {
            Image image = currentNote.getImages().get(idx);
            File file = new File(image.getPath());
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                javafx.scene.image.Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
                ImageView imageView = new ImageView(fxImage);
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(160);
                imageView.setFitHeight(160);
                noteImages_lv.getItems().add(imageView);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            idx++;
        }
    }
    public void Go_Back(ActionEvent e) throws IOException {
        // back without saving any chnages
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"HomePage.fxml");
    }
    public void go_Select(ActionEvent e) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file == null) return;
        Image image = new Image(file.getAbsolutePath());
        currentNote.getImages().add(image);
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
            javafx.scene.image.Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);

            ImageView imageView = new ImageView(fxImage);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(190);
            imageView.setFitHeight(160);
            noteImages_lv.getItems().add(imageView);
            noteImages_lv.setCellFactory(param -> new ListCell<ImageView>() {
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
            noteImages_lv.setVisible(true);// making sure it does appear
            noteImages_lv.refresh();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    public void do_Save(ActionEvent e) throws IOException {
        // check content is not empty
        if(noteContent_txt.getText().isEmpty()) {
            saveMsg.setText("The note content can not be empty");
            return;
        }
        Scanner scanner = new Scanner(noteContent_txt.getText());
        LinkedList<String> content = new LinkedList<String>();
        while (scanner.hasNextLine()){
            content.add(scanner.nextLine());
        }
        currentNote.setContent(content);
        if(sketch != null)  currentNote.attachSketch(sketch);
        int idx = 0;
        while (idx < currentUser.getFolder().getAllNotes().size()){
            if(currentUser.getFolder().getAllNotes().get(idx).getName().equals(currentNote.getName()))
                break;
            idx++;
        }
        currentUser.getFolder().update(currentNote,idx);
        FileManager.setCurrentNote(currentNote);
        FileManager.setCurrentUser(currentUser);
        // go back to home page
        LocalController lc =  new LocalController();
        lc.SwitchPage(e,"HomePage.fxml");
    }
    // list image view controllers
    public void view_Image(ActionEvent e) throws IOException {
        if(noteImages_lv.getSelectionModel().getSelectedItem() == null)return;
        int index = noteImages_lv.getSelectionModel().getSelectedIndex();
        Image showedImage = currentNote.getImages().get(index);
        Image.ShowImage(showedImage);
    }
    public void delete_Image(ActionEvent e) throws IOException {
        if(noteImages_lv.getSelectionModel().getSelectedItem() == null)return;
        int index = noteImages_lv.getSelectionModel().getSelectedIndex();
        noteImages_lv.getItems().remove(index);
        currentNote.getImages().remove(index);
    }
    public void draw_Sketch(ActionEvent event) throws IOException {
        if(sketch == null)sketch = new Sketch();
        sketch.draw();
    }
}