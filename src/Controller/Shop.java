package Controller;

import Model.*;

public class Shop extends Menu {
    private Collection collection;

    public void inputCommandLine(){
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] separatedInput = inputLine.split("[ ]+");

        Collection collection = Main.application.getLoggedInAccount().getCollection();

        if(inputLine.equals("exit"))
            return;
        else if(inputLine.equals("show Model.Collection"))
            collection.showCollection();
        else if(inputLine.matches("search .+")){
            String collectionItemName = separatedInput[1];
            this.collection.search(collectionItemName);
        }
        else if(inputLine.matches("search collection *+")){
            String collectionItemName = separatedInput[2];
            Main.application.getLoggedInAccount().getCollection().search(collectionItemName);
        }
        else if(inputLine.matches("but *+")){
            String collectionNameItem = separatedInput[1];
            this.buy(collectionNameItem);
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

    public void buy(String name){
        CollectionItem collectionItem = this.collection.getCollectionItemByName(name);
        if(collectionItem == null){
            System.out.println("There isn't this thing in collection");
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
