package Model;

import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Hero;
import Model.CollectionItem.Minion;
import Model.CollectionItem.Spell;

public class AI extends Player {

    public AI() {
        super(new Account("admin", "1234"));
    }

    public void selectMainDeck(Deck deck){
        this.getAccount().setUsername("AI");
        for(CollectionItem collectionItem : deck.getCards()) {
            CollectionItem newCollectionItem;
            if(collectionItem instanceof Spell) newCollectionItem =
            if(collectionItem instanceof Hero) newCollectionItem =
            if(collectionItem instanceof Minion) newCollectionItem =
        }
    }

    public String outputSomeRandomOrder(){

    }
}
