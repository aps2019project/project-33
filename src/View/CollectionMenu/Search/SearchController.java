package View.CollectionMenu.Search;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    public ImageView backButton;
    public Label searchLabel;
    public TextField nameField;
    public static ServerMassage serverMassage;

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

        searchLabel.setOnMouseClicked(event -> {
            try {
                serverMassage = Client.getClient().collectionMenuCommand(ClientMassage.CollectionMenuRequest.Search,
                        null, null, nameField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}