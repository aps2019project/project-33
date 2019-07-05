package View.BattleMenu;

import Controller.Client.Client;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseKind implements Initializable {
    public Label kindOneButton;
    public Label kindThreeButton;
    public Label kindTwoButton;
    public Label backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kindOneButton.setOnMouseClicked(event -> {
            BattleMenuController.kind = BattleMenuController.KindList.KillEnemyHero;
            try {
                BattleMenuController.startGame();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        kindTwoButton.setOnMouseClicked(event -> {
            BattleMenuController.kind = BattleMenuController.KindList.HoldFlag;
            try {
                BattleMenuController.startGame();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        kindThreeButton.setOnMouseClicked(event -> {
            BattleMenuController.kind = BattleMenuController.KindList.TakeHalfOfFlags;
            try {
                BattleMenuController.startGame();
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

    }
}
