package com.github.fabiomqs.sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class VectorFieldApplication extends Application {

    //keep track position of the mouse
    private double mouseX;
    private double mouseY;

    private List<Arrow> arrows = new ArrayList<>();
    private Circle circle;

    @Override
    public void start(Stage stage) throws Exception {
        var scene = new Scene(createContent());
        scene.setOnMouseMoved(e -> {
            // update mouse x,y
            mouseX = e.getX();
            mouseY = e.getY();
        });
        stage.setScene(scene);
        stage.show();
    }

    private Parent createContent() {
        var root = new Pane();
        root.setPrefSize(800,600);

        for(int y = 0; y < 600  / 24; y++) {
            for(int x = 0; x < 800  / 50; x++) {
                var a = new Arrow();
                a.setTranslateX(x * 50);
                a.setTranslateY(y * 24);
                arrows.add(a);
                root.getChildren().add(a);
            }
        }

        circle = new Circle(10, Color.RED);
        root.getChildren().add(circle);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
        return root;
    }

    private void onUpdate() {
        arrows.forEach(a -> {
            circle.setCenterX(mouseX);
            circle.setCenterY(mouseY);
            var vx = mouseX - a.getTranslateX();
            var vy = mouseY - a.getTranslateY();

            double angle = Math.toDegrees(Math.atan2(vy, vx));

            a.setRotate(angle);
        });
    }

    private static class Arrow extends Parent {
        Arrow() {

            double scale = 1.5;

            //Line(startX, startY, endX, endY)
            //startX - the horizontal coordinate of the start point of the line segment
            //startY - the vertical coordinate of the start point of the line segment
            //endX - the horizontal coordinate of the end point of the line segment
            //endY - the vertical coordinate of the end point of the line segment
            var lineTop = new Line(20 * scale, 5 * scale, 17.5 * scale, 2.5 * scale);
            var lineMid = new Line(0 * scale, 5 * scale, 20 * scale, 5 * scale);
            var lineBot = new Line(20 * scale, 5 * scale, 17.5 * scale, 7.5 * scale);

            getChildren().addAll(lineTop, lineMid, lineBot);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
