package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayDeque;

/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class UndoApplication extends Application {

    private ArrayDeque<UIAction> history = new ArrayDeque<>();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(getContent()));
        stage.show();
    }

    private Parent getContent() {
        var root = new VBox();
        root.setPrefSize(800,600);
        var field = new TextField("");
        field.setFont(Font.font(22));
        var btnAdd = new Button("Add");
        btnAdd.setFont(Font.font(22));
        btnAdd.setOnAction(e -> {
            run(new AddText(field.getText(), root));
        });
        var btnUndo = new Button("Undo");
        btnUndo.setFont(Font.font(22));
        btnUndo.setOnAction(e -> undo());
        root.getChildren().addAll(field, btnAdd, btnUndo);
        return root;
    }

    private void run(UIAction action) {
        history.addLast(action);
        action.run();
    }

    private void undo() {
        if(history.isEmpty()) return;
        //UIAction action =
        history.removeLast().undo();
        //action.undo();
    }

    class AddText implements UIAction {

        String text;
        Pane root;
        Text uiText;

        public AddText(String text, Pane root) {
            this.text = text;
            this.root = root;
        }

        @Override
        public void run() {
            uiText = new Text(text);
            uiText.setFont(Font.font(22));
            root.getChildren().add(uiText);
        }

        @Override
        public void undo() {
            root.getChildren().remove(uiText);
        }
    }

    interface UIAction {
        void run();
        void undo();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
