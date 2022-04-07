package com.github.fabiomqs.sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class CanvasApplication extends Application {


    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final double MAX_ATTRACT_DISTANCE = 50;
    private static final double MIN_ATTRACT_DISTANCE = 0.1;

    private static final double FORCE_CONSTANT = 500;

    //keep track position of the mouse
    private double mouseX;
    private double mouseY;

    private GraphicsContext g;

    private List<Particle> particles = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        var scene = new Scene(createContent());
        scene.setOnMouseMoved(e -> {
            // update mouse x,y
            mouseX = e.getX();
            mouseY = e.getY();
        });

        stage.setX(-1200);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }

    private Parent createContent() {
        for (int y = 0; y < HEIGHT / 10; y++) {
            for (int x = 0; x < WIDTH / 10; x++) {
                particles.add(new Particle(x * 10, y * 10, Color.BLUE));
            }
        }

        var canvas = new Canvas(WIDTH, HEIGHT);
        g = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
        var pane = new Pane(canvas);
        pane.setPrefSize(800, 600);
        return pane;
    }

    // what is inside the graphiocs
    //g.fill();
    // what is outside the graphiocs
    //g.stroke();
    private void onUpdate() {
        //clear the canvas in every frame
        g.clearRect(0,0,WIDTH,HEIGHT);
        var mousePos = new Point2D(mouseX, mouseY);
        particles.forEach(p -> {
            p.update(mousePos);
            //set the color
            g.setFill(p.color);
            //set the position width and height
            g.fillOval(p.x , p.y , 2, 2);
        });

    }

    private static class Particle {
        double x;
        double y;
        Color color;

        private double originalX;
        private double originalY;
        private Color originalColor;

        public Particle(double x, double y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.originalX = x;
            this.originalY = y;
            this.originalColor = color;
        }

        void update(Point2D cursosPos) {
            var distance = cursosPos.distance(x, y);
            //if the point is too far from the mouse cursor set its original position
            //color = Color.RED;
            if(distance > MAX_ATTRACT_DISTANCE) {
                //var originalPos = new Point2D(this.originalX, this.originalY);
                //var distanceFromOriginal  = originalPos.distance(x, y);

                if (this.originalX - this.x > 0)
                    this.x += 0.5;
                else if (this.originalX - this.x < 0)
                    this.x -= 0.5;

                if(this.originalY - this.y > 0)
                    this.y += 0.5;
                else if(this.originalY - this.y > 0)
                    this.y -= 0.5;


                //this.x = this.originalX;
                //this.y = this.originalY;
                //this.color = originalColor;
            } else if(distance < MIN_ATTRACT_DISTANCE) {
                this.x = cursosPos.getX();
                this.y = cursosPos.getY();
            } else {
                // calculates the vector ' ----> '
                //       P ----> M
                var vector = cursosPos.subtract(x, y);

                // if the distance is big the attraction is small
                // if the distance is small the attraction is big
                // calculate the scale
                var scaledLength = FORCE_CONSTANT * 1 / distance;

                // adjust the vector ' ----> ' according to the distance
                //       P --> M  or  P ------> M
                vector = vector.normalize().multiply(scaledLength);
                this.x = this.originalX + vector.getX();
                this.y = this.originalY + vector.getY();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

