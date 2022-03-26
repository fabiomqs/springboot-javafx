package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class StageApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setX(-1200);
        stage.setY(0);

        //stage.setMaxHeight(720);
        //stage.setMaxWidth(1280);

        stage.setOpacity(0.5);

        //stage.centerOnScreen();

        stage.setResizable(false);

        var image = new WritableImage(32, 32);
        for(int y = 0; y< 32; y++) {
            for(int x = 0; x< 32; x++) {
                image.getPixelWriter().setColor(x, y, Color.RED);
            }
        }

        stage.getIcons().add(image);

        Button btn = new Button("Close");
        btn.setOnAction(e -> stage.close());

        stage.setScene(new Scene(new Pane(btn), 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
