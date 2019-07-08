package View.Server;

import Model.Account;
import View.View;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowClientsController implements Initializable {
    public VBox vBox;
    public Label shopLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0, unit = 1000000000, fps = 10;
            @Override
            public void handle(long now) {
                if(last == 0) last = now;
                if(now > last + unit / fps){
                    last = now;
                    vBox.getChildren().clear();
                    ArrayList<Account> accounts = Account.getAccounts();
                    for(Account account : accounts){
                        Label label = new Label();
                        label.setText(account.getUsername() + " State: " + account.getState());
                        if(account.getState() == Account.State.Online)
                            label.getStyleClass().add("OnlineLabel");
                        else
                            label.getStyleClass().add("OfflineLabel");
                        vBox.getChildren().add(label);
                    }
                }
            }
        };
        animationTimer.start();
        shopLabel.setOnMouseClicked(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerView.primaryStage.setScene(new Scene(root));

        });
    }
}
