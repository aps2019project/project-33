package View.ShopMenu;

import Controller.Client.Client;
import Controller.MenuList;
import Model.CollectionItem.*;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopShowCollectionController implements Initializable {
    public ImageView backButton;
    public AnchorPane nonBlurAnchor;
    public AnchorPane blurAnchor;
    public AnchorPane mainAnchor;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();


    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText, VBox vBox) {
        Label label = new Label(labelText);
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setStyle("-fx-font-size: 15");
        vBox.getChildren().add(label);

        VBox partVBox = Graphic.createCards(collectionItems);
        vBox.getChildren().add(partVBox);
        VBox.setMargin(vBox, new Insets(0, 0, 20, 0));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            mainVBox.getChildren().clear();
            ArrayList<CollectionItem> collectionItems = Client.getClient().getResultOfSearch();

            ArrayList<CollectionItem> heroes = Graphic.getHeroes(collectionItems);
            addPart(heroes, "HEROES:", mainVBox);

            ArrayList<CollectionItem> minions = Graphic.getMinions(collectionItems);
            addPart(minions, "MINIONS:", mainVBox);

            ArrayList<CollectionItem> spells = Graphic.getSpells(collectionItems);
            addPart(spells, "SPELLS:", mainVBox);

            ArrayList<CollectionItem> items = Graphic.getItems(collectionItems);
            addPart(items, "ITEMS:", mainVBox);

            mainVBox.setLayoutY(100);
            mainVBox.setLayoutX(100);

            VBox.setMargin(mainVBox, new Insets(0, 0, 20, 0));

            mainAnchor.getChildren().add(mainVBox);

        }
        isFirstTime = false;
        backButton.setOnMouseClicked(event -> {
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShopMenu);
            isFirstTime = true;
        });
    }
}
