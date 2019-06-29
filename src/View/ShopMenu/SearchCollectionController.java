package View.ShopMenu;

import Controller.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchCollectionController implements Initializable {
    public ImageView backButton;
    public Label searchButton;
    public TextField nameField;
    public AnchorPane searchCollection;
    public AnchorPane mainPane;
    public AnchorPane blurPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchButton.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("search collection " + nameField.getText());
        });
    }
}
