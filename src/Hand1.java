import java.util.ArrayList;

public class Hand {
    ArrayList<CollectionItem> handCards = new ArrayList<CollectionItem>();
   //havaset bashe to taghire in
    int numberOfUsedDeckCollectionItems = 0;

    void showSpells(){
        for(CollectionItem collectionItem : handCards){
            if(collectionItem instanceof Spell) {
                Spell spell = (Spell)collectionItem;
                spell.showSpell();
            }
        }
    }

    //showMinion o showSpell tuye classashun ezafe beshan

    void showMinions(){
        for(CollectionItem collectionItem : handCards)
            if(collectionItem instanceof Minion){
                Minion minion = (Minion) collectionItem;
                minion.showMinion();
            }
    }
    //nazadam
    void showNextCard(Deck mainDeck){
        if(numberOfUsedDeckCollectionItems >= mainDeck.getCards().size()){
            System.out.println("next card doesn't exist!");
            return;
        }
        CollectionItem collectionItem = mainDeck.getCards().get(numberOfUsedDeckCollectionItems);
        System.out.println(collectionItem.getName());
    }

    void show(Deck mainDeck){
        System.out.println("spells : \n");
        this.showSpells();
        System.out.println("\nheroes : \n");
        this.showMinions();
        showNextCard(mainDeck);
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

    ArrayList<CollectionItem> getHand(){
        return handCards;
    }

}
