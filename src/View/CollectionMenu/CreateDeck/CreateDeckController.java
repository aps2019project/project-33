package View.CollectionMenu.CreateDeck;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.Server;
import Controller.Server.ServerMassage;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateDeckController implements Initializable {
    public AnchorPane mainPane;
    public TextField nameField;
    public Label createLabel;
    public ImageView duelystImage;
    public ImageView backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.CollectionMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        createLabel.setOnMouseClicked(event -> {
            try {
                ServerMassage serverMassage = Client.getClient().collectionMenuCommand(ClientMassage.CollectionMenuRequest.CreateDeck,
                        null, null, nameField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
