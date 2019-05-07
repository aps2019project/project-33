package Model;

import Model.CollectionItem.*;

import java.io.FileNotFoundException;

public class AI extends Player {

    public AI() {
        super(new Account("admin", "1234"));
    }

    public void selectMainDeck(Deck deck) throws FileNotFoundException {
        this.getAccount().setUsername("AI");
        this.getAccount().getCollection().createDeck("AI");
        for(CollectionItem collectionItem : deck.getCards()) {
            CollectionItem newCollectionItem = null;
            if(collectionItem instanceof Spell) newCollectionItem = Spell.createSpell(collectionItem.getName(), "AI");
            if(collectionItem instanceof Hero) newCollectionItem = Hero.createHero(collectionItem.getName(), "AI");
            if(collectionItem instanceof Minion) newCollectionItem = Minion.createMinion(collectionItem.getName(), "AI");
            if(collectionItem instanceof Item) newCollectionItem = Item.createItem(collectionItem.getName(), "AI");
            this.getAccount().getCollection().addCollectionItemToCollection(newCollectionItem.getID());
            this.getAccount().getCollection().addCollectionItemToDeck(newCollectionItem.getID(), "AI");
        }
        this.getAccount().getCollection().selectMainDeck("AI");
    }

    public String outputSomeRandomOrder(){

    }
}
