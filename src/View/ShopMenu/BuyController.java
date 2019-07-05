package View.ShopMenu;

import Controller.Client.Client;
import Controller.MenuList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BuyController implements Initializable {
    public AnchorPane mainPane;
    public AnchorPane blurPane;
    public AnchorPane searchCollection;
    public TextField nameField;
    public Label searchButton;
    public ImageView backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnMouseClicked(event -> {
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShopMenu);
        });

        searchButton.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("search " + nameField.getText());

        });
    }
}
