package View.BattleMenu;

import Controller.Client.Client;
import Controller.MenuList;
import View.View;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseSecondPlayerController implements Initializable {
    public Label backButton;
    public Label playGame;
    public TextField textField;
    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.MainMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        playGame.setOnMouseClicked(event -> {
            BattleMenuController.secondPlayerUserName = textField.getText();
            textField.clear();
            try {
                BattleMenuController.startMultiPlayerGame();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
