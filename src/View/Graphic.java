package View;

import Model.CollectionItem.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Graphic {
    public static int numberOfGifs = 69;
    public static int numberOfColumns = 5;
    public static ArrayList<VBox> vBoxes = new ArrayList<>();

    public static VBox createCards(ArrayList<CollectionItem> collectionItems) throws FileNotFoundException {
        vBoxes.clear();

        int index = 0;
        VBox mainVBox = new VBox();

        int size = collectionItems.size();
        for (int i = 0; i < (size - 1) / numberOfColumns + 1; i++) {
            if(index >= size)
                break;
            HBox hBox = new HBox();
            for (int j = 0; j < numberOfColumns; j++) {
                if(index >= size)
                    break;
                CollectionItem collectionItem = collectionItems.get(index);
                VBox cardVbox = Graphic.createCard(collectionItem, collectionItem.getName());
                vBoxes.add(cardVbox);

                hBox.getChildren().add(cardVbox);
                HBox.setMargin(cardVbox, new Insets(0, 0, 5, 5));

                index++;
            }
            mainVBox.getChildren().add(hBox);
        }

        return mainVBox;
    }

    public static VBox createCard(CollectionItem collectionItem, String cardName) throws FileNotFoundException {
        VBox cardVbox = new VBox();
        cardVbox.setPrefWidth(150);
        cardVbox.setPrefHeight(250);
        //add gif
        String type = ".gif";
        if(collectionItem instanceof Spell)
            type = ".png";
        String address = "resources/unit_gifs/" + cardName  + type;
        System.out.println(address + "*******");
        Image image;
        try{
            image = new Image(new FileInputStream(address));
        }
        catch (Exception e){
            System.out.println(":(((((((((");
            Random random = new Random();
            int randomNumber = random.nextInt(18) + 50;
            address = "resources/unit_gifs/" + randomNumber  + ".gif";
            image = new Image(new FileInputStream(address));
        }
        ImageView cardGif = new ImageView(image);
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

    public static ArrayList<CollectionItem> getHeroes(ArrayList<CollectionItem> collectionItems) {
        ArrayList<CollectionItem> heroes = new ArrayList<>();
        for (CollectionItem collectionItem : collectionItems)
            if (collectionItem instanceof Hero)
                heroes.add(collectionItem);
        return heroes;
    }

    public static ArrayList<CollectionItem> getMinions(ArrayList<CollectionItem> collectionItems) {
        ArrayList<CollectionItem> minions = new ArrayList<>();
        for (CollectionItem collectionItem : collectionItems)
            if (collectionItem instanceof Minion)
                minions.add(collectionItem);
        return minions;
    }

    public static ArrayList<CollectionItem> getSpells(ArrayList<CollectionItem> collectionItems) {
        ArrayList<CollectionItem> spells = new ArrayList<>();
        for (CollectionItem collectionItem : collectionItems)
            if (collectionItem instanceof Spell)
                spells.add(collectionItem);
        return spells;
    }

    public static ArrayList<CollectionItem> getItems(ArrayList<CollectionItem> collectionItems) {
        ArrayList<CollectionItem> items = new ArrayList<>();
        for (CollectionItem collectionItem : collectionItems)
            if (collectionItem instanceof Item)
                items.add(collectionItem);
        return items;
    }

    public static void clearShadows(ArrayList<VBox> cardVboxes) {
        for (VBox vBox : cardVboxes)
            vBox.getStyleClass().remove("SelectedCard");
    }
}
