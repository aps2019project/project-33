package View.BattleMenu;

import Controller.Client.Client;
import Controller.MenuList;
import View.View;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseType implements Initializable {
    public Label singlePlayerButton;
    public Label multiPlayerButton;
    public Label backButton;
    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        singlePlayerButton.setOnMouseClicked(event -> {
            BattleMenuController.type = BattleMenuController.TypeList.SinglePlayer;
            try {
                Client.getClient().changeCurrentMenu(MenuList.ChooseMode);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        multiPlayerButton.setOnMouseClicked(event -> {
            BattleMenuController.type = BattleMenuController.TypeList.MultiPlayer;
            try {
                Client.getClient().changeCurrentMenu(MenuList.ChooseKind);
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
