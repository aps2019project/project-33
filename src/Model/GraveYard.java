package Model;

import java.util.ArrayList;
import Controller.*;
import Model.CollectionItem.Card;
import Model.CollectionItem.LivingCard;

public class GraveYard {
    private ArrayList<Card> cards;

    public void addCard(LivingCard livingCard){
        cards.add(livingCard);
    }

    public Card SearchCard(String cardID){
        return null;
    }

    public void showInfo(String cardID){
        for(Card card : this.cards){
            if(card.getID().equals(cardID)){
                card.showCardInCollection();
                return;
            }
        }
        System.out.println("Can't find the card !!");
        return;
    }

    public void showCards(){
        System.out.println("All cards in graveyard");
        for(Card card : this.cards){
            card.showCardInCollection();
            System.out.println(" ---- ");
        }
        return;
    }
    public void inputCommandLine(){
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");

        if(inputLine.equals("exit")){
            return;
        }
        else if(inputLine.matches("Show info *.")){
            String cardID = input[2];
            showInfo(cardID);
        }
        else if(inputLine.equals("Show cards")){
            showCards();
        }
        else
            System.out.println("Invalid Command !!!");
    }

    //Here is Setters && Getters

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
