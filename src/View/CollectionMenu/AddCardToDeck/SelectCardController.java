package View.CollectionMenu.AddCardToDeck;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.Collection;
import Model.CollectionItem.CollectionItem;
import Model.Deck;
import View.Graphic;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectCardController implements Initializable {
    public AnchorPane mainPane;
    public ImageView duelystImage;
    public ImageView backButton;
    public Label okButton;
    public CollectionItem selectedCollectionItem;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();
    public static ArrayList<VBox> vBoxes = new ArrayList<>();
    public static int numberOfHeroes, numberOfMinions, numberOfSpells, numberOfItems;
    ArrayList<CollectionItem> heroes = new ArrayList<>();
    ArrayList<CollectionItem> minions = new ArrayList<>();
    ArrayList<CollectionItem> spells = new ArrayList<>();
    ArrayList<CollectionItem> items = new ArrayList<>();

    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText, VBox vBox) throws FileNotFoundException {
        Label label = new Label(labelText);
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setStyle("-fx-font-size: 15");
        vBox.getChildren().add(label);

        VBox partVBox = Graphic.createCards(collectionItems);

        vBox.getChildren().add(partVBox);

        VBox.setMargin(vBox, new Insets(0, 0, 20, 0));

        vBoxes.addAll(Graphic.vBoxes);
    }

    public static ArrayList<CollectionItem> removeDeckCards(Deck deck, ArrayList<CollectionItem> collectionItems) {
        ArrayList<CollectionItem> editedCollectionItems = new ArrayList<>();
        for (CollectionItem collectionItem : collectionItems){
            boolean find = false;
            for(CollectionItem tempCollectionItem : deck.getCards())
                if(tempCollectionItem.getID().equals(collectionItem.getID()))
                    find = true;
            if (!find)
                editedCollectionItems.add(collectionItem);
        }
        return editedCollectionItems;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            vBoxes.clear();
            mainVBox.getChildren().clear();


            ServerMassage serverMassage = null;
            try {
                serverMassage = Client.getClient().collectionMenuCommand(ClientMassage.CollectionMenuRequest.GiveCollection);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Collection collection = serverMassage.getCollection();
            ArrayList<CollectionItem> collectionItems = collection.getCollectionItems();
            collectionItems = removeDeckCards(SelectDeckController.selectedDeck, collectionItems);

            heroes = Graphic.getHeroes(collectionItems);
            try {
                addPart(heroes, "HEROES:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            numberOfHeroes = heroes.size();

            minions = Graphic.getMinions(collectionItems);
            try {
                addPart(minions, "MINIONS:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            numberOfMinions = minions.size();

            spells = Graphic.getSpells(collectionItems);
            try {
                addPart(spells, "SPELLS:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            numberOfSpells = spells.size();

            items = Graphic.getItems(collectionItems);
            try {
                addPart(items, "ITEMS:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            numberOfItems = items.size();

            mainVBox.setLayoutY(130);
            mainVBox.setLayoutX(150);

            VBox.setMargin(mainVBox, new Insets(0, 0, 20, 0));

            mainPane.getChildren().add(mainVBox);
        }

        int index = 0;
        for (VBox vBox : vBoxes) {
            CollectionItem collectionItem;
            if (index < numberOfHeroes) {
                collectionItem = heroes.get(index);
            } else if (index < numberOfHeroes + numberOfMinions) {
                collectionItem = minions.get(index - numberOfHeroes);
            } else if (index < numberOfHeroes + numberOfMinions + numberOfSpells) {
                collectionItem = spells.get(index - (numberOfHeroes + numberOfMinions));
            } else {
                collectionItem = items.get(index - (numberOfHeroes + numberOfMinions + numberOfSpells));
            }

            vBox.setOnMouseClicked(event -> {
                selectedCollectionItem = collectionItem;
                Graphic.clearShadows(vBoxes);
                vBox.getStylesheets().add(Graphic.class.getResource("Card.css").toExternalForm());
                vBox.getStyleClass().add("SelectedCard");
            });
            index++;
        }

        isFirstTime = false;

        backButton.setOnMouseClicked(event -> {
            try {
                Client.getClient().changeCurrentMenu(MenuList.CollectionSelectDeckForAdd);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            isFirstTime = true;
        });

        okButton.setOnMouseClicked(event -> {
            if (selectedCollectionItem != null) {
                try {
                    ServerMassage serverMassage = Client.getClient().collectionMenuCommand(ClientMassage.CollectionMenuRequest.AddCollectionItemToDeck,
                            SelectDeckController.selectedDeck, selectedCollectionItem, null);

                    isFirstTime = true;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                isFirstTime = true;
                System.out.println();
            }
        });
    }
}
