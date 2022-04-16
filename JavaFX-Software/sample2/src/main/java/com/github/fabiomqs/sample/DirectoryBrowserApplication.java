package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class DirectoryBrowserApplication  extends Application {

    private Parent getContent() {
        var root = new VBox();
        var listView = new ListView<String>();
        root.setPrefSize(800,600);
        var btnBrowse = new Button("Browse...");
        //btnBrowse.setFont(Font.font(22));
        btnBrowse.setOnAction(e -> {
            //FileChooser fileChooser = new FileChooser();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setInitialDirectory(Paths.get(System.getProperty("user.dir")).toFile());
            var file = directoryChooser.showDialog(null);
            if(file != null) {
                var startDir = file.toPath();
                listView.getItems().clear();
                try {
                    Files.walk(startDir)
                            .filter(aPath -> Files.isDirectory(aPath))
                            .forEach(dir -> {
                                listView.getItems().add(dir.toString());
                            });
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        root.getChildren().addAll(btnBrowse, listView);
        return root;
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
