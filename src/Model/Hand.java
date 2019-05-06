package Model;

import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Minion;
import Model.CollectionItem.Spell;

import java.util.ArrayList;

public class Hand {

    private ArrayList<CollectionItem> handCards = new ArrayList<>();
    //havaset bashe to taghire in
    private int  numberOfUsedDeckCollectionItems = 0;

    public void showSpells(){
        for(CollectionItem collectionItem : handCards){
            if(collectionItem instanceof Spell) {
                System.out.println(collectionItem.getInfo());
            }
        }
    }

    //showMinion o showSpell tuye classashun ezafe beshan

    public void showMinions(){
        for(CollectionItem collectionItem : handCards)
            if(collectionItem instanceof Minion){
                System.out.println(collectionItem.getInfo());
            }
    }
    //nazadam
    public void showNextCard(Deck mainDeck){
        if(numberOfUsedDeckCollectionItems >= mainDeck.getCards().size()){
            System.out.println("next card doesn't exist!");
            return;
        }
        CollectionItem collectionItem = mainDeck.getCards().get(numberOfUsedDeckCollectionItems);
        System.out.println(collectionItem.getName());
    }

    public void show(Deck mainDeck){
        System.out.println("spells : \n");
        this.showSpells();
        System.out.println("heroes : \n");
        this.showMinions();
        showNextCard(mainDeck);
    }

    public void removeCard(String cardID){
        for(CollectionItem collectionItem : handCards){
            if(collectionItem.getID().equals(cardID)){
                handCards.remove(collectionItem);
                break;
            }
        }
    }

    public void addCard(CollectionItem collectionItem){
        handCards.add(collectionItem);
    }

    CollectionItem getCollectionItemByID(String collectionItemID){
        for(CollectionItem collectionItem : handCards)
            if(collectionItem.getID().equals(collectionItemID))
                return collectionItem;
        return null;
    }
    //havaset be tartibe ina bashe
    public CollectionItem getCollectionItemByIndex(int index){
        return handCards.get(index);
    }

    public ArrayList<CollectionItem> getHandCards() {
        return handCards;
    }

}