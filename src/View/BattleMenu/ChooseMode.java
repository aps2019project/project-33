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

public class ChooseMode implements Initializable {
    public Label customGameButton;
    public Label storyButton;
    public Label backButton;
    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        storyButton.setOnMouseClicked(event -> {
            BattleMenuController.mode = BattleMenuController.ModeList.Story;
            try {
                Client.getClient().changeCurrentMenu(MenuList.StoryChapters);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        customGameButton.setOnMouseClicked(event -> {
            BattleMenuController.mode = BattleMenuController.ModeList.Custom;
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
