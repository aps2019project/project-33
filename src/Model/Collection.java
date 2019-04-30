package Model;

import Model.CollectionItem.*;

import java.util.ArrayList;

public class Collection {
    private ArrayList<CollectionItem> cards;
    private Deck mainDeck;
    private ArrayList<Deck> decks = new ArrayList<Deck>();

    public void removeCollectionItemFromCollection(String collectionItemID){
    }

    public void addCollectionItemToCollection(String collectionItemID){
    }

    public void showDeck(String deckName){
        Deck.showDeck(deckName, false);
    }

    public void showAllDecks(){
        Deck.showAllDecks();
    }

    public void selectMainDeck(String deckName){
        Deck deck = Deck.getDeckByName(deckName);
        if(deck == null){
            System.out.println("deck not found");
            return;
        }
        this.mainDeck = deck;
    }

    public boolean checkValidateDeck(String deckName){
        Deck deck = Deck.getDeckByName(deckName);
        if(deck == null) {
            System.out.println("deck not found");
            return false;
        }
        return deck.checkValidateDeck();
    }

    boolean validateInput(CollectionItem collectionItem, Deck deck){
        if(deck == null){
            System.out.println("deck not found");
            return false;
        }

        if(collectionItem == null){
            System.out.println("card not found");
            return false;
        }
        return true;
    }

    public void removeCollectionItemFromDeck(String ID, String deckName){
        Deck deck = Deck.getDeckByName(deckName);
        CollectionItem collectionItem = deck.findCollectionItemInDeck(ID);
        if(this.validateInput(collectionItem, deck))
            deck.removeCollectionItemFromDeck(collectionItem);
    }

    public void addCollectionItemToDeck(String ID, String deckName){
        Deck thisDeck = null;
        for(Deck deck : this.getDecks()){
            if(deck.getName().equals(deckName)){
                thisDeck = deck;
            }
        }
        if(thisDeck == null){
            System.out.println("deck isnt in your collection");
            return;
        }
        CollectionItem collectionItem = CollectionItem.getCollectionItemByID(ID);
        if(this.validateInput(collectionItem, thisDeck))
            thisDeck.addCard(collectionItem);
    }

    public void deleteDeck(String deckName){
        for(Deck deck : this.getDecks()){
            if(deck.getName().equals(deckName)){
                Deck.removeDeck(deck);
                decks.remove(deck);
                return;
            }
        }
        System.out.println("deck wasnt found");
    }

    public ArrayList<Deck> getDecks(){
        return this.decks;
    }

    public void createDeck(String deckName){
        Deck deck = Deck.createDeck(deckName);
        if(deck != null)
            decks.add(deck);
    }

    public ArrayList<CollectionItem> getCards(){
        return this.cards;
    }

    public ArrayList<String> search(String cardName){
        ArrayList<String> IDs = new ArrayList<String>();
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem.getName().equals(cardName)){
                IDs.add(collectionItem.getID());
            }
        }
        return IDs;
    }

    public void showHeroes(String descriptionOfPrice){
        System.out.println("Heroes :");
        int numberOfHeroes = 0;
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Hero){
                numberOfHeroes++;
                System.out.print(numberOfHeroes + " : " + collectionItem.getID() + " " + descriptionOfPrice + " : " +
                        collectionItem.getPrice());
            }
        }
    }

    public void showItems(String descriptionOfPrice){
        System.out.println("Items :");
        int numberOfItems = 0;
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Item){
                numberOfItems++;
                System.out.print(numberOfItems + " : " + collectionItem.getInfo() + " " + descriptionOfPrice + " : " +
                        + collectionItem.getPrice());
            }
        }
    }

    public void showCards(String descriptionOfPrice){
        int numberOfCards = 0;
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Spell || collectionItem instanceof Minion){
                numberOfCards++;
                System.out.print(numberOfCards + " : " + collectionItem.getInfo() + " " + descriptionOfPrice + " : " +
                        + collectionItem.getPrice());
            }
        }
    }

    public void showCollection(String descriptionOfPrice){
        this.showHeroes(descriptionOfPrice);
        this.showItems(descriptionOfPrice);
        this.showCards(descriptionOfPrice);
    }

    public void save(){
        //pull test
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }
}
