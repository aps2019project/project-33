package Controller.Menus;

import Controller.Main;
import Model.*;

public class CollectionMenu extends Menu {
    @Override
    public void inputCommandLine() {
        System.out.println("Here is Collection Menu");

        if(Main.application.getLoggedInAccount() == null){
            System.out.println("Please login first !!");
            return;
        }

        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        inputLine = inputLine.toLowerCase();
        String[] input = inputLine.split("[ ]+");

        Collection collection = Main.application.getLoggedInAccount().getCollection();

        if (inputLine.equals("exit"))
            return;
        else if (inputLine.equals("show")) {
            collection.showCollection("Sell Cost");
        } else if (inputLine.equals("save")) {
            collection.save();
        } else if (inputLine.matches("create deck *.")) {
            createDeck(input[2], collection);
        } else if (inputLine.matches("delete deck *.")) {
            deleteDeck(input[2], collection);
        } else if (inputLine.matches("add *. to *.")) {
            String collectionItemId = input[1], deckName = input[3];
            collection.addCollectionItemToDeck(collectionItemId, deckName);
        } else if (inputLine.matches("remove *. from *.")) {
            String collectionItemId = input[1], deckName = input[3];
            collection.removeCollectionItemFromDeck(collectionItemId, deckName);
        } else if (inputLine.matches("validate deck *.")) {
            checkValidityOfDeck(input[2], collection);
        } else if (inputLine.matches("select deck *.")) {
            selectMainDeck(input[2], collection);
        } else if (inputLine.equals("show all decks")) {
            collection.showAllDecks();
        } else if (inputLine.matches("show deck *.")) {
            showDeck(input[2], collection);
        } else if (inputLine.equals("help"))
            CollectionMenu.showHelp();
        else
            System.out.println("Enter valid command line !");
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
        System.out.println("1. exit");
        System.out.println("2. show");
        System.out.println("3. search[card name | item name]");
        System.out.println("4. save");
        System.out.println("5. create deck[deck name]");
        System.out.println("6. create deck[deck name]");
        System.out.println("7. add [card id| item id | hero id] | to deck [deck name]");
        System.out.println("8. remove [card id | item id | hero id ] from deck[deck name]");
        System.out.println("9. validate deck [deck name]");
        System.out.println("10. select deck[deck name]");
        System.out.println("11. show all decks");
        System.out.println("12. show deck [deck name]");
        System.out.println("13. help");
    }
}
