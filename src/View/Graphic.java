package View;

import Model.CollectionItem.CollectionItem;
import View.ShopMenu.ShowingShopController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Graphic {
    public static int numberOfGifs = 69;
    public static int numberOfColumns = 5;
    public static ArrayList<ImageView> imageViews = new ArrayList<>();

    public static VBox createCards(ArrayList<CollectionItem> collectionItems) {
        int index = 0;
        VBox mainVBox = new VBox();

        ArrayList<VBox> cardVboxes = new ArrayList<>();

        int size = collectionItems.size();
        for (int i = 0; i < size / numberOfColumns; i++) {
            HBox hBox = new HBox();
            for (int j = 0; j < numberOfColumns; j++) {
                CollectionItem collectionItem = collectionItems.get(index);

                VBox cardVbox = new VBox();
                cardVbox.setPrefWidth(100);
                cardVbox.setPrefHeight(100);
                String address = "unit_gifs/" + index % numberOfGifs + ".gif";
                Image image = new Image(Graphic.class.getResourceAsStream(address));
                ImageView cardGif = new ImageView(image);
                imageViews.add(cardGif);
                cardGif.setFitHeight(cardVbox.getPrefHeight());
                cardGif.setFitWidth(cardVbox.getPrefWidth());

                cardVbox.getChildren().add(cardGif);


        /*    Label label = new Label(collectionItem.getInfo());
            cardVbox.getChildren().add(label);
*/
                hBox.getChildren().add(cardVbox);
                cardVboxes.add(cardVbox);

                index++;
            }
            mainVBox.getChildren().add(hBox);
        }

        return mainVBox;
    }
}
