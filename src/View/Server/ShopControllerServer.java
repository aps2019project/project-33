package View.Server;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.MenuList;
import Controller.Server.ServerMain;
import Controller.Server.ServerMassage;
import Model.Collection;
import Model.CollectionItem.CollectionItem;
import View.Graphic;
import View.View;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class ShopControllerServer implements Initializable {

    public ScrollPane scrollPane;
    public AnchorPane anchorPane;
    public ImageView backButton;

    public static boolean isFirstTime = true;
    public static VBox mainVBox = new VBox();
    public Label createCardLabel;

    public static void addPart(ArrayList<CollectionItem> collectionItems, String labelText, VBox vBox,
                               ArrayList<Integer> numbers) throws FileNotFoundException {
        Label label = new Label(labelText);
        label.setTextFill(Color.NAVY);
        label.setStyle("-fx-font-size: 15");
        vBox.getChildren().add(label);

        VBox partVBox = Graphic.createCards(collectionItems);
        vBox.getChildren().add(partVBox);
        VBox.setMargin(vBox, new Insets(0, 0, 20, 0));
        int index = 0;
        for(VBox vBox1 : Graphic.vBoxes){
            Label numberLabel = new Label();
            numberLabel.setText(Integer.toString(numbers.get(index)));
            numberLabel.setAlignment(Pos.CENTER);
            numberLabel.setTextFill(Color.WHITE);
            vBox1.getChildren().add(numberLabel);
            index++;
        }
    }

    public static ArrayList<Integer> numberOfCollectionItems = new ArrayList<>();

    public static ArrayList<CollectionItem> calcNumberOfCollectionItems(ArrayList<CollectionItem> collectionItems) {
        ArrayList<CollectionItem> uniqued = new ArrayList<>();
        for (CollectionItem collectionItem : collectionItems) {
            int number = 0;
            for (CollectionItem tempCollectionItem : collectionItems) {
                if (collectionItem.getName().equals(tempCollectionItem.getName()))
                    number++;
            }
            boolean find = false;
            for (CollectionItem tempCollectionItem : uniqued)
                if (tempCollectionItem.getName().equals(collectionItem.getName()))
                    find = true;
            if (!find) {
                uniqued.add(collectionItem);
                numberOfCollectionItems.add(number);
            }
        }
        return uniqued;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            mainVBox.getChildren().clear();
            Collection shop = ServerMain.application.getShop();
            ArrayList<CollectionItem> collectionItems = shop.getCollectionItems();
            calcNumberOfCollectionItems(collectionItems);
            collectionItems = calcNumberOfCollectionItems(collectionItems);

            ArrayList<CollectionItem> heroes = Graphic.getHeroes(collectionItems);
            ArrayList<Integer> numberOfHero = new ArrayList<>();
            for (CollectionItem collectionItem : heroes) {
                int number = 0;
                for (CollectionItem tempCollectionItem : shop.getCollectionItems())
                    if (collectionItem.getName().equals(tempCollectionItem.getName()))
                        number++;
                numberOfHero.add(number);
            }

            try {
                addPart(heroes, "HEROES:", mainVBox, numberOfHero);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<CollectionItem> minions = Graphic.getMinions(collectionItems);
            ArrayList<Integer> numberOfMinion = new ArrayList<>();
            for (CollectionItem collectionItem : minions) {
                int number = 0;
                for (CollectionItem tempCollectionItem : shop.getCollectionItems())
                    if (collectionItem.getName().equals(tempCollectionItem.getName()))
                        number++;
                numberOfMinion.add(number);
            }
            try {
                addPart(minions, "MINIONS:", mainVBox, numberOfMinion);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<CollectionItem> spells = Graphic.getSpells(collectionItems);
            ArrayList<Integer> numberOfSpell = new ArrayList<>();
            for (CollectionItem collectionItem : spells) {
                int number = 0;
                for (CollectionItem tempCollectionItem : shop.getCollectionItems())
                    if (collectionItem.getName().equals(tempCollectionItem.getName()))
                        number++;
                numberOfSpell.add(number);
            }
            try {
                addPart(spells, "SPELLS:", mainVBox, numberOfSpell);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<CollectionItem> items = Graphic.getItems(collectionItems);
            ArrayList<Integer> numberOfItem = new ArrayList<>();
            for (CollectionItem collectionItem : items) {
                int number = 0;
                for (CollectionItem tempCollectionItem : shop.getCollectionItems())
                    if (collectionItem.getName().equals(tempCollectionItem.getName()))
                        number++;
                numberOfItem.add(number);
            }
            try {
                addPart(items, "ITEMS:", mainVBox, numberOfItem);
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
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("ShowClients.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerView.primaryStage.setScene(new Scene(root));

            isFirstTime = true;
        });

        createCardLabel.setOnMouseClicked(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("CreateCard.fxml"));
                ServerView.primaryStage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
