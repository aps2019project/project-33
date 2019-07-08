package Controller.Menus;

import Controller.Client.Client;
import Controller.Client.ClientMassage;
import Controller.Server.Server;
import Controller.Server.ServerMain;
import Controller.MenuList;
import Controller.Server.ServerMassage;
import Model.*;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Hero;
import Model.CollectionItem.UsableItem;
import View.CollectionMenu.AddCardToDeck.SelectDeckController;

import java.util.ArrayList;

public class ShopMenu extends Menu {
    private Collection shop = ServerMain.application.getShop();

    public ServerMassage inputCommandLine(String inputLine, String authToken) {
        Account account = Account.getAccountByUsername(authToken);
        System.out.println("Here is shop !");
        System.out.println("For help, enter : show menu");

//        String inputLine = ServerMain.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] separatedInput = inputLine.split("[ ]+");
        inputLine = inputLine.toLowerCase();

        Collection collection = null; //todo = ServerMain.application.getLoggedInAccount().getCollection();
        if (inputLine.equals("show collection for sell")) {
            Client.getClient().setResultOfSearch(collection.getCollectionItems());
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.SellMenu);
        }
        if (inputLine.equals("show collection")) {
            Client.getClient().setResultOfSearch(collection.getCollectionItems());
            collection.showCollection("Sell Cost");
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShopShowCollection);

        } else if (inputLine.matches("search for show .+")) {
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setCollectionItems(searchInCollection(separatedInput[3], this.shop));
            return serverMassage;
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShopShowSearch);
        } else if (inputLine.matches("search collection .+")) {
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setCollectionItems(searchInCollection(separatedInput[2], account.getCollection()));
            return serverMassage;
        } else if (inputLine.matches("search .+")) {
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setCollectionItems(searchInCollection(separatedInput[1], this.shop));
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShowingBuy);
            return serverMassage;
        } else if (inputLine.matches("buy .+")) {
            String collectionNameItem = separatedInput[1];
            return this.buy(collectionNameItem, authToken);
        } else if (inputLine.matches("sell .+")) {
            String collectionItemID = separatedInput[1];
            this.sell(collectionItemID, account.getCollection(), authToken);
            return new ServerMassage(ServerMassage.Type.Accept, null);
        } else if (inputLine.equals("show")) {
            this.shop.showCollection("Buy Cost");
            Client.getClient().setResultOfSearch(shop.getCollectionItems());
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.ShowShop);
        } else if (inputLine.equals("show menu"))
            ShopMenu.showMenu();
        else if (inputLine.equals("exit")) {
            //todo in bayad doros she
            // Client.getClient().setCurrentMenu(MenuList.MainMenu);
        } else if(inputLine.equals("give collection")){
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setCollection(account.getCollection());
            return serverMassage;
        }
        else if(inputLine.equals("give shop")){
            ServerMassage serverMassage = new ServerMassage(ServerMassage.Type.Accept, null);
            serverMassage.setShopCollection(this.shop);
            return serverMassage;
        }
        else
            System.out.println("Please enter valid command line !");
        return null;
    }

    private ArrayList<CollectionItem> searchInCollection(String collectionItemName, Collection collection) {
        ArrayList<CollectionItem> foundCollectionItems = collection.search(collectionItemName);
        System.out.println("Result of search :");
        int index = 0;
        for (CollectionItem collectionItem : foundCollectionItems) {
            System.out.println(++index + ". " + collectionItem.getID());
        }
        return foundCollectionItems;
    }

    private void sell(String collectionItemID, Collection collection, String authToken) {
        CollectionItem collectionItem = collection.getCollectionItemByID(collectionItemID);
        if (collectionItem == null) {
            System.out.println("Can't find this CollectionItem");
            return;
        }

        Account customer = Account.getAccountByUsername(authToken); //todo ServerMain.application.getLoggedInAccount();
        customer.increaseBudget(collectionItem.getPrice());
        this.shop.addCollectionItemToCollection(collectionItem.getID());
        collection.removeCollectionItemFromCollection(collectionItem.getID());
        System.out.println("You sold it :(");
        System.out.println("remaining budget is : " + customer.getBudget());
    }

    public ServerMassage buy(String name, String authToken) {
        ArrayList<CollectionItem> foundCollectionItems = this.shop.search(name);
        if (foundCollectionItems.size() == 0) {
            System.out.println("There isn't this thing in shop");
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.NotFoundCollectionItem);
        }

        CollectionItem collectionItem = foundCollectionItems.get(0);
        if (collectionItem == null) {
            System.out.println("WTF !!");
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.WTF);
        }

        Account customer = Account.getAccountByUsername(authToken); //todo ServerMain.application.getLoggedInAccount();

        if (collectionItem instanceof UsableItem) {
            if (customer.getNumberOfItems() == 3) {
                System.out.println("You have 3 Items! Please sell at least 1 item at first !");
                return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.CantBuyItem);
            }
        }

        if (collectionItem.getPrice() > customer.getBudget()) {
            System.out.println("Low Budget !!");
            return new ServerMassage(ServerMassage.Type.Error, ServerMassage.ErrorType.LowBudget);
        }

        this.shop.removeCollectionItemFromCollection(collectionItem.getID());
        customer.decreaseBudget(collectionItem.getPrice());
        customer.getCollection().addCollectionItemToCollection(collectionItem.getID());
        System.out.println("You bought it :)");
        System.out.println("remaining budget is : " + customer.getBudget());
        return new ServerMassage(ServerMassage.Type.Accept, null);
    }

    public static void showMenu() {
        System.out.println("1. show collection");
        System.out.println("2. search [item name | card name]");
        System.out.println("3. search shop [item name | card name]");
        System.out.println("4. buy [card name | item name]");
        System.out.println("5. sell [card name | item name]");
        System.out.println("6. show");
        System.out.println("7. show menu");
        System.out.println("8. exit");
    }

    public ServerMassage interpret(ClientMassage clientMassage){
        if(clientMassage.getShopMenuRequest() == ClientMassage.ShopMenuRequest.Search){
            String name = clientMassage.getName();
            return this.inputCommandLine("search " + name, clientMassage.getAuthToken());
        }
        if(clientMassage.getShopMenuRequest() == ClientMassage.ShopMenuRequest.Buy){
            String collectionItemName = clientMassage.getName();
            return this.inputCommandLine("buy " + collectionItemName, clientMassage.getAuthToken());
        }
        if(clientMassage.getShopMenuRequest() == ClientMassage.ShopMenuRequest.SearchInCollection){
            String collectionItemName = clientMassage.getName();
            return this.inputCommandLine("search collection " + collectionItemName, clientMassage.getAuthToken());
        }
        if (clientMassage.getShopMenuRequest() == ClientMassage.ShopMenuRequest.SearchInShop){
            String collectionItemName = clientMassage.getName();
            return this.inputCommandLine("search for show " + collectionItemName, clientMassage.getAuthToken());
        }
        if(clientMassage.getShopMenuRequest() == ClientMassage.ShopMenuRequest.GiveCollection){
            return this.inputCommandLine("give collection", clientMassage.getAuthToken());
        }
        if(clientMassage.getShopMenuRequest() == ClientMassage.ShopMenuRequest.Sell){
            String collectionItemID = clientMassage.getName();
            return this.inputCommandLine("sell " + collectionItemID, clientMassage.getAuthToken());
        }
        if(clientMassage.getShopMenuRequest() == ClientMassage.ShopMenuRequest.GiveShop){
            return this.inputCommandLine("give shop", clientMassage.getAuthToken());
        }
        return null;
    }

    //Here is Setters && Getters

    public Collection getShop() {
        return shop;
    }

    public void setShop(Collection shop) {
        this.shop = shop;
    }
}
