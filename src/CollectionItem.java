import java.util.ArrayList;

public abstract class CollectionItem {
    private String name;
    private String ID;
    public static ArrayList<LivingCard> allCards = new ArrayList<>();
    public static ArrayList<Item> allItems = new ArrayList<>();

    public String getID(){
        return this.ID;
    }

    public static CollectionItem getCollectionItemByID(String ID){
        for(CollectionItem collectionItem : allCards){
            if(collectionItem.ID.equals(ID))
                return collectionItem;
        }
        return null;
    }

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
