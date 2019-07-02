package View;

import Model.CollectionItem.CollectionItem;
import View.ShopMenu.ShowingShopController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Graphic {
    public static int numberOfGifs = 69;
    public static int numberOfColumns = 5;
    public static ArrayList<ImageView> imageViews = new ArrayList<>();
    public static ArrayList<VBox> vBoxes = new ArrayList<>();

    public static VBox createCards(ArrayList<CollectionItem> collectionItems) {
        int index = 0;
        VBox mainVBox = new VBox();

        int size = collectionItems.size();
        for (int i = 0; i < size / numberOfColumns; i++) {
            HBox hBox = new HBox();
            for (int j = 0; j < numberOfColumns; j++) {
                CollectionItem collectionItem = collectionItems.get(index);

                VBox cardVbox = Graphic.createCard(collectionItem, index);
                vBoxes.add(cardVbox);

                hBox.getChildren().add(cardVbox);
                HBox.setMargin(cardVbox, new Insets(0, 0, 5, 5));

                index++;
            }
            mainVBox.getChildren().add(hBox);
        }

        return mainVBox;
    }

    public static VBox createCard(CollectionItem collectionItem, int index){
        VBox cardVbox = new VBox();
        cardVbox.setPrefWidth(150);
        cardVbox.setPrefHeight(250);
        //add gif
        String address = "unit_gifs/" + index + ".gif";
        Image image = new Image(Graphic.class.getResourceAsStream(address));
        ImageView cardGif = new ImageView(image);
        imageViews.add(cardGif);
        cardGif.setFitHeight(cardVbox.getPrefHeight() / 2);
        cardGif.setFitWidth(cardVbox.getPrefWidth());
        cardVbox.getChildren().add(cardGif);
        //add labels
        ArrayList<Label> infoLabels = collectionItem.getInfo();
        for (Label label : infoLabels) {
            label.setMinWidth(cardVbox.getPrefWidth());
            label.setAlignment(Pos.CENTER);
            label.setTextFill(Color.WHITE);
            cardVbox.getChildren().add(label);

        }
        //add card background
        cardVbox.getStylesheets().add(Graphic.class.getResource("Card.css").toExternalForm());
        cardVbox.getStyleClass().add("VBoxBackground");
        return cardVbox;
    }

}
