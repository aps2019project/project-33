package View.Battle;

import Controller.Client.Client;
import Controller.MenuList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinnerPageController implements Initializable {
    public Label continueButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        continueButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.MainMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
