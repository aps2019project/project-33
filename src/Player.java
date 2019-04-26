import java.util.ArrayList;

public class Player {
    public CollectionItem getSelectedCollectionItem() {
        return selectedCollectionItem;
    }

    public void setSelectedCollectionItem(CollectionItem selectedCollectionItem) {
        this.selectedCollectionItem = selectedCollectionItem;
    }

    private CollectionItem selectedCollectionItem;

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    private Hand hand;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    private Hero hero;

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public void setNumberOfFlags(int numberOfFlags) {
        this.numberOfFlags = numberOfFlags;
    }

    private int numberOfFlags;

    public int getFlagTurns() {
        return flagTurns;
    }

    public void setFlagTurns(int flagTurns) {
        this.flagTurns = flagTurns;
    }

    private int flagTurns;

    public Cell getHeroPosition() {
        return heroPosition;
    }

    public void setHeroPosition(Cell heroPosition) {
        this.heroPosition = heroPosition;
    }

    private Cell heroPosition;

    public Mana getMana() {
        return mana;
    }

    public void setMana(Mana mana) {
        this.mana = mana;
    }

    private Mana mana;

    public ArrayList<CollectionItem> getUsedCards() {
        return usedCards;
    }

    private ArrayList<CollectionItem> usedCards = new ArrayList<CollectionItem>;

    public void addNewUsedCards(CollectionItem collectionItem){
        usedCards.add(collectionItem);
    }

    public ArrayList<CollectableItem> getCollectableItems() {
        return collectableItems;
    }

    private ArrayList<CollectableItem> collectableItems = new ArrayList<CollectionItem>;

    public void addNewCollectableItems(CollectableItem collectableItem){
        collectableItems.add(collectableItem);
    }

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