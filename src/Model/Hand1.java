package Model;

import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Item;
import Model.CollectionItem.Minion;
import Model.CollectionItem.Spell;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand1 implements Serializable {

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
    //pull kon

    public void showMinions(){
        for(CollectionItem collectionItem : handCards)
            if(collectionItem instanceof Minion){
                System.out.println(collectionItem.getInfo());
            }
    }

    public void showItems(){
        for(CollectionItem collectionItem : handCards){
            if(collectionItem instanceof Item){
                System.out.println(collectionItem.getInfo());
            }
        }
    }

    //nazadam
    public void showNextCard(Deck mainDeck){
        if(numberOfUsedDeckCollectionItems >= mainDeck.getCards().size() - 1){
            System.out.println("next card doesn't exist!");
            return;
        }
        CollectionItem collectionItem = mainDeck.getCards().get(numberOfUsedDeckCollectionItems);
        System.out.println("Next card :");
        System.out.println(collectionItem.getName());
    }

    public void show(Deck mainDeck){
        System.out.println("spells :");
        this.showSpells();
        System.out.println("minions :");
        this.showMinions();
        System.out.println("items :");
        this.showItems();
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

    public void addNextCard(Deck mainDeck){
        if(mainDeck.getCards().size() - 1 <= numberOfUsedDeckCollectionItems){
            System.out.println("main deck is empty");
            return;
        }
        System.out.println(numberOfUsedDeckCollectionItems);
        handCards.add(mainDeck.getCards().get(numberOfUsedDeckCollectionItems));
        numberOfUsedDeckCollectionItems++;
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

    public int getNumberOfUsedDeckCollectionItems() {
        return numberOfUsedDeckCollectionItems;
    }

}