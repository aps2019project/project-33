package Model;

import Model.CollectionItem.*;

import java.util.ArrayList;

public class Collection {
    private ArrayList<CollectionItem> cards = new ArrayList<>();
    private Deck mainDeck;
    private ArrayList<Deck> decks = new ArrayList<>();

    public Deck getDeckByName(String deckName){
        for(Deck deck : decks) {
            if (deck.getName().equals(deckName))
                return deck;
        }
        return null;
    }

    public CollectionItem getCollectionItemByID(String ID){
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem.getID().equals(ID)){
                return collectionItem;
            }
        }
        return null;
    }

    public void removeCollectionItemFromCollection(String collectionItemID){
        CollectionItem collectionItem = this.getCollectionItemByID(collectionItemID);
        if(collectionItem == null){
            System.out.println("Can't find this CollectionItem");
            return;
        }

        cards.remove(collectionItem);
        for(Deck deck : this.decks)
            deck.removeCollectionItemFromDeck(collectionItem);
    }

    public void addCollectionItemToCollection(String collectionItemID){
        CollectionItem collectionItem = CollectionItem.getCollectionItemByID(collectionItemID);
        if(collectionItem == null){
            System.out.println("Can't find this CollectionItem");
            return;
        }
        this.cards.add(collectionItem);
    }

    public void showDeck(String deckName){
        Deck deck = this.getDeckByName(deckName);
        if(deck == null){
            System.out.println("deck doesn't exist");
            return;
        }
        deck.showDeck(false);
    }

    public void showAllDecks(){
        int numberOfDecks = 0;
        for(Deck deck : this.getDecks()){
            numberOfDecks++;
            System.out.println(numberOfDecks + " : " + deck.getName() + " :");
            deck.showDeck(true);
        }
    }

    public void selectMainDeck(String deckName){
        Deck deck = this.getDeckByName(deckName);
        if(deck == null){
            System.out.println("deck not found");
            return;
        }
        this.mainDeck = deck;
    }

    public boolean checkValidateDeck(String deckName){
        Deck deck = this.getDeckByName(deckName);
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
        Deck deck = this.getDeckByName(deckName);
        CollectionItem collectionItem = deck.findCollectionItemInDeck(ID);
        if(collectionItem == null){
            System.out.println("collection item not found");
            return;
        }
        if(this.validateInput(collectionItem, deck))
            deck.removeCollectionItemFromDeck(collectionItem);
    }

    public void addCollectionItemToDeck(String ID, String deckName){
        Deck thisDeck = this.getDeckByName(deckName);
        CollectionItem collectionItem = this.getCollectionItemByID(ID);
        if(this.validateInput(collectionItem, thisDeck))
            thisDeck.addCard(collectionItem);
    }

    public void deleteDeck(String deckName){
        for(Deck deck : this.getDecks()){
            if(deck.getName().equals(deckName)){
                decks.remove(deck);
                return;
            }
        }
        System.out.println("deck wasn't found");
    }

    public ArrayList<Deck> getDecks(){
        return this.decks;
    }

    public void createDeck(String deckName){
        Deck deck = this.getDeckByName(deckName);
        if(deck != null){
            System.out.println("a deck with this name already exists");
            return;
        }
        deck = new Deck(deckName);
        this.decks.add(deck);
    }

    public ArrayList<CollectionItem> getCards(){
        return this.cards;
    }

    public ArrayList<String> search(String cardName){
        ArrayList<String> IDs = new ArrayList<>();
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
                System.out.println(numberOfHeroes + " : " + collectionItem.getID() + " " + descriptionOfPrice + " : " +
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
                System.out.println(numberOfItems + " : " + collectionItem.getInfo() + " " + descriptionOfPrice + " : " +
                        + collectionItem.getPrice());
            }
        }
    }

    public void showCards(String descriptionOfPrice){
        System.out.println("Cards :");
        int numberOfCards = 0;
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Spell || collectionItem instanceof Minion){
                numberOfCards++;
                System.out.println(numberOfCards + " : " + collectionItem.getInfo() + " " + descriptionOfPrice + " : " +
                        + collectionItem.getPrice());
            }
        }
    }

    public void showCollection(String descriptionOfPrice){
        this.showHeroes(descriptionOfPrice);
        this.showItems(descriptionOfPrice);
        this.showCards(descriptionOfPrice);
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }
}
