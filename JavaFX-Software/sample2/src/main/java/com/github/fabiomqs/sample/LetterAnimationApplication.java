package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LetterAnimationApplication extends Application {

    private Parent getContent() {
        var root = new Pane();

        for(int i = 1; i<= 3;i++) {
            Text letter = new Text("A");
            letter.setFont(Font.font(72));
            letter.setTranslateX(150);
            letter.setTranslateX(150);

            var angle= 360 / 36 * i;

            root.getChildren().add(letter);
        }

        return root;
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(getContent()));
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
