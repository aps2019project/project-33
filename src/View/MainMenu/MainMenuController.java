package View.MainMenu;

import Controller.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collection.setOnMouseClicked(event -> {

            try {
                Client.getClient().getMainMenu().inputCommandLine("enter collection");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        logout.setOnMouseClicked(event -> {
            try {
                Client.getClient().getMainMenu().inputCommandLine("logout");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        shop.setOnMouseClicked(event -> {
            try {
                Client.getClient().getMainMenu().inputCommandLine("enter shop menu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        battle.setOnMouseClicked(event -> {
            try {
                Client.getClient().getMainMenu().inputCommandLine("enter battle");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        save.setOnMouseClicked(event -> {
            try {
                Client.getClient().getMainMenu().inputCommandLine("save");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        collection.setOnMouseClicked(event -> {
            try {
                Client.getClient().getMainMenu().inputCommandLine("enter collection");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
