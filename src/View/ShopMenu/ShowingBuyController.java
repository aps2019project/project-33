package View.ShopMenu;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.Collection;
import Model.CollectionItem.CollectionItem;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static ArrayList<CollectionItem> collectionItems = new ArrayList<>();
    public static boolean isFirstTime = true;
    public static VBox cardsVbox = new VBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            collectionItems = BuyController.serverMassage.getCollectionItems();
            nonBlurAnchor.getChildren().remove(cardsVbox);
            try {
                cardsVbox = Graphic.createCards(collectionItems);
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
                selectedCollectionItem = collectionItems.get(y);
                Graphic.clearShadows(Graphic.vBoxes);
                vBox.getStylesheets().add(Graphic.class.getResource("Card.css").toExternalForm());
                vBox.getStyleClass().add("SelectedCard");
            });
            index++;
        }

        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.ShopMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            isFirstTime = true;
        });

        buyLabel.setOnMouseClicked(event -> {
            if (selectedCollectionItem != null) {
                try {
                    ServerMassage serverMassage = Client.getClient().shopMenuCommand(ClientMassage.ShopMenuRequest.Buy,
                            null, selectedCollectionItem.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Client.getClient().changeCurrentMenu(MenuList.BuyMenu);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                isFirstTime = true;
            }
        });
    }

}
