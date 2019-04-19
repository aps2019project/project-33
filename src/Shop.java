import java.awt.*;
import java.util.Scanner;

public class Shop extends Menu {
    private Collection collection;

    public void inputCommandLine(){
        String inputLine = Main.scanner.nextLine();
        inputLine = inputLine.trim();
        String[] separatedInput = inputLine.split("[ ]+");

        if(inputLine.equals("exit"))
            return;
        else if(inputLine.equals("show Collection"))
            Main.application.getLoggedInAccount().getCollection().showCollection();
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

    //Here is Setters && Getters

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
