package View.CollectionMenu.ShowCollection;

import Controller.Client.Client;
import Controller.MenuList;
import Model.CollectionItem.CollectionItem;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowCollectionController implements Initializable {
    public AnchorPane mainPane;
    public ImageView backButton;

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
            ArrayList<CollectionItem> collectionItems = Client.getClient().getResultOfSearch();

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

            mainPane.getChildren().add(mainVBox);

        }
        isFirstTime = false;
        backButton.setOnMouseClicked(event -> {
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
            isFirstTime = true;
        });
    }
}
