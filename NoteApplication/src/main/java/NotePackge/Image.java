package NotePackge;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.Serializable;

public class Image implements Serializable {

    private String imagePath;
    public Image (String path) {
        this.imagePath = path;
    }
    public String getPath() {
        return imagePath;
    }
    public static void ShowImage(Image showedimage) {
        File file = new File(showedimage.getPath());
        javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        imageView.setFitWidth(bounds.getWidth());imageView.setFitHeight(bounds.getHeight());
        VBox vbox = new VBox(imageView);vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, bounds.getWidth(),bounds.getHeight());
        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());
        Stage stage = new Stage(); stage.setTitle("Image Viewer");
        stage.setWidth(bounds.getWidth());stage.setHeight(bounds.getHeight());
        stage.setScene(scene); stage.show();
    }
}