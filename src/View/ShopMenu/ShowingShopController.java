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

public class ShowingShopController implements Initializable {
    public AnchorPane nonBlurAnchor;
    public AnchorPane blurAnchor;
    public AnchorPane mainAnchor;
    public static boolean isFirstTime = true;
    public ImageView backButton;

    public CollectionItem selectedCollectionItem;

    public static VBox cardsVbox = new VBox();
    public Label buyLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
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
                clearShadows();
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
            Client.getClient().getShopMenu().inputCommandLine("buy " + selectedCollectionItem.getName());
        });
    }

    public void clearShadows() {
        for (VBox vBox : Graphic.vBoxes)
            vBox.getStyleClass().remove("SelectedCard");
    }
}
