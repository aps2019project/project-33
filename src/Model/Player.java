package Model;

import java.util.ArrayList;

import Model.CollectionItem.CollectableItem;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Hero;
import Model.CollectionItem.LivingCard;
import Model.Enviroment.Cell;
import Model.Enviroment.Map1;

public class Player {
    private ArrayList<LivingCard> aliveCards = new ArrayList<>();
    private ArrayList<CollectableItem> collectableItems = new ArrayList<>();
    private CollectionItem selectedCollectionItem;
    private Cell heroPosition;
    private Mana mana = new Mana();
    private Hand1 hand = new Hand1();
    private Hero hero;
    private int numberOfFlags;
    private int flagTurns;
    private GraveYard graveYard = new GraveYard();
    private Account account;
    private boolean haveGhosleTamid, haveTerrorHood;
    private int timeOfGhosleTamid;
    private boolean canAddPoisonWhileAttacking, canAddStunWhileAttacking;

    public ArrayList<LivingCard> getAliveCards(){
        return this.aliveCards;
    }

    public void addAliveCard(LivingCard livingCard){
        this.aliveCards.add(livingCard);
    }

    public void removeDeadCard(LivingCard deadCard){
        for(LivingCard livingCard : this.getAliveCards()){
            if(livingCard.getID().equals(deadCard.getID())){
                this.aliveCards.remove(livingCard);
                this.graveYard.addCard(deadCard);
                break;
            }
        }
    }

    public Player(Account account){
        this.account = account;
    }

    public CollectionItem getSelectedCollectionItem() {
        return selectedCollectionItem;
    }

    public void setSelectedCollectionItem(CollectionItem selectedCollectionItem) {
        this.selectedCollectionItem = selectedCollectionItem;
    }

    public Hand1 getHand() {
        return hand;
    }

    public void setHand(Hand1 hand) {
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

    public ArrayList<CollectableItem> getCollectableItems() {
        return collectableItems;
    }

    public void addNewCollectableItems(CollectableItem collectableItem) {
        collectableItems.add(collectableItem);
    }

    public void insertCard(Map1 map, String cardName, int x, int y) {
        map.insertCard(cardName, x, y);
    }

    public void showCollectableItems() {
    //    CollectableItem.showInfo();
    }

    public void attack(String opponentID, String maCardID) {
    //    Battle.attackToOppenentCard(opponentID);
    }

    public void comboAttack(String opponentID, String... myCardID) {
    //    Battle.comboAttackToOppenentCard(opponentID, myCardID);
    }

    public GraveYard getGraveYard() {
        return graveYard;
    }

    public void setGraveYard(GraveYard graveYard) {
        this.graveYard = graveYard;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isHaveGhosleTamid() {
        return haveGhosleTamid;
    }

    public void setHaveGhosleTamid(boolean haveGhosleTamid) {
        this.haveGhosleTamid = haveGhosleTamid;
    }

    public int getTimeOfGhosleTamid() {
        return timeOfGhosleTamid;
    }

    public void setTimeOfGhosleTamid(int timeOfGhosleTamid) {
        this.timeOfGhosleTamid = timeOfGhosleTamid;
    }

    public boolean isCanAddPoisonWhileAttacking() {
        return canAddPoisonWhileAttacking;
    }

    public void setCanAddPoisonWhileAttacking(boolean canAddPoisonWhileAttacking) {
        this.canAddPoisonWhileAttacking = canAddPoisonWhileAttacking;
    }

    public boolean isCanAddStunWhileAttacking() {
        return canAddStunWhileAttacking;
    }

    public void setCanAddStunWhileAttacking(boolean canAddStunWhileAttacking) {
        this.canAddStunWhileAttacking = canAddStunWhileAttacking;
    }

    public boolean isHaveTerrorHood() {
        return haveTerrorHood;
    }

    public void setHaveTerrorHood(boolean haveTerrorHood) {
        this.haveTerrorHood = haveTerrorHood;
    }
}