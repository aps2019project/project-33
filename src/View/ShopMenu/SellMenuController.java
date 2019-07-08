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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.event.CaretListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellMenuController implements Initializable {
    public AnchorPane mainAnchor;
    public AnchorPane blurAnchor;
    public AnchorPane nonBlurAnchor;
    public ImageView backButton;
    public Label sellLabel;

    public CollectionItem selectedCollectionItem;

    public static int numberOfHeros, numberOfMinions, numberOfSpells, numberOfItems;
    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();
    public static ArrayList<VBox> vBoxes = new ArrayList<>();
    public static ArrayList<CollectionItem> heroes = new ArrayList<>();
    public static ArrayList<CollectionItem> minions = new ArrayList<>();
    public static ArrayList<CollectionItem> spells = new ArrayList<>();
    public static ArrayList<CollectionItem> items = new ArrayList<>();
    ServerMassage serverMassage = null;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            vBoxes.clear();
            mainVBox.getChildren().clear();

            try {
                serverMassage = Client.getClient().shopMenuCommand(ClientMassage.ShopMenuRequest.GiveCollection,
                        null, null);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<CollectionItem> collectionItems = serverMassage.getCollection().getCollectionItems();

            heroes = Graphic.getHeroes(collectionItems);
            numberOfHeros = heroes.size();
            try {
                addPart(heroes, "HEROES:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            minions = Graphic.getMinions(collectionItems);
            numberOfMinions = minions.size();
            try {
                addPart(minions, "MINIONS:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            spells = Graphic.getSpells(collectionItems);
            numberOfSpells = spells.size();
            try {
                addPart(spells, "SPELLS:", mainVBox);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            items = Graphic.getItems(collectionItems);
            numberOfItems = items.size();
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

        int index = 0;
        for (VBox vBox : vBoxes) {
            CollectionItem collectionItem;
            if(index < numberOfHeros){
                collectionItem = heroes.get(index);
            }
            else if(index < numberOfHeros + numberOfMinions){
                collectionItem = minions.get(index - numberOfHeros);
            }
            else if(index < numberOfHeros + numberOfMinions + numberOfSpells){
                collectionItem = spells.get(index - (numberOfHeros + numberOfMinions));
            }
            else {
                collectionItem = items.get(index - (numberOfHeros + numberOfMinions + numberOfSpells));
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
                Client.getClient().changeCurrentMenu(MenuList.ShopMenu);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            isFirstTime = true;
        });

        sellLabel.setOnMouseClicked(event -> {
            if (selectedCollectionItem != null) {
                try {
                    ServerMassage serverMassage = Client.getClient().shopMenuCommand(ClientMassage.ShopMenuRequest.Sell,
                            null, selectedCollectionItem.getID());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Client.getClient().changeCurrentMenu(MenuList.ShopMenu);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                isFirstTime = true;
            }
        });
    }
}
