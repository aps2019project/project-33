package View.CollectionMenu.RemoveCardFromDeck;

import Controller.Client;
import Controller.MenuList;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectCardController implements Initializable {
    public AnchorPane mainPane;
    public ImageView backButton;
    public ImageView duelystImage;
    public Label okButton;

    public CollectionItem selectedCollectionItem;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();
    public static ArrayList<VBox> vBoxes = new ArrayList<>();
    public static int numberOfHeroes, numberOfMinions, numberOfSpells, numberOfItems;

    public static ArrayList<CollectionItem> heroes = new ArrayList<>();
    public static ArrayList<CollectionItem> minions = new ArrayList<>();
    public static ArrayList<CollectionItem> spells = new ArrayList<>();
    public static ArrayList<CollectionItem> items = new ArrayList<>();

    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText, VBox vBox) {
        Label label = new Label(labelText);
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setStyle("-fx-font-size: 15");
        vBox.getChildren().add(label);

        VBox partVBox = Graphic.createCards(collectionItems);

        vBox.getChildren().add(partVBox);

        VBox.setMargin(vBox, new Insets(0, 0, 20, 0));

        vBoxes.addAll(Graphic.vBoxes);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            vBoxes.clear();
            mainVBox.getChildren().clear();
            Deck selectedDeck = View.CollectionMenu.RemoveCardFromDeck.SelectDeckController.selectedDeck;

            ArrayList<CollectionItem> collectionItems = selectedDeck.getCards();
            System.out.println(selectedDeck.getName() + " ******** " + collectionItems.size());

            heroes = Graphic.getHeroes(collectionItems);
            addPart(heroes, "HEROES:", mainVBox);
            numberOfHeroes = heroes.size();

            minions = Graphic.getMinions(collectionItems);
            addPart(minions, "MINIONS:", mainVBox);
            numberOfMinions = minions.size();

            spells = Graphic.getSpells(collectionItems);
            addPart(spells, "SPELLS:", mainVBox);
            numberOfSpells = spells.size();

            items = Graphic.getItems(collectionItems);
            addPart(items, "ITEMS:", mainVBox);
            numberOfItems = items.size();

            mainVBox.setLayoutY(150);
            mainVBox.setLayoutX(150);

            VBox.setMargin(mainVBox, new Insets(0, 0, 20, 0));

            mainPane.getChildren().add(mainVBox);
        }

        int index = 0;
        for (VBox vBox : vBoxes) {
            CollectionItem collectionItem;
            if(index < numberOfHeroes){
                collectionItem = heroes.get(index);
            }
            else if(index < numberOfHeroes + numberOfMinions){
                collectionItem = minions.get(index - numberOfHeroes);
            }
            else if(index < numberOfHeroes + numberOfMinions + numberOfSpells){
                collectionItem = spells.get((index - (numberOfHeroes + numberOfMinions)));
            }
            else{
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
            Client.getClient().setCurrentMenu(MenuList.CollectionSelectDeckForRemove);
            isFirstTime = true;
        });

        okButton.setOnMouseClicked(event -> {
            if (selectedCollectionItem != null) {
                try {
                    Client.getClient().getCollectionMenu().inputCommandLine("remove " + selectedCollectionItem.getID() +
                            " from deck " + SelectDeckController.selectedDeck.getName());
                    Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
                isFirstTime = true;
            }
        });

    }
}
