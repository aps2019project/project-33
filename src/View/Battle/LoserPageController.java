package View.Battle;

import Controller.Client.Client;
import Controller.MenuList;
import View.View;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoserPageController implements Initializable {
    public Label continueButton;
    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        View.addMusic("resources/sfx/lost.m4a", root, false);

        continueButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.MainMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
