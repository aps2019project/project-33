package View.CollectionMenu.ImportDeck;

import Controller.Application;
import Controller.MenuList;
import Model.Account;
import Model.Collection;
import Model.CollectionItem.*;
import Model.Deck;
import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportController implements Initializable {
    public AnchorPane mainPane;
    public ImageView duelystImage;
    public ImageView backButton;
    public Label importButton;
    public TextField nameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      /*  backButton.setOnMouseClicked(event -> {
            Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
        });

        importButton.setOnMouseClicked(event -> {
            String address = "Data/ExportedDecks/" + nameField.getText() + ".json";
            try {
                Deck importedDeck = (Deck) Application.readJSON(Deck.class, address);
                Collection collection = Client.getClient().getCollection();
                Deck deck = new Deck(importedDeck.getName());
                for(CollectionItem collectionItem : importedDeck.getCards()){
                    if(collectionItem instanceof Hero){
                        Hero hero = Hero.createHero(collectionItem.getName(), Client.getClient().getUsername());
                        deck.getCards().add(hero);
                    }
                    if (collectionItem instanceof Minion){
                        Minion minion = Minion.createMinion(collectionItem.getName(), Client.getClient().getUsername());
                        deck.getCards().add(minion);
                    }
                    if(collectionItem instanceof Spell){
                        Spell spell = Spell.createSpell(collectionItem.getName(), Client.getClient().getUsername());
                        deck.getCards().add(spell);
                    }
                    if(collectionItem instanceof Item){
                        Item item = Item.createItem(collectionItem.getName(), Client.getClient().getUsername());
                        deck.getCards().add(item);
                    }
                }
                collection.addDeck(deck);
                Client.getClient().setCurrentMenu(MenuList.CollectionMenu);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //TODO
            }
        });*/
    }
}
