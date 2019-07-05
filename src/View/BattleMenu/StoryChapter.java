package View.BattleMenu;

import Controller.Client.Client;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
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
            try {
                BattleMenuController.startGame();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        chapterTwoButton.setOnMouseClicked(event -> {
            BattleMenuController.chapter = BattleMenuController.ChapterList.Two;
            try {
                BattleMenuController.startGame();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        chapterThreeButton.setOnMouseClicked(event -> {
            BattleMenuController.chapter = BattleMenuController.ChapterList.Three;
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
