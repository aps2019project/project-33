package View.BattleMenu;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnswerToGameController implements Initializable {
    public Label acceptButton;
    public Label rejectButton;
    public Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acceptButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().answerToGame(ClientMassage.BattleMenuRequest.AcceptGame);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        rejectButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().answerToGame(ClientMassage.BattleMenuRequest.RejectGame);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}