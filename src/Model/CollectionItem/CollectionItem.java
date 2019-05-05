package Model.CollectionItem;

import java.util.ArrayList;

public abstract class CollectionItem {
    private Information information;
    private String name;
    private String ID;
    private int price;
    private String description;
    public static ArrayList<LivingCard> allLivingCards = new ArrayList<>();
    public static ArrayList<Item> allItems = new ArrayList<>();
    public static ArrayList<Spell> allSpells = new ArrayList<>();

    public static ArrayList<LivingCard> getAllLivingCards(){
        return allLivingCards;
    }

    public static void addLivingCardToAllLivingCards(LivingCard livingCard){
        allLivingCards.add(livingCard);
    }

    public static ArrayList<Spell> getAllSpells(){
        return allSpells;
    }

    public static void addSpellToAllSpells(Spell spell){
        allSpells.add(spell);
    }

    public static ArrayList<Item> getAllItems(){
        return allItems;
    }

    public static CollectionItem getCollectionItemByID(String ID){
        for(CollectionItem collectionItem : allLivingCards){
            if(collectionItem.getID().equals(ID))
                return collectionItem;
        }
        for(CollectionItem collectionItem : allItems)
            if(collectionItem.getID().equals(ID))
                return collectionItem;
        for(CollectionItem collectionItem : allSpells)
            if(collectionItem.getID().equals(ID))
                return collectionItem;
        System.out.println("Can't find collection item !!");
        return null;
    }
    public static LivingCard getLivingCardByID(String livingCardID){
        for(LivingCard livingCard : CollectionItem.allLivingCards)
            if(livingCard.getID().equals(livingCardID))
                return livingCard;
        System.out.println("Can't find living card !!");
        return null;
    }

    public static Item getItemById(String itemID){
        for(Item item : CollectionItem.allItems)
            if(item.getID().equals(itemID))
                return item;
        System.out.println("Can't find item !!");
        return null;
    }

    public static Spell getSpellById(String spellID){
        for(Spell spell : CollectionItem.allSpells)
            if(spell.getID().equals(spellID))
                return spell;
        System.out.println("Can't find spell !!");
        return null;
    }

    public static int readCoolDownTime(LivingCard livingCard){
        //TODO
        return 0;
    }

    public void doImpact(String livingCardID){}
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }
}
