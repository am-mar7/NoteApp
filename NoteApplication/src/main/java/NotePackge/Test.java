package NotePackge;

import FileManagement.FileManager;
import FileManagement.User;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        Secured_Note n = new Secured_Note("1","2");
    }
}
//package NotePackge;
//
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.control.ColorPicker;
//import javafx.scene.image.WritableImage;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.stage.Screen;
//import javafx.stage.Stage;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//        import java.io.File;
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;

//public class Sketch implements Serializable {
//
//    private String sketchPath;
//    private transient Canvas sketchCanvas = new Canvas();
//    private List<DrawingInstruction> drawingInstructions;
//    private  double currentX = 0, currentY = 0;
//    private Pane canvasPane;
//    private GraphicsContext gc;
//    boolean isAdded;
//    public Sketch() {
//        drawingInstructions = new ArrayList<>();
//        canvasPane = new Pane();
//        isAdded = false;
//    }
//    public Sketch (String path) {
//        this.sketchPath =  path;
//        drawingInstructions = new ArrayList<>();
//        canvasPane = new Pane();
//        isAdded = false;
//    }
    //    public  void saveSketchToFile( String Dir, String fileName ) {
//        File saveDir = new File(Dir);
//        if(!saveDir.exists()){
//            saveDir.mkdirs();
//        }
//        File file = new File(Dir + fileName);
//        try {
//            WritableImage writableImage = new WritableImage((int) sketchCanvas.getWidth(), (int) sketchCanvas.getHeight());
//            sketchCanvas.snapshot(null, writableImage);
//            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
//            System.out.println("Sketch saved to: " + file.getAbsolutePath());
//        } catch (IOException e) {
//            System.err.println("Error saving file: " + e.getMessage());
//        }
//    }
//    public void draw(){
//        List<DrawingInstruction> currentInstructions= new ArrayList<>();
//        Stage sketchStage = new Stage();
//        Screen screen = Screen.getPrimary();
//        javafx.geometry.Rectangle2D screenBounds = screen.getVisualBounds();
//        sketchStage.setX(screenBounds.getMinX());sketchStage.setY(screenBounds.getMinY());
//        sketchStage.setWidth(screenBounds.getWidth());sketchStage.setHeight(screenBounds.getHeight());
//        sketchStage.setResizable(false);
//        sketchStage.setTitle("Draw a sketch");
//
//        reDraw();
//        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
//        colorPicker.setOnAction(e -> gc.setStroke(colorPicker.getValue()));
//        sketchCanvas.setOnMousePressed(e-> {
//            gc.beginPath();
//            gc.moveTo(e.getX(), e.getY());
//            gc.stroke();
//            currentX = e.getX() ;
//            currentY = e.getY();
//        });
//        sketchCanvas.setOnMouseDragged(e -> {
//            double startX = currentX , startY = currentY , endX = e.getX() , endY = e.getY();// need to updated
//            gc.lineTo(e.getX(), e.getY());
//            gc.stroke();
//            currentInstructions.add(new DrawingInstruction(startX,startY,endX,endY,colorPicker.getValue(),gc.getLineWidth()));
//            System.out.println("Instruction added");
//        });
//
//        Button clearButton = new Button("Clear");
//        clearButton.setOnAction(e -> {
//            gc.clearRect(0, 0, sketchCanvas.getWidth(), sketchCanvas.getHeight());
//            currentInstructions.clear();
//        });
//
//        Button saveButton = new Button("Save");
//        saveButton.setOnAction(e ->{
//            for(DrawingInstruction it :currentInstructions) drawingInstructions.add(it);
//            sketchStage.close();
//        });
////        clearButton.getStyleClass().add("modern-button");
////        saveButton.getStyleClass().add("modern-button");
//        HBox toolBar = new HBox(10, colorPicker, clearButton, saveButton);
//        toolBar.setStyle("-fx-padding: 10;");
//        toolBar.getStyleClass().add("toolbar");
//        BorderPane layout = new BorderPane();
//        layout.setTop(toolBar);
//        layout.setCenter(canvasPane);
//        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());
////        scene.getStylesheets().add("modern-sketch.css"); // External CSS file for styling
//        sketchStage.setScene(scene);
//        sketchStage.show();
////        gc.clearRect(0, 0, sketchCanvas.getWidth(), sketchCanvas.getHeight());
//    }
//    private void reDraw () {
//        canvasPane.setStyle("-fx-background-color: white;"); // White theme background
//        gc = sketchCanvas.getGraphicsContext2D();
//        gc.setStroke(Color.BLACK); // Default stroke color is black
//        gc.setLineWidth(2.0);
//        if(!isAdded)gc.setFill(Color.WHITE);
////        gc.clearRect(0,0,sketchCanvas.getWidth(),sketchCanvas.getHeight());
//        sketchCanvas.widthProperty().bind(canvasPane.widthProperty());
//        sketchCanvas.heightProperty().bind(canvasPane.heightProperty());
//        System.out.println(drawingInstructions.size());
//        System.out.println(sketchCanvas.toString());
//        gc.setStroke(Color.BLACK);gc.setLineWidth(2.0);
//        if(!isAdded)canvasPane.getChildren().add(sketchCanvas) ; isAdded = true;
//        for(DrawingInstruction d : drawingInstructions){
//            gc.setStroke(d.getColor());
//            gc.strokeLine(d.getStartX(),d.getStartY(),d.getEndX(),d.getEndY());
//        }
//    }
//}