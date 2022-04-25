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

public class LetterAnimationApplication2 extends Application {

    private static final String ABC = "abcdefghijklmnopqrstuvwxyz";

    private Parent getContent() {
        var root = new Pane();
        root.setCache(true);
        root.setCacheHint(CacheHint.SPEED);

        for(int j = 0; j < ABC.length();j++) {
            var ch = ABC.charAt(j) + "";
            double x = ((j > 13 ? (j - 13) : j) * 130) + 30;
            double y = j > 13 ? 350 : 150;

            var letters = new RotatedLetter(ch, x , y);
            RotateTransition rt = new RotateTransition(Duration.seconds(0.2), letters);

            rt.setCycleCount(Integer.MAX_VALUE);
            rt.setToAngle(10);
            rt.play();

            root.getChildren().add(letters);
        }






        return root;
    }

    class RotatedLetter extends Parent {
        RotatedLetter(String str, double x, double y) {
            for(int i = 0; i < 36;i++) {
                    Text letter = new Text(str.toUpperCase());
                    letter.setFont(Font.font(108));
                    letter.setTranslateX(x);
                    letter.setTranslateY(y);

                    var angle = 360 / 36 * i;

                    letter.setRotate(angle);
                    getChildren().add(letter);

                }

        }

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
