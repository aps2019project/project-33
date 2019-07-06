package View.ShopMenu;

import Controller.Client.Client;
import Controller.MenuList;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
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
        backButton.setOnMouseClicked(event -> {
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShopMenu);
        });

        searchButton.setOnMouseClicked(event -> {
            Client.getClient().getShopMenu().inputCommandLine("search collection " + nameField.getText());
            searchCollection.getChildren().remove(searchButton);
            searchCollection.getChildren().remove(nameField);
            VBox cardsVBox = null;
            try {
                cardsVBox = Graphic.createCards(Client.getClient().getResultOfSearch());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            cardsVBox.setLayoutX(100);
            cardsVBox.setLayoutY(100);
            searchCollection.getChildren().add(cardsVBox);
        });
    }
}
