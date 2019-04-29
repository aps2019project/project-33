package Model;

import java.util.ArrayList;

public abstract class CollectionItem {
    private String name;
    public static ArrayList<LivingCard> allCards = new ArrayList<>();
    public static ArrayList<Item> allItems = new ArrayList<>();

    abstract public void doImpact();

    //Here is Setters && Getters

    public static LivingCard getLivingCardByID(String livingCardID){
        for(LivingCard livingCard : CollectionItem.allCards)
            if(livingCard.getID().equals(livingCardID))
                return livingCard;
        System.out.println("Can't find living card !!");
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
