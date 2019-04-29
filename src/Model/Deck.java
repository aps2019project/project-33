package Model;

import Model.CollectionItem.*;

import java.util.ArrayList;

public class Deck{
    private ArrayList<CollectionItem> cards = new ArrayList<CollectionItem>();
    private static ArrayList<Deck> decks = new ArrayList<>();
    private String name;

    //constructor

    public Deck(String name) {
        this.name = name;
    }

    public static Deck getDeckByName(String deckName){
        for(Deck deck : decks)
            if(deck.getName().equals(deckName))
                return deck;
        return null;
    }

// ye string desc o special power bayad be carda ezafe she
    public static void showDeck(String deckName, boolean haveTab){
        Deck deck = Deck.getDeckByName(deckName);
        System.out.println("Heroes :");
        int numberOfHeroes = 0;
        for(CollectionItem collectionItem : deck.getCards()){
            if(collectionItem instanceof Hero){
                numberOfHeroes++;
                //desc bayaad too collectionitem bashe ? + class baraye hero
                System.out.print(numberOfHeroes + " : Name : " + collectionItem.getName() + " - AP : " + collectionItem.getAP());
                System.out.print(" - HP : " + collectionItem.getHP() + " Class : " + collectionItem.getClass());
                System.out.println(" Special power: " + collectionItem.getSpecialPower());
            }
        }
        System.out.println("Items :");
        int numberOfItems = 0;
        for(CollectionItem collectionItem : deck.getCards()){
            if(collectionItem instanceof Item){
                numberOfItems++;
                System.out.println(numberOfItems + " : Name : " + collectionItem.getName() + " - Desc " + collectionItem.getDesc());
            }
        }
        int numberOfCards = 0;
        for(CollectionItem collectionItem : deck.getCards()){
            if(collectionItem instanceof Spell){
                numberOfCards++;
                System.out.print(numberOfCards + " : Type : Spell - Name : " + collectionItem.getName() + " - MP : ");
                System.out.println(collectionItem.getMP() + " - Desc : " + collectionItem.getDesc());
            }
            if(collectionItem instanceof Minion){
                numberOfCards++;
                System.out.print(numberOfCards + " : Type : Minion - Name : " + collectionItem.getName() + " - Class: ");
                System.out.print(collectionItem.getClass() + " - AP : " + collectionItem.getAP() + " - HP : ");
                System.out.print(collectionItem.getHP() + " - MP : " + collectionItem.getMP() + " - Special power : ");
                System.out.println(collectionItem.getSpecialPower());
            }
        }
    }

    public static void showAllDecks(){
        int i = 1;
        for(Deck deck : decks) {
            System.out.println(i + " : " + deck.getName() + " :");
            showDeck(deck.getName(), true);
        }
    }

    //check kon ke 20 ta be hero e ya bedune hero
    public boolean checkValidateDeck(){
        ArrayList<CollectionItem> cards = this.getCards();
        if(cards.size != 21)
            return false;
        int numberOfHeroes = 0;
        for(CollectionItem collectionItem : cards){
            if(collectionItem instanceof Hero)
                numberOfHeroes++;
        }
        if(numberOfHeroes != 1)
            return false;
        return true;
    }

    public void removeCard(CollectionItem collectionItem){
        this.cards.remove(collectionItem);
    }

    public static void removeCardFromDeck(String id, String deckName){
        Deck deck = getDeckByName(deckName);
        for(CollectionItem collectionItem: deck.getCards())
            if(collectionItem.getID().equals(id)){
                deck.removeCard(collectionItem);
                break;
            }
    }

    public void addCard(CollectionItem collectionItem){
        this.cards.add(collectionItem);
    }

    public static void addCardToDeck(String id, String deckName){
        Deck deck = getDeckByName(deckName);
        CollectionItem collectionItem = getCollectionItemByID(id);
        deck.addCard(collectionItem);
    }


    public static void deleteDeck(String deckName){
        for(Deck deck : decks){
            if(deck.getName().equals(deckName)){
                decks.remove(deck);
                break;
            }
        }
    }
    //too cell havaset bashe hamaro besazi avval
    public static void createDeck(String deckName){
        Deck deck = getDeckByName(deckName);
        if(deck != null){
            System.out.println("a deck with this name already exists");
            return;
        }
        deck = new Deck();
        deck.setName(deckName);
        decks.add(deck);
    }

    //Here is setters && getters

    public ArrayList<CollectionItem> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}