package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class LayoutApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setX(-1200);
        stage.setY(0);
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    private Parent createContent() {
        //var root = new VBox();
        //var root = new HBox();
        //root.setAlignment(Pos.CENTER);

        //five regions, top, bottom, center, left, right
        //var root = new BorderPane();
//        root.setTop(btn1);
//        root.setBottom(btn2);
//        root.setCenter(btn3);
//        root.setLeft(btn4);
//        root.setRight(btn5);

        //var root = new StackPane();
        var root = new Pane();
        root.setPrefSize(800, 600);

        var bg = new Rectangle(150,40, Color.BLUE);
        var text = new Text("Hello world");


        Button btn1 = new Button("Button 1");
        btn1.setFont(Font.font(18));
        Button btn2 = new Button("Button 2");
        btn2.setFont(Font.font(18));
        Button btn3 = new Button("Button 3");
        btn3.setFont(Font.font(18));
        Button btn4 = new Button("Button 4");
        btn4.setFont(Font.font(18));
        Button btn5 = new Button("Button 5");
        btn5.setFont(Font.font(18));


        root.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);

        //For pane
        btn1.setLayoutX(50);
        btn1.setLayoutY(50);

        btn2.setLayoutX(100);
        btn2.setLayoutY(100);

        btn3.setLayoutX(150);
        btn3.setLayoutY(150);

        btn4.setLayoutX(200);
        btn4.setLayoutY(200);

        btn5.setLayoutX(250);
        btn5.setLayoutY(250);


        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
