//Done
//Json moonde

package Controller.Menus;

import Controller.Main;
import Model.*;
import Model.CollectionItem.CollectionItem;

import java.util.ArrayList;

public class CollectionMenu extends Menu {
    @Override
    public void inputCommandLine() {
        System.out.println("Here is Collection Menu");

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        String[] input = inputLine.split("[ ]+");

        Collection collection = Main.application.getLoggedInAccount().getCollection();

        if (inputLine.equals("show")) {
            collection.showCollection("Sell Cost");
        } else if (inputLine.matches("search .*")) {
            searchInCollection(collection, input[1]);
        } else if (inputLine.matches("create deck .*")) {
            createDeck(input[2], collection);
        } else if (inputLine.matches("delete deck .*")) {
            deleteDeck(input[2], collection);
        } else if (inputLine.matches("add .* to .*")) {
            String collectionItemId = input[1], deckName = input[3];
            collection.addCollectionItemToDeck(collectionItemId, deckName);
        } else if (inputLine.matches("remove .* from .*")) {
            String collectionItemId = input[1], deckName = input[3];
            collection.removeCollectionItemFromDeck(collectionItemId, deckName);
        } else if (inputLine.matches("validate deck .*")) {
            checkValidityOfDeck(input[2], collection);
        } else if (inputLine.matches("select deck .*")) {
            selectMainDeck(input[2], collection);
        } else if (inputLine.equals("show all decks")) {
            collection.showAllDecks();
        } else if (inputLine.matches("show deck .*")) {
            showDeck(input[2], collection);
        } else if (inputLine.equals("save")) {
            this.save();
        } else if (inputLine.equals("help"))
            CollectionMenu.showHelp();
        else if (inputLine.equals("exit"))
            return;
        else
            System.out.println("Enter valid command line !");

        this.inputCommandLine();
    }

    private void searchInCollection(Collection collection, String cardName) {
        ArrayList<String> collectionItems = collection.search(cardName);
        int index = 0;
        System.out.println("CollectionItems with this name :");
        for(String string : collectionItems)
            System.out.println(++index + " " + string);
    }

    private void showDeck(String deckName, Collection collection) {
        Deck deck = collection.getDeckByName(deckName);
        if (deck == null) {
            System.out.println("This deck doesn't exist");
            return;
        } else
            collection.showDeck(deckName);
    }

    private void selectMainDeck(String deckName, Collection collection) {
        Deck deck = collection.getDeckByName(deckName);
        if (deck == null) {
            System.out.println("This deck doesn't exist");
            return;
        }
        collection.setMainDeck(deck);
    }

    private void checkValidityOfDeck(String deckName, Collection collection) {
        Deck deck = collection.getDeckByName(deckName);
        if (deck == null) {
            System.out.println("This deck doesn't exist");
            return;
        }
        System.out.println("validity state of " + deckName + " is : " + deck.checkValidateDeck());
    }

    private void deleteDeck(String deckName, Collection collection) {
        Deck deck = collection.getDeckByName(deckName);
        if (deck == null) {
            System.out.println("This deck doesn't exist");
            this.inputCommandLine();
            return;
        }
        collection.deleteDeck(deckName);
    }

    private void createDeck(String deckName, Collection collection) {
        collection.createDeck(deckName);
    }

    public static void showHelp() {
        System.out.println("1. show");
        System.out.println("2. search[card name | item name]");
        System.out.println("3. create deck[deck name]");
        System.out.println("4. delete deck[deck name]");
        System.out.println("5. add [card id| item id | hero id] | to deck [deck name]");
        System.out.println("6. remove [card id | item id | hero id ] from deck[deck name]");
        System.out.println("7. validate deck [deck name]");
        System.out.println("8. select deck [deck name]");
        System.out.println("9. show all decks");
        System.out.println("10. show deck [deck name]");
        System.out.println("11. save");
        System.out.println("12. help");
        System.out.println("13. exit");
    }

    private void save(){
        //TODO
        //Json here, every where ?
    }
}
