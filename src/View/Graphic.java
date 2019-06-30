package View;

import Model.CollectionItem.CollectionItem;
import View.ShopMenu.ShowingShopController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Graphic {
    public static int numberOfGifs = 69;

    public static ArrayList<VBox> createCards(ArrayList<CollectionItem> collectionItems) {
        int index = 0;
        ArrayList<VBox> cardVboxes = new ArrayList<>();
        for (CollectionItem collectionItem : collectionItems) {
            int row = index / 5, column = index % 5;
            VBox cardVbox = new VBox();
            String address = "unit_gifs/" + index % numberOfGifs + ".gif";
            System.out.println("********* :" + address);
            Image image = new Image(Graphic.class.getResourceAsStream(address));
            ImageView cardGif = new ImageView(image);
            cardVbox.getChildren().add(cardGif);


        /*    Label label = new Label(collectionItem.getInfo());
            cardVbox.getChildren().add(label);
*/
            cardVbox.setLayoutY(row * 50);
            cardVbox.setLayoutX(column * 50);

            cardVboxes.add(cardVbox);
            index++;
        }
        return cardVboxes;
    }
}
