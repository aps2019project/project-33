package View.BattleMenu;

import Controller.Client.Client;
import Controller.MenuList;
import View.View;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseKind implements Initializable {
    public Label kindOneButton;
    public Label kindThreeButton;
    public Label kindTwoButton;
    public Label backButton;
    public TextField numberOfFlagField;
    public Label applyButton;
    public AnchorPane root;
    public TextField maximumTimeOfTurnField;
    public Label applyTimeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        kindOneButton.setOnMouseClicked(event -> {
            BattleMenuController.kind = BattleMenuController.KindList.KillEnemyHero;
            BattleMenuController.numberOfFlag = 0;
            try {
                if(BattleMenuController.type == BattleMenuController.TypeList.SinglePlayer)
                    BattleMenuController.startGame();
                else
                    Client.getClient().changeCurrentMenu(MenuList.ChooseSecondPlayer);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        kindTwoButton.setOnMouseClicked(event -> {
            BattleMenuController.kind = BattleMenuController.KindList.HoldFlag;
            BattleMenuController.numberOfFlag = 1;
            try {
                if(BattleMenuController.type == BattleMenuController.TypeList.SinglePlayer)
                    BattleMenuController.startGame();
                else
                    Client.getClient().changeCurrentMenu(MenuList.ChooseSecondPlayer);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        kindThreeButton.setOnMouseClicked(event -> {
            BattleMenuController.kind = BattleMenuController.KindList.TakeHalfOfFlags;
            try {
                if(BattleMenuController.type == BattleMenuController.TypeList.SinglePlayer)
                    BattleMenuController.startGame();
                else
                    Client.getClient().changeCurrentMenu(MenuList.ChooseSecondPlayer);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.MainMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            BattleMenuController.relax();
        });

        applyButton.setOnMouseClicked(event -> {
            String string = numberOfFlagField.getText();
            numberOfFlagField.clear();
            if(!string.matches("[\\d]+")) return;
            BattleMenuController.numberOfFlag = Integer.parseInt(string);
        });

        applyTimeButton.setOnMouseClicked(event -> {
            String string = maximumTimeOfTurnField.getText();
            maximumTimeOfTurnField.clear();
            if(!string.matches("[\\d]+")) return;
            BattleMenuController.maximumTimeOfTurn = Integer.parseInt(string);
        });
    }
}
