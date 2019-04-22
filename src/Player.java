


public class Player {
    private CollectionItem selectedCollectionItem;
    private Hand hand;
    private Hero hero;
    private int numberOfFlags;
    private int flagTurns;
    private Cell heroPosition;
    private Mana mana;
    private Arraylist<CollectionItem> usedCards = new Arraylist<CollectionItem>;
    private Arraylist<CollectableItem> collectableItems = new Arraylist<CollectionItem>;

    public void insertCard(Map map, String cardName, int x, int y) {
        map.insertCard(cardName, x, y);
    }

    public void showCollectableItems () {
        CollectableItem.showInfo();
    }
    //attack ha ro badan miznm:)
    public void attack (String opponentID, String maCardID) {

    }

    public void comboAttack (String opponentID, String myCardID,...){

    }

}