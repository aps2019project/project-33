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
        if(haveTab)
            System.out.print("    ");
        System.out.println("Heroes :");
        int numberOfHeroes = 0;
        for(CollectionItem collectionItem : deck.getCards()){
            if(collectionItem instanceof Hero){
                numberOfHeroes++;
                //desc bayaad too collectionitem bashe ? + class baraye hero
                if(haveTab)
                    System.out.print("    ");
                System.out.print("    " + numberOfHeroes + " : ");
                Hero hero = (Hero)collectionItem;
                hero.showHero();
                /*
                System.out.print(numberOfHeroes + " : Name : " + collectionItem.getName() + " - AP : " + collectionItem.getAP());
                System.out.print(" - HP : " + collectionItem.getHP() + " Class : " + collectionItem.getClass());
                System.out.println(" Special power: " + collectionItem.getSpecialPower());
                */
            }
        }
        if(haveTab)
            System.out.print("    ");
        System.out.println("Items :");
        int numberOfItems = 0;
        for(CollectionItem collectionItem : deck.getCards()){
            if(collectionItem instanceof Item){
                numberOfItems++;
                if(haveTab)
                    System.out.print("    ");
                System.out.print("    " + numberOfItems + " : ");
                Item item = (Item)collectionItem;
                item.showItem();
                /*
                System.out.println(numberOfItems + " : Name : " + collectionItem.getName() + " - Desc " + collectionItem.getDesc());
                */
            }
        }
        int numberOfCards = 0;
        if(haveTab)
            System.out.print("    ");
        System.out.println("Cards :");
        for(CollectionItem collectionItem : deck.getCards()){
            if(collectionItem instanceof Spell){
                numberOfCards++;
                if(haveTab)
                    System.out.print("    ");
                System.out.print("    " + numberOfCards + " : ");
                Spell spell = (Spell)collectionItem;
                spell.showSpell();
                /*
                System.out.print(numberOfCards + " : Type : Spell - Name : " + collectionItem.getName() + " - MP : ");
                System.out.println(collectionItem.getMP() + " - Desc : " + collectionItem.getDesc());
                */
            }

            if(collectionItem instanceof Minion){
                numberOfCards++;
                if(haveTab)
                    System.out.print("    ");
                System.out.print("    " + numberOfCards + " : ");
                Minion minion = (Minion)collectionItem;
                minion.showMinion();
                /*
                System.out.print(numberOfCards + " : Type : Minion - Name : " + collectionItem.getName() + " - Class: ");
                System.out.print(collectionItem.getClass() + " - AP : " + collectionItem.getAP() + " - HP : ");
                System.out.print(collectionItem.getHP() + " - MP : " + collectionItem.getMP() + " - Special power : ");
                System.out.println(collectionItem.getSpecialPower());
                */
            }
        }
    }

    CollectionItem findCollectionItemInDeck(String ID){
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem.getID().equals(ID)){
                return collectionItem;
            }
        }
        return null;
    }

    public static void showAllDecks(){
        int numberOfDecks = 0;
        for(Deck deck : decks) {
            numberOfDecks++;
            System.out.println(numberOfDecks + " : " + deck.getName() + " :");
            showDeck(deck.getName(), true);
        }
    }

    //check kon ke 20 ta be hero e ya bedune hero
    public boolean checkValidateDeck(){
        ArrayList<CollectionItem> cards = this.getCards();
        if(cards.size() != 21)
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

    public void removeCollectionItemFromDeck(CollectionItem collectionItem){
        for(CollectionItem deckCollectionItem : this.getCards())
            if(deckCollectionItem.getID().equals(collectionItem.getID())){
                this.cards.remove(collectionItem);
                break;
            }
    }

    public int getNumberOfNoneHeroCards(){
        int numberOfNoneHeroCards = 0;
        for(CollectionItem collectionItem : this.getCards())
            if(collectionItem instanceof Spell || collectionItem instanceof Minion)
                numberOfNoneHeroCards++:
        return numberOfNoneHeroCards;
    }

    public void addCard(CollectionItem collectionItem){
        int numberOfCards = this.getNumberOfNoneHeroCards();
        if((collectionItem instanceof Spell || collectionItem instanceof Minion) && numberOfCards == 20){
            System.out.println("Deck is full !");
            return;
        }
        if(collectionItem instanceof Hero && this.haveHero()){
            System.out.println("This deck has already a hero !");
            return;
        }
        this.cards.add(collectionItem);
    }

    private boolean haveHero(){
        for(CollectionItem collectionItem : this.cards)
            if(collectionItem instanceof Hero)
                return true;
        return false;
    }
    //havaset bashe reference kharab nashe moghe pak kardan

    public static void removeDeck(Deck deck){
        decks.remove(deck);
    }
    //too cell havaset bashe hamaro besazi avval
    public static Deck createDeck(String deckName){
        Deck deck = getDeckByName(deckName);
        if(deck != null){
            System.out.println("a deck with this name already exists");
            return null;
        }
        deck = new Deck(deckName);
        decks.add(deck);
        return deck;
    }

    public ArrayList<CollectionItem> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

}