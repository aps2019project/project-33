package View.ShopMenu;

import Controller.Client;
import Controller.MenuList;
import Model.Collection;
import Model.CollectionItem.*;
import View.Graphic;
import com.sun.prism.paint.Color;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.sun.prism.paint.Color.WHITE;

public class ShopShowCollectionController implements Initializable {
    public ImageView backButton;
    public AnchorPane nonBlurAnchor;
    public AnchorPane blurAnchor;
    public AnchorPane mainAnchor;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();

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

    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText) {
        Label label = new Label(labelText);
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setStyle("-fx-font-size: 15");
        mainVBox.getChildren().add(label);

        VBox partVBox = Graphic.createCards(collectionItems);
        mainVBox.getChildren().add(partVBox);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {

            ArrayList<CollectionItem> collectionItems = Client.getClient().getResultOfSearch();

            ArrayList<CollectionItem> heroes = getHeroes(collectionItems);
            addPart(heroes, "HEROES:");

            ArrayList<CollectionItem> minions = getMinions(collectionItems);
            addPart(minions, "MINIONS:");

            ArrayList<CollectionItem> spells = getSpells(collectionItems);
            addPart(spells, "SPELLS:");

            ArrayList<CollectionItem> items = getItems(collectionItems);
            addPart(items, "ITEMS:");

            mainVBox.setLayoutY(100);
            mainVBox.setLayoutX(100);

            VBox.setMargin(mainVBox, new Insets(0, 0, 20, 0));

            mainAnchor.getChildren().add(mainVBox);

        }
        isFirstTime = false;
        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.ShopMenu);
            isFirstTime = true;
        });
    }
}
