package View.ShopMenu;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchShopController implements Initializable {

    public AnchorPane mainPane;
    public AnchorPane blurPane;
    public AnchorPane searchCollection;
    public TextField nameField;
    public Label searchButton;
    public ImageView backButton;

    public static ServerMassage serverMassage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchButton.setOnMouseClicked(event -> {
            try {
                serverMassage = Client.getClient().shopMenuCommand(ClientMassage.ShopMenuRequest.SearchInShop,
                        null, nameField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.ShopMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
