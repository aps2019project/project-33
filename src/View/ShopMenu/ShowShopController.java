package View.ShopMenu;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowShopController implements Initializable {

    public ScrollPane scrollPane;
    public AnchorPane anchorPane;
    public ImageView backButton;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();

    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText, VBox vBox) throws FileNotFoundException {
        Label label = new Label(labelText);
        label.setTextFill(Color.NAVY);
        label.setStyle("-fx-font-size: 15");
        vBox.getChildren().add(label);

        VBox partVBox = Graphic.createCards(collectionItems);
        vBox.getChildren().add(partVBox);
        VBox.setMargin(vBox, new Insets(0, 0, 20, 0));
    }

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
        if(isFirstTime){
            ServerMassage serverMassage = null;
            try {
                serverMassage = Client.getClient().shopMenuCommand(ClientMassage.ShopMenuRequest.GiveShop, null, null);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<CollectionItem> collectionItems = serverMassage.getShopCollection().getCollectionItems();
            collectionItems = unique(collectionItems);

            ArrayList<CollectionItem> heroes = Graphic.getHeroes(collectionItems);
            try {
                addPart(heroes, "HEROES:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<CollectionItem> minions = Graphic.getMinions(collectionItems);
            try {
                addPart(minions, "MINIONS:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<CollectionItem> spells = Graphic.getSpells(collectionItems);
            try {
                addPart(spells, "SPELLS:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<CollectionItem> items = Graphic.getItems(collectionItems);
            try {
                addPart(items, "ITEMS:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            mainVBox.setLayoutY(150);
            mainVBox.setLayoutX(100);

            VBox.setMargin(mainVBox, new Insets(0, 0, 20, 0));

            anchorPane.getChildren().add(mainVBox);
        }
        isFirstTime = false;

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
    }
}
