package View.ShopMenu;

import Controller.Client;
import Controller.MenuList;
import Model.CollectionItem.CollectionItem;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowingBuyController implements Initializable {
    public AnchorPane nonBlurAnchor;
    public AnchorPane blurAnchor;
    public AnchorPane mainAnchor;
    public ImageView backButton;
    public Label buyLabel;

    public CollectionItem selectedCollectionItem;

    public static boolean isFirstTime = true;
    public static VBox cardsVbox = new VBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            nonBlurAnchor.getChildren().remove(cardsVbox);
            cardsVbox = Graphic.createCards(Client.getClient().getResultOfSearch());
            nonBlurAnchor.getChildren().add(cardsVbox);
            cardsVbox.setLayoutX(200);
            cardsVbox.setLayoutY(200);
        }
        isFirstTime = false;
        int index = 0;
        for (VBox vBox : Graphic.vBoxes) {
            final int y = index;
            vBox.setOnMouseClicked(event -> {
                selectedCollectionItem = Client.getClient().getResultOfSearch().get(y);
                Graphic.clearShadows(Graphic.vBoxes);
                vBox.getStylesheets().add(Graphic.class.getResource("Card.css").toExternalForm());
                vBox.getStyleClass().add("SelectedCard");
            });
            index++;
        }

        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.ShopMenu);
            isFirstTime = true;
        });

        buyLabel.setOnMouseClicked(event -> {
            if (selectedCollectionItem != null) {
                Client.getClient().getShopMenu().inputCommandLine("buy " + selectedCollectionItem.getName());
                Client.getClient().setCurrentMenu(MenuList.BuyMenu);

                isFirstTime = true;
            }
        });
    }

}
