import java.util.ArrayList;

public class Handd {
    ArrayList<CollectionItem> handCards = new ArrayList<CollectionItem>();

    void showSpells(){
        for(CollectionItem collectionItem : collectionItem){
            if(collectionItem instanceof Spell)
                collectionItem.showSpell();
        }
    }

    //showMinion o showSpell tuye classashun ezafe beshan

    void showMinions(){
        for(CollectionItem collectionItem : handCards)
            if(collectionItem instanceof Minion)
                collectionItem.showMinion();
    }
//nazadam
    void showNextCard(){
        //????
    }

    void show(){
        System.out.println("spells : \n");
        this.showSpells();
        System.out.println("\nheroes : \n");
        this.showMinions();
        showLastCard();
    }

    void removeCard(String cardID){
        for(CollectionItem collectionItem : handCards){
            if(collectionItem.getID().equals(cardID)){
                handCards.remove(collectionItem);
                break;
            }
        }
    }

    void addCard(CollectionItem collectionItem){
        handCards.add(collectionItem);
    }

    CollectionItem getCollectionItemByID(String collectionItemID){
        for(CollectionItem collectionItem : handCards)
            if(collectionItem.getID().equals(collectionItemID))
                return collectionItem;
        return null;
    }
//havaset be tartibe ina bashe
    CollectionItem getCollectionItemByIndex(int index){
        return handCards.get(index);
    }

    void getHand(){
        removeCard(handCards);
    }

}
