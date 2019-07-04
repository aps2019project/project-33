package View.CollectionMenu.CollectionMenu;

import Controller.Client;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CollectionMenuController implements Initializable {
    public AnchorPane anchorPane;
    public Label showLabel;
    public Label searchLabel;
    public Label createDeckLabel;
    public Label deleteDeckLabel;
    public Label addToDeckLabel;
    public Label removeFromDeckLabel;
    public Label validateDeckLabel;
    public Label selectDeckLabel;
    public Label showAllDecksLabel;
    public Label showDeckLabel;
    public Label saveLabel;
    public ImageView backButton;
    public ImageView duelystImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().getCollectionMenu().inputCommandLine("exit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveLabel.setOnMouseClicked(event -> {
            try {
                Client.getClient().getCollectionMenu().inputCommandLine("save");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        showLabel.setOnMouseClicked(event -> {
            try {
                Client.getClient().getCollectionMenu().inputCommandLine("show");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        searchLabel.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.CollectionSearch);
        });
    }
}
