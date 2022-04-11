package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * @author Fabio Marques Moreira (fabiomqs@gmail.com)
 */
public class LoginApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane pane = new AnchorPane();
        pane.setPrefSize(400, 300);

        TextField txUser = new TextField();
        txUser.setPromptText("username");

        PasswordField txPass = new PasswordField();
        txPass.setPromptText("password");

        Button btLogin = new Button("Log In");
        Button btExit = new Button("Exit");

        pane.getChildren().addAll(txUser, txPass, btLogin, btExit);
        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue 0%, silver 100%);");

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();


        txUser.setLayoutX(
                (pane.getWidth() - txUser.getWidth()) / 2);
        txUser.setLayoutY(50);
        txPass.setLayoutX(
                (pane.getWidth() - txPass.getWidth()) / 2);
        txPass.setLayoutY(100);
        btLogin.setLayoutX(
                (pane.getWidth() - btLogin.getWidth()) / 2);
        btLogin.setLayoutY(150);
        btExit.setLayoutX(
                (pane.getWidth() - btExit.getWidth()) / 2);
        btExit.setLayoutY(200);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
