package Model.CollectionItem;

import Controller.AttackArea;
import Controller.Impact;
import Model.Buffs.Buff;
import Model.Enviroment.Cell;

import java.awt.event.ItemEvent;
import java.util.ArrayList;

abstract public class LivingCard extends Card {
    private int HP, remainingHP, rangeOfAttack, decreaseHPByAttack, changeHP, changePower, numberOfSameTypeInComboAttack,
    changeRangeOfAttack, numberOfDamaged;
    private String counterAttackType, type;
    private ArrayList<Buff> effects;
    private boolean canCounterAttack, canMoveOrAttack, isAlive;
    private boolean canMoveGreaterTwoCell, canMove, canAttack;
    private ArrayList<Item> Items = new ArrayList<>();
    //location mikhad
    private int coolDown;


    public void addNewBuff(Buff buff){
        this.effects.add(buff);
    }
    public void deleteBuff(Buff buff){
        this.effects.remove(buff);
    }

    public ArrayList<Cell> findImpactCellsOfAttack(){
        return AttackArea.getImpactCellsOfAttack(this);
    }

    public void attack(String opponentID){
        if(!canAttack){
            System.out.println("This living card can't attack this round !");
            return;
        }

        LivingCard opponentCard = CollectionItem.getLivingCardByID(opponentID);

        ArrayList<Cell> impactCells = this.findImpactCellsOfAttack();
        boolean canAttackToOpponent = false;
        for(Cell cell : impactCells){
            if(cell.getX() == opponentCard.getPositionRow() && cell.getY() == opponentCard.getPositionColumn())
                canAttackToOpponent = true;
        }
        if(!canAttackToOpponent){
            System.out.println("This opponent isn't in impact area !1");
            return;
        }

        Impact.attack(this, this, opponentCard);

        this.canAttack = false;
    }

    public ArrayList<Cell> findImpactCellsOfCounterAttack() {
        return AttackArea.getImpactCellsOfCounterAttack(this);
    }

    public void counterAttack(String opponentID){
        if(!canCounterAttack){
            System.out.println("This living card can't counter attack");
            return;
        }

        LivingCard opponentCard = CollectionItem.getLivingCardByID(opponentID);

        boolean canConuterAttackToOpponent = false;
        ArrayList<Cell> impactCells = this.findImpactCellsOfCounterAttack();
        for(Cell cell : impactCells){
            if(cell.getX() == opponentCard.getPositionRow() && cell.getY() == opponentCard.getPositionColumn())
                canConuterAttackToOpponent = true;
        }

        if(!canConuterAttackToOpponent){
            System.out.println("The opponent card is not in counter attack impact area !!");
            return;
        }
        Impact.counterAttack(this, opponentCard);
    }

    public Cell getCell(){
        return this.getBattle().getMap().getCellByCoordination(this.getPositionRow(), this.getPositionColumn());
    }

    public void handleAttack(int damage){}

    public void kill(){}

    // Here is Setters && Getters

    public boolean getCanMoveGreaterTwoCell(){
        return this.canMoveGreaterTwoCell;
    }

    public void setCanMoveGreaterTwoCell(boolean canMoveGreaterTwoCell){
        canMoveGreaterTwoCell = canMoveGreaterTwoCell;
    }

    public int getRemainingHP(){
        return this.remainingHP;
    }

    public void setRemainingHP(int remainingHP){
        this.remainingHP = remainingHP;
    }

    public int getHP() {
        return HP;
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

    public int getDecreaseHPByAttack() {
        return decreaseHPByAttack;
    }

    //TODO
    //be in sadegi ha ham nist
    public void setDecreaseHPByAttack(int decreaseHPByAttack) {
        this.decreaseHPByAttack = decreaseHPByAttack;
    }

    public int getChangeHP() {
        return changeHP;
    }

    public void setChangeHP(int changeHP) {
        this.changeHP = changeHP;
    }

    public int getChangePower() {
        return changePower;
    }

    public void setChangePower(int changePower) {
        this.changePower = changePower;
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

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
