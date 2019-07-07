package View.Server;

import Model.Account;
import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowClientsController implements Initializable {
    public VBox vBox;

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
    }
}
