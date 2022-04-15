package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.function.Predicate;

/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class InputValidationApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(getContent()));
        stage.show();
    }

    private Parent getContent() {
        var field = new ValidatingTextField(input -> input.contains("a"));
        field.setFont(Font.font(22));
        var btn = new Button("Submit");
        btn.setFont(Font.font(22));
        btn.disableProperty().bind(field.isValidProperty.not());
        return new VBox(field, btn);
    }

    private static class ValidatingTextField extends TextField {
        private final Predicate<String> validation;
        private BooleanProperty isValidProperty = new SimpleBooleanProperty();

        public ValidatingTextField(Predicate<String> validation) {
            this.validation = validation;
            textProperty().addListener((observable, oldText, newText) -> {
                System.out.println(observable);
                isValidProperty.set(validation.test(newText));
            });
            isValidProperty.set(validation.test(""));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
