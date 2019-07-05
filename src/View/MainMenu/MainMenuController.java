package View.MainMenu;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public AnchorPane anchorPane;
    public VBox vBox;
    public Label battle;
    public Label shop;
    public Label collection;
    public ImageView duelyst;
    public ImageView logout;
    public Label save;
    public Label scoreboard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collection.setOnMouseClicked(event -> {
            try {
                Client.getClient().mainMenuCommand(ClientMassage.MainMenuRequest.EnterCollectionMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        logout.setOnMouseClicked(event -> {
            try {
                ServerMassage serverMassage = Client.getClient().mainMenuCommand(ClientMassage.MainMenuRequest.LogOut);
                if(serverMassage.getCommand() == ServerMassage.Command.ClearAuthToken)
                    Client.getClient().setAuthToken(null);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        shop.setOnMouseClicked(event -> {
            try {
                Client.getClient().mainMenuCommand(ClientMassage.MainMenuRequest.EnterShopMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        battle.setOnMouseClicked(event -> {
            try {
                Client.getClient().mainMenuCommand(ClientMassage.MainMenuRequest.EnterBattleMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        save.setOnMouseClicked(event -> {
            try {
                Client.getClient().mainMenuCommand(ClientMassage.MainMenuRequest.Save);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        collection.setOnMouseClicked(event -> {
            try {
                Client.getClient().mainMenuCommand(ClientMassage.MainMenuRequest.EnterCollectionMenu);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        scoreboard.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.Scoreboard);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
