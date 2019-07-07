package View.ShopMenu;

import Controller.MenuList;
import Model.Collection;
import Model.CollectionItem.CollectionItem;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowShopController implements Initializable {

    public ScrollPane scrollPane;
    public AnchorPane anchorPane;
    public ImageView backButton;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();

/*    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText, VBox vBox) {
        Label label = new Label(labelText);
        label.setTextFill(Color.NAVY);
        label.setStyle("-fx-font-size: 15");
        vBox.getChildren().add(label);

        VBox partVBox = Graphic.createCards(collectionItems);
        vBox.getChildren().add(partVBox);
        VBox.setMargin(vBox, new Insets(0, 0, 20, 0));
    }*/

    public static ArrayList<CollectionItem> unique(ArrayList<CollectionItem> collectionItems){
        ArrayList<CollectionItem> uniquedCollectionItems = new ArrayList<>();
        for(CollectionItem collectionItem : collectionItems){
            boolean find = false;
            for(CollectionItem uniquedCollectionItem : uniquedCollectionItems)
                if(uniquedCollectionItem.getName().equals(collectionItem.getName()))
                    find = true;
            if(!find)
                uniquedCollectionItems.add(collectionItem);
        }
        return uniquedCollectionItems;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    /*    if(isFirstTime){
            ArrayList<CollectionItem> collectionItems = Client.getClient().getResultOfSearch();
            collectionItems = unique(collectionItems);

            ArrayList<CollectionItem> heroes = Graphic.getHeroes(collectionItems);
            addPart(heroes, "HEROES:", mainVBox);

            ArrayList<CollectionItem> minions = Graphic.getMinions(collectionItems);
            addPart(minions, "MINIONS:", mainVBox);

            ArrayList<CollectionItem> spells = Graphic.getSpells(collectionItems);
            addPart(spells, "SPELLS:", mainVBox);

            ArrayList<CollectionItem> items = Graphic.getItems(collectionItems);
            addPart(items, "ITEMS:", mainVBox);

            mainVBox.setLayoutY(150);
            mainVBox.setLayoutX(100);

            VBox.setMargin(mainVBox, new Insets(0, 0, 20, 0));

            anchorPane.getChildren().add(mainVBox);
        }
        isFirstTime = false;

        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.ShopMenu);
            isFirstTime = true;
        });*/
    }
}
