package View.Scoreboard;

import Controller.Client.Client;
import Controller.MenuList;
import Model.Account;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {
    public Label backButton;
    public VBox scoreboardPane;
    private ArrayList<Account> accounts = new ArrayList<>();
    private static AnimationTimer animationTimer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnMouseClicked(event -> {
            animationTimer.stop();
            try {
                Client.getClient().changeCurrentMenu(MenuList.MainMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        animationTimer = new AnimationTimer() {
            long last = 0, unit = 1000000000, fps = 10;
            @Override
            public void handle(long now) {
                if(last == 0) last = now;
                if(now > last + unit / fps){
                    scoreboardPane.getChildren().clear();
                    try {
                        accounts = Client.getClient().getAccounts().getAccounts();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    int index = 1;
                    for(Account account : accounts){
                        Label label = new Label();
                        String state;
                        if(account.getState() == Account.State.Online || account.getState() == Account.State.Busy)
                            state = "Online";
                        else
                            state = "Offline";
                        label.setText(index++ + ". " + account.getUsername() + " " + "W: " + account.getNumberOfWins() + " "
                                + "L: " + account.getNumberOfLooses() + " State: " + state);
                        scoreboardPane.getChildren().add(label);
                        if(state.equals("Online")) label.getStyleClass().add("ScoreboardLabelOnline");
                        else label.getStyleClass().add("ScoreboardLabelOffline");
                    }
                }
            }
        };
        animationTimer.start();
    }
}
