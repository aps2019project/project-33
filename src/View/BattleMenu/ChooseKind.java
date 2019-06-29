package View.BattleMenu;

import Controller.Client;
import Controller.MenuList;
import Controller.Menus.BattleMenu;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
            BattleMenuController.startGame();
        });

        kindTwoButton.setOnMouseClicked(event -> {
            BattleMenuController.kind = BattleMenuController.KindList.HoldFlag;
            BattleMenuController.startGame();
        });

        kindThreeButton.setOnMouseClicked(event -> {
            BattleMenuController.kind = BattleMenuController.KindList.TakeHalfOfFlags;
            BattleMenuController.startGame();
        });

        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.MainMenu);
            BattleMenuController.relax();
        });

    }
}
