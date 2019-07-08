package View.Battle;

import Controller.Client.Client;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.Collection;
import Model.CollectionItem.Card;
import Model.CollectionItem.CollectionItem;
import View.CollectionMenu.CollectionMenu.CollectionMenuController;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class GraveYardController implements Initializable {
    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();
    public GridPane grid;
    public Label back;

    public static ArrayList<CollectionItem> cardsToCollectionItem(ArrayList<Card> cards){
        ArrayList<CollectionItem> collectionItems = new ArrayList<>();
        for(Card card : cards){
            CollectionItem collectionItem = (CollectionItem) card;
            collectionItems.add(collectionItem);
        }
        return collectionItems;
    }

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
            grid.getChildren().remove(mainVBox);
            mainVBox.getChildren().clear();
            ServerMassage serverMassage;
            ArrayList<CollectionItem> collectionItems = new ArrayList<>();
            try {
                serverMassage = Client.getClient().graveYardCommand();
                collectionItems = cardsToCollectionItem(serverMassage.getGraveYard());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

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

            mainVBox.setLayoutY(100);
            mainVBox.setLayoutX(100);

            VBox.setMargin(mainVBox, new Insets(0, 0, 20, 0));

            grid.getChildren().add(mainVBox);

        }
        isFirstTime = false;

        back.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.Battle);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            isFirstTime = true;
        });
    }
}
