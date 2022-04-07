package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;



/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class DraggableApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    private Parent createContent() {
        var root = new Pane();
        root.setPrefSize(800, 600);

        Circle c = new Circle(25, 25, 25, Color.YELLOW);
        Rectangle r = new Rectangle(150, 30, Color.RED);
        Label l = new Label("Hello World!");
        l.setFont(Font.font(42));

        c.setTranslateX(50);
        c.setTranslateY(50);
        r.setTranslateX(150);
        r.setTranslateY(50);
        l.setTranslateX(250);
        l.setTranslateY(50);

        root.getChildren().addAll(c, r, l);
        root.getChildren().forEach(this::makeDraggable);
        return root;
    }

    private double startX;
    private double startY;

    private void makeDraggable(Node node) {
        //calculate offset
        node.setOnMousePressed(e-> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        //set values
        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - startX);
            node.setTranslateY(e.getSceneY() - startY);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
