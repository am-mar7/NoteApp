package NotePackge;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.Serializable;

public class Sketch implements Serializable {

    private String sketchName;
    private String sketchPath;
    private transient Canvas sketchCanvas;
    private  WritableImage sketchImage;
    private transient Canvas canvas;
    public Sketch() {
    }
    public Sketch (String path) {
        this.sketchPath =  path;
    }
    public void setPath (String path) {
        this.sketchPath =  path;
    }
    public String getPath () {
        return this.sketchPath;
    }
    public void draw(){
        // create Stage to draw on
        Stage sketchStage = new Stage();
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D screenBounds = screen.getVisualBounds();
        sketchStage.setX(screenBounds.getMinX());sketchStage.setY(screenBounds.getMinY());
        sketchStage.setWidth(screenBounds.getWidth());sketchStage.setHeight(screenBounds.getHeight());
        sketchStage.setResizable(false);
        sketchStage.setTitle("Draw a sketch");

        canvas = new Canvas(screenBounds.getWidth(), screenBounds.getHeight());
        Pane canvasPane = new Pane();
        if(sketchCanvas == null)sketchCanvas = new Canvas(screenBounds.getWidth(), screenBounds.getHeight());
        canvas = sketchCanvas;
        canvasPane.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // color picker
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(e -> gc.setStroke(colorPicker.getValue()));

        canvas.setOnMousePressed(e-> {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });
        canvas.setOnMouseDragged(e -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            sketchCanvas = null;
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e ->{
            sketchCanvas = canvas;
            sketchStage.close();
        });
        // preparing layOut
        HBox toolBar = new HBox(10, colorPicker, clearButton, saveButton);
        toolBar.setStyle("-fx-padding: 10;");
        toolBar.getStyleClass().add("toolbar");
        BorderPane layout = new BorderPane();
        layout.setTop(toolBar);
        layout.setCenter(canvasPane);
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());
//        scene.getStylesheets().add("modern-sketch.css"); // External CSS file for styling
        sketchStage.setScene(scene);
        sketchStage.show();

    }
}