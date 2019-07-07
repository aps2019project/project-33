//Done
//Json moonde

package Controller.Menus;

import Controller.Application;
import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.Server.Server;
import Controller.Server.ServerMain;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.*;
import Model.CollectionItem.CollectionItem;

import java.io.IOException;
import java.util.ArrayList;

public class CollectionMenu extends Menu {
    private boolean isFirstTime = true;
    private Collection collection = null;

    public ServerMassage inputCommandLine(String inputLine, String authToken) throws IOException {
//todo collection bayad chi bashe akhar ?
        Account account = Account.getAccountByUsername(authToken);

        inputLine = inputLine.trim();
        String[] input = inputLine.split("[ ]+");
        inputLine = inputLine.toLowerCase();
        if (isFirstTime) {
            collection = null; //todo (Collection) Application.copy(ServerMain.application.getLoggedInAccount().getCollection(), Collection.class);
            isFirstTime = false;
        }

        if (inputLine.equals("show")) {
            account.setCurrentMenu(MenuList.CollectionShowCollection);
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setCollection(account.getCollection());
            Client.getClient().setResultOfSearch(collection.getCollectionItems());
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.CollectionShowCollection);
            return serverMassage;
        } else if (inputLine.matches("search .*")) {
            searchInCollection(collection, input[1]);
        } else if (inputLine.matches("create deck .*")) {
            String deckName = input[2];
            collection.createDeck(deckName);
        } else if (inputLine.matches("delete deck .*")) {
            String deckName = input[2];
            collection.deleteDeck(deckName);
        } else if (inputLine.matches("add .* to deck .*")) {
            String collectionItemId = input[1], deckName = input[4];
            collection.addCollectionItemToDeck(collectionItemId, deckName);
        } else if (inputLine.matches("remove .* from .*")) {
            String collectionItemId = input[1], deckName = input[4];
            collection.removeCollectionItemFromDeck(collectionItemId, deckName);
        } else if (inputLine.matches("validate deck .*")) {
            checkValidityOfDeck(input[2], collection);
        } else if (inputLine.matches("select deck .*")) {
            String deckName = input[2];
            collection.selectMainDeck(deckName);
        } else if (inputLine.equals("show all decks")) {
            collection.showAllDecks();
        } else if (inputLine.matches("show deck .*")) {
            collection.showDeck(input[2]);
        } else if (inputLine.equals("save")) {
            this.save(collection);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.equals("show menu"))
            CollectionMenu.showMenu();
        else if (inputLine.equals("exit")) {
            isFirstTime = true;
            account.setCurrentMenu(MenuList.MainMenu);
            return new ServerMassage(ServerMassage.Type.Accept, null);
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.MainMenu);
        } else
            System.out.println("Enter valid command line !");
        return null;
    }

    private void searchInCollection(Collection collection, String cardName) {
        ArrayList<CollectionItem> collectionItems = collection.search(cardName);
        int index = 0;
        System.out.println("CollectionItems with this name :");
        for (CollectionItem collectionItem : collectionItems)
            System.out.println(++index + " " + collectionItem.getID());
    }

    private void checkValidityOfDeck(String deckName, Collection collection) {
        Deck deck = collection.getDeckByName(deckName);
        if (deck == null) {
            System.out.println("This deck doesn't exist");
            return;
        }
        System.out.println("validity state of " + deckName + " is : " + deck.checkValidateDeck());
    }

    public static void showMenu() {
        System.out.println("1. show");
        System.out.println("2. search [card name | item name]");
        System.out.println("3. create deck [deck name]");
        System.out.println("4. delete deck [deck name]");
        System.out.println("5. add [card id| item id | hero id] to deck [deck name]");
        System.out.println("6. remove [card id | item id | hero id] from deck [deck name]");
        System.out.println("7. validate deck [deck name]");
        System.out.println("8. select deck [deck name]");
        System.out.println("9. show all decks");
        System.out.println("10. show deck [deck name]");
        System.out.println("11. save");
        System.out.println("12. show menu");
        System.out.println("13. exit");
    }

    private void save(Collection collection) {

        //todo ServerMain.application.getLoggedInAccount().setCollection(collection);
    }
    public ServerMassage interpret(ClientMassage clientMassage) throws IOException {
        if(clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Exit)
            return this.inputCommandLine("exit", clientMassage.getAuthToken());
        if(clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Save)
            return this.inputCommandLine("save", clientMassage.getAuthToken());
        if(clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Show)
            return this.inputCommandLine("show", clientMassage.getAuthToken());
        return null;
    }
}
