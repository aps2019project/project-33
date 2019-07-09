//done

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
import Model.CollectionItem.*;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
            collection = (Collection) Application.copy(account.getCollection(), Collection.class);
            isFirstTime = false;
        }

        if (inputLine.equals("show")) {
            account.setCurrentMenu(MenuList.CollectionShowCollection);
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setCollection(collection);
            return serverMassage;
        } else if (inputLine.matches("search .*")) {
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setCollectionItems(searchInCollection(collection, input[1]));
            account.setCurrentMenu(MenuList.CollectionShowSearch);
            return serverMassage;
        } else if (inputLine.matches("create deck .*")) {
            String deckName = input[2];
            ServerMassage serverMassage = collection.createDeck(deckName);
            if (serverMassage.getType() == ServerMassage.Type.Accept)
                account.setCurrentMenu(MenuList.CollectionMenu);
            return serverMassage;
        } else if (inputLine.matches("delete deck .*")) {
            String deckName = input[2];
            collection.deleteDeck(deckName);
            account.setCurrentMenu(MenuList.CollectionMenu);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.matches("add .* to deck .*")) {
            String collectionItemId = input[1], deckName = input[4];
            collection.addCollectionItemToDeck(collectionItemId, deckName);
            account.setCurrentMenu(MenuList.CollectionMenu);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.matches("remove .* from .*")) {
            String collectionItemId = input[1], deckName = input[3];
            collection.removeCollectionItemFromDeck(collectionItemId, deckName);
            account.setCurrentMenu(MenuList.CollectionMenu);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.matches("validate deck .*")) {
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setValidateDeck(checkValidityOfDeck(input[2], collection));
            return serverMassage;
        } else if (inputLine.matches("select deck .*")) {
            String deckName = input[2];
            collection.selectMainDeck(deckName);
            account.setCurrentMenu(MenuList.CollectionMenu);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.equals("show all decks")) {
            collection.showAllDecks();
        } else if (inputLine.matches("show deck .*")) {
            account.setCurrentMenu(MenuList.CollectionShowDeck);
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setDeck(collection.showDeck(input[2]));
            return serverMassage;
        } else if (inputLine.equals("save")) {
            this.save(collection, account);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.equals("show menu"))
            CollectionMenu.showMenu();
        else if (inputLine.equals("exit")) {
            isFirstTime = true;
            account.setCurrentMenu(MenuList.MainMenu);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.equals("give collection")) {
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setCollection(collection);
            return serverMassage;
        } else if (inputLine.equals("export deck")) {
            account.setCurrentMenu(MenuList.CollectionMenu);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.matches("import .*")) {
            String deckName = input[1];
            return importDeck(deckName, account);
        } else
            System.out.println("Enter valid command line !");
        return null;
    }

    private ServerMassage importDeck(String deckName, Account account) throws FileNotFoundException {
        String address = "Data/ExportedDecks/" + deckName + ".json";
        Deck importedDeck = null;
        try {
            importedDeck = (Deck) Application.readJSON(Deck.class, address);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.InvalidDeckNameForImport);
        }
        Deck deck = new Deck(importedDeck.getName());
        for (CollectionItem collectionItem : importedDeck.getCards()) {
            if (collectionItem instanceof Hero) {
                Hero hero = Hero.createHero(collectionItem.getName(), account.getUsername());
                deck.getCards().add(hero);
            }
            if (collectionItem instanceof Minion) {
                Minion minion = Minion.createMinion(collectionItem.getName(), account.getUsername());
                deck.getCards().add(minion);
            }
            if (collectionItem instanceof Spell) {
                Spell spell = Spell.createSpell(collectionItem.getName(), account.getUsername());
                deck.getCards().add(spell);
            }
            if (collectionItem instanceof Item) {
                Item item = Item.createItem(collectionItem.getName(), account.getUsername());
                deck.getCards().add(item);
            }
        }
        account.getCollection().addDeck(deck);
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }

    private ArrayList<CollectionItem> searchInCollection(Collection collection, String cardName) {
        return collection.search(cardName);
    }

    private boolean checkValidityOfDeck(String deckName, Collection collection) {
        Deck deck = collection.getDeckByName(deckName);
        if (deck == null) {
            System.out.println("This deck doesn't exist");
            return false;
        }
        return deck.checkValidateDeck();
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

    private void save(Collection collection, Account account) {
        account.setCollection(collection);
    }

    public ServerMassage interpret(ClientMassage clientMassage) throws IOException {
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Exit)
            return this.inputCommandLine("exit", clientMassage.getAuthToken());
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Save)
            return this.inputCommandLine("save", clientMassage.getAuthToken());
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Show)
            return this.inputCommandLine("show", clientMassage.getAuthToken());
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.GiveCollection)
            return this.inputCommandLine("give collection", clientMassage.getAuthToken());
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.AddCollectionItemToDeck) {
            String collectionItemID = clientMassage.getSelectedCollectionItem().getID();
            String deckName = clientMassage.getSelectedDeck().getName();
            return this.inputCommandLine("add " + collectionItemID + " to deck " + deckName, clientMassage.getAuthToken());
        }
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.CreateDeck) {
            String deckName = clientMassage.getName();
            return this.inputCommandLine("create deck " + deckName, clientMassage.getAuthToken());
        }
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.DeleteDeck) {
            String deckName = clientMassage.getName();
            return this.inputCommandLine("delete deck " + deckName, clientMassage.getAuthToken());
        }
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Export) {
            Deck selectedDeck = clientMassage.getSelectedDeck();
            Application.writeJSON(selectedDeck, "Data/ExportedDecks/" + selectedDeck.getName() + ".json");
            return this.inputCommandLine("export deck", clientMassage.getAuthToken());
        }
        if (clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Import) {
            String deckName = clientMassage.getName();
            return this.inputCommandLine("import " + deckName, clientMassage.getAuthToken());
        }
        if(clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.RemoveFromDeck){
            String deckName = clientMassage.getSelectedDeck().getName();
            String collectionItemID = clientMassage.getSelectedCollectionItem().getID();
            return this.inputCommandLine("remove " + collectionItemID + " from " + deckName, clientMassage.getAuthToken());
        }
        if(clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.Search){
            String collectionItemName = clientMassage.getName();
            return this.inputCommandLine("search " + collectionItemName, clientMassage.getAuthToken());
        }
        if(clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.SelectDeck){
            String deckName = clientMassage.getName();
            return this.inputCommandLine("select deck " + deckName, clientMassage.getAuthToken());
        }
        if(clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.ShowDeck){
            String deckName = clientMassage.getSelectedDeck().getName();
            return this.inputCommandLine("show deck " + deckName, clientMassage.getAuthToken());
        }
        if(clientMassage.getCollectionMenuRequest() == ClientMassage.CollectionMenuRequest.ValidateDeck){
            String deckName = clientMassage.getSelectedDeck().getName();
            return this.inputCommandLine("validate deck " + deckName, clientMassage.getAuthToken());
        }
        return null;
    }
}
