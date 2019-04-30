package Model.CollectionItem;

import java.util.ArrayList;

public abstract class CollectionItem {
    private String name;
    private String ID;
    private int price;
    public static ArrayList<LivingCard> allLivingCards = new ArrayList<>();
    public static ArrayList<Item> allItems = new ArrayList<>();

    public static CollectionItem getCollectionItemByID(String ID){
        for(CollectionItem collectionItem : allLivingCards){
            if(collectionItem.getID().equals(ID))
                return collectionItem;
        }
        for(CollectionItem collectionItem : allItems)
            if(collectionItem.getID().equals(ID))
                return collectionItem;
        return null;
    }
    public static LivingCard getLivingCardByID(String livingCardID){
        for(LivingCard livingCard : CollectionItem.allLivingCards)
            if(livingCard.getID().equals(livingCardID))
                return livingCard;
        System.out.println("Can't find living card !!");
        return null;
    }

    public abstract void doImpact();
    public abstract String getInfo();


    //Here is Setters && Getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
