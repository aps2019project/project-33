package Model.CollectionItem;

import Controller.Impact;
import Controller.Battle;
import Model.Buffs.Buff;
import Model.Enviroment.Cell;

import java.util.ArrayList;



abstract public class LivingCard extends Card {
    private int HP, rangeOfAttack, AP, extraHP, extraAP, numberOfSameTypeInComboAttack,
    changeRangeOfAttack, numberOfDamaged, shield, decreasHPNextRound, decreaseHP2NextRound;
    private String counterAttackType, type;
    private ArrayList<Buff> effects = new ArrayList<>();
    private boolean canCounterAttack, canMoveOrAttack, isAlive;
    private boolean canMoveGreaterTwoCell, canMove, canAttack, haveShamshireChini, haveSoulEater;
    private ArrayList<Item> Items = new ArrayList<>();
    //location mikhad

    public void addNewBuff(Buff buff){
        this.effects.add(buff);
    }
    public void deleteBuff(Buff buff){
        this.effects.remove(buff);
    }

    //Azad kardane flag, ezafe shodan be grave yard

    //alan in az too cell pak mishe ? ya az too alive card haye player ?
    public boolean checkAlive(Battle battle) {
        if (this.getHP() <= 0) {

            if (battle.getPlayerOn().getAliveCards().contains(this))
                battle.getPlayerOn().getGraveYard().addCard(this);
            else
                battle.getPlayerOff().getGraveYard().addCard(this);

            //todo moshkeli ke dare ine ke alan flag daste yeki bashe bad vel she bayad neshoon bedimesh, vali neshoon nemidim

            battle.removeAliveCard(this);
            battle.handleFlags();

            this.getCell().removeCard();

            if (battle.getSelectedCard() != null && battle.getSelectedCard().getID().equals(this.getID()))
                battle.removeSelectedCard();

            if(this.getInformation().isOnDeath())
                if(this instanceof Minion) Impact.specialAttackOfMinion(battle, (Minion)this, null);

            return false;
        }
        return true;
    }

    public Cell getCell(){
        return this.getBattle().getMap().getCellByCoordination(this.getPositionRow(), this.getPositionColumn());
    }

    //TODO
    public void handleAttack(Battle battle, int damage){
        damage = Integer.max(damage - this.getShield(), 0);
        this.setHP(this.getHP() - damage);
        this.checkAlive(battle);
    }

    public void increaseRangeOfAttack(int amount){
        this.rangeOfAttack += amount;
    }

    // Here is Setters && Getters

    public boolean getCanMoveGreaterTwoCell(){
        return this.canMoveGreaterTwoCell;
    }

    public void setCanMoveGreaterTwoCell(boolean canMoveGreaterTwoCell){
        this.canMoveGreaterTwoCell = canMoveGreaterTwoCell;
    }

    public int getHP() {
        return HP + extraHP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getRangeOfAttack() {
        return rangeOfAttack;
    }

    public void setRangeOfAttack(int rangeOfAttack) {
        this.rangeOfAttack = rangeOfAttack;
    }

    public int getAP() {
        return AP + extraAP;
    }

    //TODO
    //be in sadegi ha ham nist
    public void setAP(int AP) {
        this.AP = AP;
    }

    public int getExtraHP() {
        return extraHP;
    }

    public void setExtraHP(int extraHP) {
        this.extraHP = extraHP;
    }
    public int getExtraAP() {
        return extraAP;
    }

    public void setExtraAP(int extraAP) {
        this.extraAP = extraAP;
    }

    public int getNumberOfSameTypeInComboAttack() {
        return numberOfSameTypeInComboAttack;
    }

    public void setNumberOfSameTypeInComboAttack(int numberOfSameTypeInComboAttack) {
        this.numberOfSameTypeInComboAttack = numberOfSameTypeInComboAttack;
    }

    public int getChangeRangeOfAttack() {
        return changeRangeOfAttack;
    }

    public void setChangeRangeOfAttack(int changeRangeOfAttack) {
        this.changeRangeOfAttack = changeRangeOfAttack;
    }

    public int getNumberOfDamaged() {
        return numberOfDamaged;
    }

    public void setNumberOfDamaged(int numberOfDamaged) {
        this.numberOfDamaged = numberOfDamaged;
    }

    public String getCounterAttackType() {
        return counterAttackType;
    }

    public void setCounterAttackType(String counterAttackType) {
        this.counterAttackType = counterAttackType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Buff> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Buff> effects) {
        this.effects = effects;
    }

    public boolean isCanCounterAttack() {
        return canCounterAttack;
    }

    public void setCanCounterAttack(boolean canCounterAttack) {
        this.canCounterAttack = canCounterAttack;
    }

    public boolean isCanMoveOrAttack() {
        return canMoveOrAttack;
    }

    public void setCanMoveOrAttack(boolean canMoveOrAttack) {
        this.canMoveOrAttack = canMoveOrAttack;
    }

    public ArrayList<Item> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Item> items) {
        Items = items;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean isCanMoveGreaterTwoCell() {
        return canMoveGreaterTwoCell;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void changeHP(int amount) {
        this.HP += amount;
    }

    public void changeAP(int amount){
        this.AP += amount;
    }

    public boolean isHaveShamshireChini() {
        return haveShamshireChini;
    }

    public void setHaveShamshireChini(boolean haveShamshireChini) {
        this.haveShamshireChini = haveShamshireChini;
    }

    public boolean isHaveSoulEater() {
        return haveSoulEater;
    }

    public void setHaveSoulEater(boolean haveSoulEater) {
        this.haveSoulEater = haveSoulEater;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getDecreasHPNextRound() {
        return decreasHPNextRound;
    }

    public void setDecreasHPNextRound(int decreasHPNextRound) {
        this.decreasHPNextRound = decreasHPNextRound;
    }

    public int getDecreaseHP2NextRound() {
        return decreaseHP2NextRound;
    }

    public void setDecreaseHP2NextRound(int decreaseHP2NextRound) {
        this.decreaseHP2NextRound = decreaseHP2NextRound;
    }

    public void changeExtraAP(int amount){
        this.extraAP += amount;
    }

    public void changeExtraHP(int amount){
        this.extraHP += amount;
    }
}
