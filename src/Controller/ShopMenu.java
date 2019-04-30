package Controller;

import Controller.Menus.Menu;
import Model.*;
import Model.CollectionItem.Card;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.UsableItem;

import java.util.ArrayList;

public class ShopMenu extends Menu {
    private Collection collection;

    public void inputCommandLine(){
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] separatedInput = inputLine.split("[ ]+");

        Collection collection = Main.application.getLoggedInAccount().getCollection();

        if(inputLine.equals("exit"))
            return;
        else if(inputLine.equals("show Collection"))
            collection.showCollection("Sell Cost");
        else if(inputLine.matches("search .+")){
            searchInCollection(separatedInput[1], this.collection);
        }
        else if(inputLine.matches("search collection *+")){
            searchInCollection(separatedInput[2], collection);
        }
        else if(inputLine.matches("buy *+")){
            String collectionNameItem = separatedInput[1];
            this.buy(collectionNameItem);
        }
        else if(inputLine.matches("sell *+")){
            String collectionItemName = separatedInput[1];
            this.sell(collectionItemName, collection);
        }
        else if(inputLine.equals("show"))
            this.collection.showCollection("But Cost");
        else if(inputLine.equals("help"))
            ShopMenu.showHelp();
        else
            System.out.println("Please enter valid command line !");
    }

    private void searchInCollection(String collectionItemName, Collection collection) {
        ArrayList<String> IDs = collection.search(collectionItemName);
        System.out.println("Result of search :");
        for(String ID : IDs)
            System.out.println(ID);
    }

    private void sell(String collectionItemName, Collection collection) {
        ArrayList<String> Ids = collection.search(collectionItemName);
        if(Ids.size() == 0){
            System.out.println("You haven't this id !");
            return;
        }
        CollectionItem collectionItem = CollectionItem.getCollectionItemByID(Ids.get(0));
        if(collectionItem == null){
            System.out.println("Again wtf !");
            return;
        }

        Account costumer = Main.application.getLoggedInAccount();

        costumer.getCollection().removeCollectionItemFromCollection(collectionItem.getID());
        costumer.increaseBudget(collectionItem.getPrice());
        this.collection.addCollectionItemToCollection(collectionItem.getID());
    }

    public void buy(String name){
        ArrayList<String> Ids = this.collection.search(name);
        if(Ids.size() == 0){
            System.out.println("There isn't this thing in collection");
            return;
        }
        CollectionItem collectionItem = CollectionItem.getCollectionItemByID(Ids.get(0));
        if(collectionItem == null){
            System.out.println("WTF !!");
            return;
        }

        Account costumer = Main.application.getLoggedInAccount();

        if(collectionItem instanceof UsableItem) {
            if(costumer.getNumberOfItems() == 3){
                System.out.println("You have 3 Items! Please sell at least 1 item at first !");
                return;
            }
        }

        if(collectionItem.getPrice() > costumer.getBudget()){
            System.out.println("Low Budget !!");
            return;
        }
        this.collection.removeCollectionItemFromCollection(collectionItem.getName());
        costumer.decreaseBudget(collectionItem.getPrice());
        costumer.getCollection().addCollectionItemToCollection(collectionItem.getName());

        System.out.println("You buy it :)");
    }

    public static void showHelp(){
        System.out.println("1. exit");
        System.out.println("2. show collection");
        System.out.println("3. search  [item name | card name]");
        System.out.println("4. search collection [item name | card name]");
        System.out.println("5. buy [card name | item name]");
        System.out.println("6. sell [card name | item name]");
        System.out.println("7. show");
        System.out.println("8. help");
    }

    //Here is Setters && Getters

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
