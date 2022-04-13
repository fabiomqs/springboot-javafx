package com.github.fabiomqs.numemory;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class NuMemoryApplication extends Application {

    private final long DURATION_SECONDS = 6;

    private ScheduledExecutorService timerThread = Executors.newSingleThreadScheduledExecutor();

    private VBox root;

    private Pane tilePane;

    private List<TileView> tileSequence = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        timerThread.shutdownNow();
    }

    private Parent createContent() {
        root = new VBox();
        root.setPrefSize(1000, 600 + 100);


        var button = new Button("Start");
        button.setOnAction(e -> startGame());
        
        root.getChildren().addAll(new Pane(), button);
        return root;
    }

    private void startGame() {
        var pane = populateGrid();
        root.getChildren().set(0, pane);
        timerThread.schedule(() -> {
            pane.getChildren().stream()
                    .map(n -> (TileView)n)
                    .forEach(TileView::hide);
        }, DURATION_SECONDS, TimeUnit.SECONDS);
    }

    private Pane populateGrid() {
        tilePane = new Pane();

        var random = new Random();

        HashMap<Vec, Vec> map = new HashMap<>();

        for(int i = 0; i<=9;) {
            Vec vec = new Vec(random.nextInt(1000 / 50) * 50, random.nextInt(600 / 50) * 50);
            if(!map.containsKey(vec)) {
                var tile = new TileView(Integer.toString(i));
                map.put(vec, vec);
                tile.setTranslateX(vec.x);
                tile.setTranslateY(vec.y);
                tile.setOnMouseClicked(e -> {
                    if(tileSequence.isEmpty()) {
                        System.out.println("Sequence Empty: Game Over!!!");
                        return;
                    }
                    var correctTile = tileSequence.remove(0);
                    if(tile == correctTile) {
                        tile.show();

                    } else {
                        tileSequence.clear();
                        System.out.println("Fail: Game Over!!!");
                    }
                });
                tilePane.getChildren().add(tile);
                tileSequence.add(tile);
                i++;
            }

        }
        return tilePane;
    }

    private static class Vec {
        int x;
        int y;

        public Vec(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Vec obj = (Vec) o;
            if(obj.x == x && obj.y == y) return true;
            return false;
        }

        @Override
        public int hashCode() {
            int result = 3;
            result = result * x;
            result += result * y;
            return result;
        }
    }

    //13:30
    private static class TileView extends StackPane {

        private Text text;

        TileView(String content) {
            var border = new Rectangle(50, 50, null);
            border.setStroke(Color.BLACK);
            border.setStrokeWidth(4);
            border.setStrokeType(StrokeType.INSIDE);
            text = new Text(content);
            text.setFont(Font.font(40));
            getChildren().addAll(border, text);
            setPickOnBounds(true);
        }

        void hide() {
            text.setVisible(false);
        }
        void show() {
            text.setVisible(true);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
