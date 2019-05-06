package Model;

import Controller.Battle;

public class Mana {

    private int currentMana;
    private int maximumMana;
    private int changeManaByItem = 0;

    public Boolean decreaseMana (int spendMana) {
        if (currentMana < spendMana) {
            return false;
        } else {
            currentMana -= spendMana;
            return true;
        }
    }

    public void configueMana (Battle battle){
        if (maximumMana < 9) {
            maximumMana++;
        }
        if(battle.getNumberOfRounds() >= 3){
            maximumMana += changeManaByItem;
            changeManaByItem = 0;
        }
        currentMana = maximumMana;
    }

    public int getCurrentMana(){
        return currentMana;
    }

    public int getMaximumMana() {
        return maximumMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public void setMaximumMana(int maximumMana) {
        this.maximumMana = maximumMana;
    }

    public void increaseChangeManaByItem(){
        this.changeManaByItem ++;
    }

    public void increaseMaximumMana(int amountOfIncreaseMana) {
        this.maximumMana += amountOfIncreaseMana;
    }
}
