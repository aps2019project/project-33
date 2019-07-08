package View.ShopMenu;

import Controller.Client.Client;
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

import java.io.FileNotFoundException;
import java.io.IOException;
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


    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText, VBox vBox) throws FileNotFoundException {
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
            if(ShopController.serverMassage == null)
                System.out.println("SERVER NULL E");
            else if(ShopController.serverMassage.getCollection() == null)
                System.out.println("COLLECTION NULLE");
            ArrayList<CollectionItem> collectionItems = ShopController.serverMassage.getCollection().getCollectionItems();

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

            mainVBox.setLayoutY(100);
            mainVBox.setLayoutX(100);

            VBox.setMargin(mainVBox, new Insets(0, 0, 20, 0));

            mainAnchor.getChildren().add(mainVBox);

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
