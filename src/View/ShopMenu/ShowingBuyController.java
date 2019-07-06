package View.ShopMenu;

import Controller.Client.Client;
import Controller.MenuList;
import Model.CollectionItem.CollectionItem;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.net.URL;
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
            try {
                cardsVbox = Graphic.createCards(Client.getClient().getResultOfSearch());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShopMenu);
            isFirstTime = true;
        });

        buyLabel.setOnMouseClicked(event -> {
            if (selectedCollectionItem != null) {
                Client.getClient().getShopMenu().inputCommandLine("buy " + selectedCollectionItem.getName());
                //todo in bayad doros she
                // Client.getClient().setCurrentMenu(MenuList.BuyMenu);

                isFirstTime = true;
            }
        });
    }

}
