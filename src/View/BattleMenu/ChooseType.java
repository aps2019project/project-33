package View.BattleMenu;

import Controller.Client;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseType implements Initializable {
    public Label singlePlayerButton;
    public Label multiPlayerButton;
    public Label backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        singlePlayerButton.setOnMouseClicked(event -> {
            BattleMenuController.type = BattleMenuController.TypeList.SinglePlayer;
            Client.getClient().setCurrentMenu(MenuList.ChooseMode);
        });

        multiPlayerButton.setOnMouseClicked(event -> {
            BattleMenuController.type = BattleMenuController.TypeList.MultiPlayer;
            Client.getClient().setCurrentMenu(MenuList.ChooseMode);
        });

        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.MainMenu);
            BattleMenuController.relax();
        });

    }
}
