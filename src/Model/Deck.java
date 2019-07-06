package Model;

import Model.CollectionItem.*;

import java.util.ArrayList;
import java.util.*;

public class Deck{
    private ArrayList<CollectionItem> cards = new ArrayList<>();
    private String name;

    //constructor

    public Deck(String name) {
        this.name = name;
    }


// ye string desc o special power bayad be carda ezafe she
    public void showDeck(boolean haveTab){
        if(haveTab)
            System.out.print("    ");
        System.out.println("Heroes :");
        int numberOfHeroes = 0;
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Hero){
                numberOfHeroes++;
                //desc bayaad too collectionitem bashe ? + class baraye hero
                if(haveTab)
                    System.out.print("    ");
                System.out.print("    " + numberOfHeroes + " : " + collectionItem.getInfo() + "\n");
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
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Item){
                numberOfItems++;
                if(haveTab)
                    System.out.print("    ");
                System.out.print("    " + numberOfItems + " : " + collectionItem.getInfo() + "\n");
                /*
                System.out.println(numberOfItems + " : Name : " + collectionItem.getName() + " - Desc " + collectionItem.getDesc());
                */
            }
        }
        int numberOfCards = 0;
        if(haveTab)
            System.out.print("    ");
        System.out.println("Cards :");
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem instanceof Spell){
                numberOfCards++;
                if(haveTab)
                    System.out.print("    ");
                System.out.print("    " + numberOfCards + " : " + collectionItem.getInfo() + "\n");
                /*
                System.out.print(numberOfCards + " : Type : Spell - Name : " + collectionItem.getName() + " - MP : ");
                System.out.println(collectionItem.getMP() + " - Desc : " + collectionItem.getDesc());
                */
            }

            if(collectionItem instanceof Minion){
                numberOfCards++;
                if(haveTab)
                    System.out.print("    ");
                System.out.print("    " + numberOfCards + " : " + collectionItem.getInfo() + "\n");
                /*
                System.out.print(numberOfCards + " : Type : Minion - Name : " + collectionItem.getName() + " - Class: ");
                System.out.print(collectionItem.getClass() + " - AP : " + collectionItem.getAP() + " - HP : ");
                System.out.print(collectionItem.getHP() + " - MP : " + collectionItem.getMP() + " - Special power : ");
                System.out.println(collectionItem.getSpecialPower());
                */
            }
        }
    }

    public CollectionItem findCollectionItemInDeck(String ID){
        for(CollectionItem collectionItem : this.getCards()){
            if(collectionItem.getID().equals(ID)){
                return collectionItem;
            }
        }
        return null;
    }

    //check kon ke 20 ta ba hero e ya bedune hero
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
                numberOfNoneHeroCards++;
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

    public void shuffle(){
        Collections.shuffle(this.cards);
    }

    private boolean haveHero(){
        for(CollectionItem collectionItem : this.cards)
            if(collectionItem instanceof Hero)
                return true;
        return false;
    }
    //havaset bashe reference kharab nashe moghe pak kardan
    //too cell havaset bashe hamaro besazi avval

    public ArrayList<CollectionItem> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }


}