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
            this.buy(collectionNameItem, this.collection);
        }
        else if(inputLine.matches("sell *+")){
            String collectionItemName = separatedInput[1];
            this.sell(collectionItemName);
        }
        else if(inputLine.equals("show"))
            this.collection.showCollection();
        else if(inputLine.equals("help"))
            this.help();
        else
            System.out.println("Please enter valid command line !");
    }

    private void searchInCollection(String collectionItemName, Collection collection) {
        ArrayList<String> IDs = collection.search(collectionItemName);
        System.out.println("Result of search :");
        for(String ID : IDs)
            System.out.println(ID);
    }

    public void buy(String name, Collection collection){
        ArrayList<String> Ids = collection.search(name);
        if(Ids.size() == 0){
            System.out.println("There isn't this thing in collection");
            return;
        }
        if(collectionItem instanceof UsableItem) this.buyItem((UsableItem)collectionItem);
        else this.buyCard((Card) collectionItem);
    }

    public void buyItem(UsableItem item){
        if(item.getPrice() > Main.application.getLoggedInAccount().getBudget()){
            System.out.println("Low Budget !!");
            return;
        }
        if(Main.application.getLoggedInAccount().getNumberOfItems == 3){
            System.out.println("You have 3 Items! Please sell at least 1 item at first !");
            return;
        }
        this.collection.removeCardFromCollection(item.getName());
        Main.application.getLoggedInAccount().decreaseBudget(item.getPrice());
        Main.application.getLoggedInAccount().getCollection().addCardToCollection(item.getName());
    }

    public void buyCard(Card card){
        if(card.getPrice() > Main.application.getLoggedInAccount().getBudget()){
            System.out.println("Low Budget!");
            return;
        }
        this.collection.removeCardFromCollection(card.getName());
        Main.application.getLoggedInAccount().decreaseBudget(card.getPrice());
        Main.application.getLoggedInAccount().getCollection().addCardToCollection(card.Name);
    }

    //Here is Setters && Getters

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
