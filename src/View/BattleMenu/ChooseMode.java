package View.BattleMenu;

import Controller.Client;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseMode implements Initializable {
    public Label customGameButton;
    public Label storyButton;
    public Label backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        storyButton.setOnMouseClicked(event -> {
            BattleMenuController.mode = BattleMenuController.ModeList.Story;
            Client.getClient().setCurrentMenu(MenuList.StoryChapters);
        });

        customGameButton.setOnMouseClicked(event -> {
            BattleMenuController.mode = BattleMenuController.ModeList.Custom;
            Client.getClient().setCurrentMenu(MenuList.ChooseKind);
        });

        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.MainMenu);
            BattleMenuController.relax();
        });

    }
}
