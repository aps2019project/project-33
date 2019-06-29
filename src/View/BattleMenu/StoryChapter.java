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

public class StoryChapter implements Initializable {
    public Label backButton;
    public Label chapterOneButton;
    public Label chapterTwoButton;
    public Label chapterThreeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chapterOneButton.setOnMouseClicked(event -> {
            BattleMenuController.chapter = BattleMenuController.ChapterList.One;
            BattleMenuController.startGame();
        });

        chapterTwoButton.setOnMouseClicked(event -> {
            BattleMenuController.chapter = BattleMenuController.ChapterList.Two;
            BattleMenuController.startGame();
        });

        chapterThreeButton.setOnMouseClicked(event -> {
            BattleMenuController.chapter = BattleMenuController.ChapterList.Three;
            BattleMenuController.startGame();
        });

        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.MainMenu);
            BattleMenuController.relax();
        });
    }
}
