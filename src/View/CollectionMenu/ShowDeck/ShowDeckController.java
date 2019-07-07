package View.CollectionMenu.ShowDeck;

import Controller.MenuList;
import Model.CollectionItem.CollectionItem;
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

public class ShowDeckController implements Initializable {
    public AnchorPane mainPane;
    public ImageView backButton;
    public ImageView duelystImage;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();

/*
    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText, VBox vBox) {
        Label label = new Label(labelText);
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setStyle("-fx-font-size: 15");
        vBox.getChildren().add(label);

        VBox partVBox = Graphic.createCards(collectionItems);
        vBox.getChildren().add(partVBox);
        VBox.setMargin(vBox, new Insets(0, 0, 20, 0));
    }
*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*
        if (isFirstTime) {
            mainVBox.getChildren().clear();
            //todo server
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

            mainPane.getChildren().add(mainVBox);

        }
        isFirstTime = false;
        backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.CollectionSelectDeckForShow);
            isFirstTime = true;
        });
*/
    }
}
