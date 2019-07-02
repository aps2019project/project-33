package Controller.Menus;

import Controller.Client;
import Controller.Main;
import Controller.MenuList;
import Model.*;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.UsableItem;

import java.util.ArrayList;

public class ShopMenu extends Menu {
    private Collection shop = Main.application.getShop();

    public void inputCommandLine(String inputLine) {
        System.out.println("Here is shop !");
        System.out.println("For help, enter : show menu");

//        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] separatedInput = inputLine.split("[ ]+");
        inputLine = inputLine.toLowerCase();

        Collection collection = Main.application.getLoggedInAccount().getCollection();

        if (inputLine.equals("show collection")) {
            Client.getClient().setResultOfSearch(collection.getCollectionItems());
            collection.showCollection("Sell Cost");
            Client.getClient().setCurrentMenu(MenuList.ShopShowCollection);
        } else if (inputLine.matches("search collection .+")) {
            searchInCollection(separatedInput[2], collection);
        } else if (inputLine.matches("search .+")) {
            searchInCollection(separatedInput[1], this.shop);

            Client.getClient().setCurrentMenu(MenuList.ShowingBuy);

        } else if (inputLine.matches("buy .+")) {
            String collectionNameItem = separatedInput[1];
            this.buy(collectionNameItem);
        } else if (inputLine.matches("sell .+")) {
            String collectionItemName = separatedInput[1];
            this.sell(collectionItemName, collection);
        } else if (inputLine.equals("show"))
            this.shop.showCollection("Buy Cost");
        else if (inputLine.equals("show menu"))
            ShopMenu.showMenu();
        else if (inputLine.equals("exit")) {
            Client.getClient().setCurrentMenu(MenuList.MainMenu);
        } else
            System.out.println("Please enter valid command line !");
    }

    private void searchInCollection(String collectionItemName, Collection collection) {
        ArrayList<CollectionItem> foundCollectionItems = collection.search(collectionItemName);
        System.out.println("Result of search :");
        int index = 0;
        Client.getClient().setResultOfSearch(foundCollectionItems);
        for (CollectionItem collectionItem : foundCollectionItems) {
            System.out.println(++index + ". " + collectionItem.getID());
        }
    }

    private void sell(String collectionItemID, Collection collection) {
        CollectionItem collectionItem = collection.getCollectionItemByID(collectionItemID);
        if (collectionItem == null) {
            System.out.println("Can't find this CollectionItem");
            return;
        }

        Account customer = Main.application.getLoggedInAccount();
        customer.increaseBudget(collectionItem.getPrice());
        this.shop.addCollectionItemToCollection(collectionItem.getID());
        collection.removeCollectionItemFromCollection(collectionItem.getID());
        System.out.println("You sold it :(");
        System.out.println("remaining budget is : " + customer.getBudget());
    }

    public void buy(String name) {
        ArrayList<CollectionItem> foundCollectionItems = this.shop.search(name);
        if (foundCollectionItems.size() == 0) {
            System.out.println("There isn't this thing in shop");
            return;
        }

        CollectionItem collectionItem = foundCollectionItems.get(0);
        if (collectionItem == null) {
            System.out.println("WTF !!");
            return;
        }

        Account customer = Main.application.getLoggedInAccount();

        if (collectionItem instanceof UsableItem) {
            if (customer.getNumberOfItems() == 3) {
                System.out.println("You have 3 Items! Please sell at least 1 item at first !");
                return;
            }
        }

        if (collectionItem.getPrice() > customer.getBudget()) {
            System.out.println("Low Budget !!");
            return;
        }

        this.shop.removeCollectionItemFromCollection(collectionItem.getID());
        customer.decreaseBudget(collectionItem.getPrice());
        customer.getCollection().addCollectionItemToCollection(collectionItem.getID());
        System.out.println("You bought it :)");
        System.out.println("remaining budget is : " + customer.getBudget());
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

    //Here is Setters && Getters

    public Collection getShop() {
        return shop;
    }

    public void setShop(Collection shop) {
        this.shop = shop;
    }
}
