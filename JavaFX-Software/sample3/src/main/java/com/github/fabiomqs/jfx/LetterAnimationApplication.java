package com.github.fabiomqs.jfx;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LetterAnimationApplication extends Application {

    private static final String ABC = "abcdefghijklmnopqrstuvwxyz";

    private Parent getContent() {
        var root = new Pane();
        root.setCache(true);
        root.setCacheHint(CacheHint.SPEED);

        for(int j = 0; j < ABC.length();j++) {
            var ch = ABC.charAt(j) + "";

            for(int i = 0; i < 36;i++) {
                Text letter = new Text(ch.toUpperCase());
                letter.setFont(Font.font(108));
                letter.setTranslateX(((j > 13 ? (j - 13) : j) * 120) + 30);

                letter.setTranslateY(j > 13 ? 350 : 150);

                var angle = 360 / 36 * i;
                RotateTransition rt = new RotateTransition(Duration.seconds(i * 0.3), letter);
                //rt.setDelay(Duration.seconds(i * 0.05));
                rt.setCycleCount(Integer.MAX_VALUE);
                rt.setToAngle(angle);
                rt.play();

                //letter.setRotate(angle);
                root.getChildren().add(letter);

            }
        }

        return root;
    }


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(getContent());
        stage.setWidth(2000);
        stage.setHeight(900);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
