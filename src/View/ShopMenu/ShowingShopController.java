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
    public int selectedIndex = -1;

    public static VBox cardsVbox = new VBox();
    public Label buyLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isFirstTime){
            cardsVbox = Graphic.createCards(Client.getClient().getResultOfSearch());
            nonBlurAnchor.getChildren().add(cardsVbox);
            cardsVbox.setLayoutX(200);
            cardsVbox.setLayoutY(200);
/*
            VBox cardVbox = new VBox();
            ImageView cardGif = new ImageView("../../../resources/unit_gifs/1.gif");
            cardVbox.getChildren().add(cardGif);
            cardVboxes.add(cardVbox);
            nonBlurAnchor.getChildren().add(cardGif);
*/
        }
        isFirstTime = false;
        int index = 0;
        for(ImageView imageView : Graphic.imageViews){
            final int y = index;
            imageView.setOnMouseClicked(event -> {
                selectedIndex = y;
                selectedCollectionItem = Client.getClient().getResultOfSearch().get(y);
                imageView.setStyle("-fx-effect: dropshadow(three-pass-box, white, 5, 0.5, 0, 0);");
                clearShadows();
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

    public void clearShadows(){
        int index = 0;
        for(ImageView imageView : Graphic.imageViews) {
            if (index != selectedIndex)
                imageView.getStyleClass().clear();
            index++;
        }
    }
}
