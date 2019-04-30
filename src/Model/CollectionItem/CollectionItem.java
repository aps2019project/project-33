package Model.CollectionItem;

import java.util.ArrayList;

public abstract class CollectionItem {
    private String name;
    private String ID;
    public static ArrayList<LivingCard> allCards = new ArrayList<>();
    public static ArrayList<Item> allItems = new ArrayList<>();

    public static CollectionItem getCollectionItemByID(String ID){
        for(CollectionItem collectionItem : allCards){
            if(collectionItem.getID().equals(ID))
                return collectionItem;
        }
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
}
