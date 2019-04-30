package Model;

import java.util.ArrayList;
import Controller.*;
import Model.CollectionItem.CollectableItem;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Hero;
import Model.Enviroment.Cell;
import Model.Enviroment.Map;

public class Player {

    private ArrayList<CollectionItem> usedCards = new ArrayList<CollectionItem>();
    private ArrayList<CollectableItem> collectableItems = new ArrayList<CollectableItem>();
    private CollectionItem selectedCollectionItem;
    private Cell heroPosition;
    private Mana mana;
    private Hand hand;
    private Hero hero;
    private int numberOfFlags;
    private int flagTurns;
    private GraveYard graveYard;

    public CollectionItem getSelectedCollectionItem() {
        return selectedCollectionItem;
    }

    public void setSelectedCollectionItem(CollectionItem selectedCollectionItem) {
        this.selectedCollectionItem = selectedCollectionItem;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public void setNumberOfFlags(int numberOfFlags) {
        this.numberOfFlags = numberOfFlags;
    }

    public int getFlagTurns() {
        return flagTurns;
    }

    public void setFlagTurns(int flagTurns) {
        this.flagTurns = flagTurns;
    }

    public Cell getHeroPosition() {
        return heroPosition;
    }

    public void setHeroPosition(Cell heroPosition) {
        this.heroPosition = heroPosition;
    }

    public Mana getMana() {
        return mana;
    }

    public void setMana(Mana mana) {
        this.mana = mana;
    }

    public ArrayList<CollectionItem> getUsedCards() {
        return usedCards;
    }

    public void addNewUsedCards(CollectionItem collectionItem) {
        usedCards.add(collectionItem);
    }

    public ArrayList<CollectableItem> getCollectableItems() {
        return collectableItems;
    }

    public void addNewCollectableItems(CollectableItem collectableItem) {
        collectableItems.add(collectableItem);
    }

    public void insertCard(Map map, String cardName, int x, int y) {
        map.insertCard(cardName, x, y);
    }

    public void showCollectableItems() {
        CollectableItem.showInfo();
    }

    public void attack(String opponentID, String maCardID) {
        Battle.attackToOppenentCard(opponentID, maCardID);
    }

    public void comboAttack(String opponentID, String... myCardID) {
        Battle.comboAttackToOppenentCard(opponentID, myCardID);
    }

    public GraveYard getGraveYard() {
        return graveYard;
    }

    public void setGraveYard(GraveYard graveYard) {
        this.graveYard = graveYard;
    }
}