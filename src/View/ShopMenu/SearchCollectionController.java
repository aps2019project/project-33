package View.ShopMenu;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchCollectionController implements Initializable {
    public ImageView backButton;
    public Label searchButton;
    public TextField nameField;
    public AnchorPane searchCollection;
    public AnchorPane mainPane;
    public AnchorPane blurPane;

    public static ServerMassage serverMassage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.ShopMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        searchButton.setOnMouseClicked(event -> {
            try {
                serverMassage = Client.getClient().shopMenuCommand(ClientMassage.ShopMenuRequest.SearchInCollection,
                        null, nameField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            searchCollection.getChildren().remove(searchButton);
            searchCollection.getChildren().remove(nameField);
            VBox cardsVBox = null;
            try {
                cardsVBox = Graphic.createCards(serverMassage.getCollectionItems());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            cardsVBox.setLayoutX(100);
            cardsVBox.setLayoutY(100);
            searchCollection.getChildren().add(cardsVBox);
        });
    }
}
