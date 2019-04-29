package Model.CollectionItem;

import Model.Buffs.Buff;
import Model.Enviroment.Cell;

import java.util.ArrayList;

abstract public class LivingCard extends Card {
    private int HP, price, rangeOfAttack, decreaseHPByAttack, changeHP, changePower, numberOfSameTypeInComboAttack,
    changeRangeOfAttack, numberOfDamaged;
    private String counterAttackType, type;
    private ArrayList<Buff> effects;
    private boolean canCounterAttack, canMoveOrAttack;

    public void addNewBuff(Buff buff){}
    public void deleteBuff(Buff buff){}
    public void attack(String opponentID){}
    public void defend(String opponentID){}
    public void move(Cell cell){}
    abstract void doSpecialPower();
    public void counterAttack(){}

    // Here is Setters && Getters

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}
