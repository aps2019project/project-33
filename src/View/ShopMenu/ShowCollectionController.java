package View.ShopMenu;

import Controller.Client;
import Controller.MenuList;
import Model.Collection;
import Model.CollectionItem.*;
import View.Graphic;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowCollectionController implements Initializable {
    public ImageView backButton;
    public AnchorPane nonBlurAnchor;
    public AnchorPane blurAnchor;
    public AnchorPane mainAnchor;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();

    public static ArrayList<CollectionItem> getHeroes(ArrayList<CollectionItem> collectionItems){
        ArrayList<CollectionItem> heroes = new ArrayList<>();
        for(CollectionItem collectionItem : collectionItems)
            if(collectionItem instanceof Hero)
                heroes.add(collectionItem);
        return heroes;
    }

    public static ArrayList<CollectionItem> getMinions(ArrayList<CollectionItem> collectionItems){
        ArrayList<CollectionItem> minions = new ArrayList<>();
        for(CollectionItem collectionItem : collectionItems)
            if(collectionItem instanceof Minion)
                minions.add(collectionItem);
        return collectionItems;
    }

    public static ArrayList<CollectionItem> getSpells(ArrayList<CollectionItem> collectionItems){
        ArrayList<CollectionItem> spells = new ArrayList<>();
        for(CollectionItem collectionItem : collectionItems)
            if(collectionItem instanceof Spell)
                spells.add(collectionItem);
        return spells;
    }

    public static ArrayList<CollectionItem> getItems(ArrayList<CollectionItem> collectionItems){
        ArrayList<CollectionItem> items = new ArrayList<>();
        for(CollectionItem collectionItem : collectionItems)
            if(collectionItem instanceof Item)
                items.add(collectionItem);
        return items;
    }

    public static void addHeroes(){
        ArrayList<CollectionItem> heroes = getHeroes(Client.getClient().getResultOfSearch());
        Label heroesLabel = new Label("HEROES");
        VBox heroesVBox = Graphic.createCards(heroes);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* if(isFirstTime){
            ArrayList<CollectionItem> collectionItems = Client.getClient().getResultOfSearch();

            ArrayList<CollectionItem> minions = getMinions(collectionItems);
            ArrayList<CollectionItem> spells = getSpells(collectionItems);
            ArrayList<CollectionItem> items = getItems(collectionItems);


            VBox minionsVBox = Graphic.createCards(minions);
            VBox spellsVBox = Graphic.createCards(spells);
            VBox itemsVBox = Graphic.createCards(items);

            VBox mainVBox = new VBox();
            Label heroesLabel = new Label("HEROES:");
            mainVBox.getChildren().add(heroesVBox);


            mainAnchor.getChildren().addAll(heroesVBox, minionsVBox, spellsVBox, itemsVBox);

        }*/
        isFirstTime = false;
        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.ShopMenu);
            isFirstTime = true;
        });
    }
}
