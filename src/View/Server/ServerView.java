package View.Server;

import Controller.Client.Client;
import Controller.MenuList;
import Controller.Server.Server;
import View.Battle.BattleController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerView extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ShowClients");
        Parent root = FXMLLoader.load(getClass().getResource("ShowClients.fxml"));
        primaryStage.setScene(new Scene(root));
        ServerView.primaryStage = primaryStage;
        primaryStage.show();
    }
}
