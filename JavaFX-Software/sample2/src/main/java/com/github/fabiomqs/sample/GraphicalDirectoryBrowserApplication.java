package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class GraphicalDirectoryBrowserApplication extends Application {

    private HBox hBox = new HBox(15);

    private Parent getContent() {
        ScrollPane scrollPane = new ScrollPane(hBox);

        var root = new StackPane(scrollPane);
        root.setPrefSize(800,600);

        setContents(Paths.get("./"));

        return root;
    }

    private void setContents(Path dir) {
        hBox.getChildren().clear();

        if(Paths.get(dir.toAbsolutePath().toString()).getParent() != null) {
            createDirectoryView(
                    Paths.get(dir.toAbsolutePath().toString()).getParent(),
                    "..");
        }

        try {
            Files.walk(dir, 1)
                    .filter(aPath -> Files.isDirectory(aPath))
                    .filter(aPath -> !aPath.getFileName().toString().equals("."))
                    .forEach(contentDir -> {
                        createDirectoryView(contentDir, contentDir.getFileName().toString());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirectoryView(Path directory, String name) {
        var dv = new DirectoryView(directory,name);
        dv.setOnMouseClicked(e -> {
            setContents(dv.directory);
        });
        hBox.getChildren().add(dv);
    }

    class DirectoryView extends VBox {
        Path directory;

        public DirectoryView(Path directory, String name) {

            setSpacing(5);
            setAlignment(Pos.TOP_CENTER);
            this.directory = directory;
            Text text = new Text(name);
            text.setFont(Font.font(24));

//            Rectangle rectangle = new Rectangle(75,50, Color.LIGHTYELLOW);
//            rectangle.setStroke(Color.BLACK);
            Polygon polygon = new Polygon();
            polygon.getPoints().addAll(new Double[]{
                    0.0, 10.0,
                    0.0, 0.0,
                    25.0, 0.0,
                    32.5, 10.0,
                    75.0, 10.0,
                    75.0, 50.0,
                    0.0, 50.0,

            });
            polygon.setFill(Color.web("0xFFA000"));
            polygon.setStroke(Color.BLACK);

            Polygon polygon2 = new Polygon();
            polygon2.getPoints().addAll(new Double[]{
                    15.0, 10.0,
                    90.0, 10.0,
                    75.0, 50.0,
                    0.0, 50.0,

            });
            polygon2.setFill(Color.web("0xFFCA28"));
            polygon2.setStroke(Color.BLACK);
            var stackPane = new StackPane(polygon, polygon2);
            stackPane.setAlignment(Pos.BOTTOM_LEFT);
//            getChildren().addAll(rectangle, text);
            getChildren().addAll(stackPane, text);
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(getContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
