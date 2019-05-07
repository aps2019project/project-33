package Model;

import java.util.ArrayList;
import Controller.*;
import Model.CollectionItem.Card;
import Model.CollectionItem.LivingCard;

public class GraveYard {
    private ArrayList<Card> cards = new ArrayList<>();

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
        System.out.println("All cards in graveyard :");
        for(Card card : this.cards){
            card.showCardInCollection();
        }
        return;
    }

    public void showMenu(){
        System.out.println("1. show info [card id]");
        System.out.println("2. show cards");
        System.out.println("3. show menu");
        System.out.println("4. exit");
    }

    public void inputCommandLine(){
        System.out.println("Here is GraveYard");
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        String[] input = inputLine.split("[ ]+");

        if(inputLine.equals("exit")){
            return;
        }
        else if(inputLine.matches("show info .*")){
            String cardID = input[2];
            showInfo(cardID);
        }
        else if(inputLine.equals("show cards")){
            showCards();
        } else if(inputLine.equals("show menu")){
            showMenu();
        }
        else
            System.out.println("Invalid Command !!!");
        inputCommandLine();;
    }


    public void addCard(LivingCard livingCard){
        cards.add(livingCard);
    }

    public Card SearchCard(String cardID){
        return null;
    }

    //Here is Setters && Getters

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
