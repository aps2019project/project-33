package Model;

import Controller.Battle;
import Model.Buffs.ManaBuff;

import java.io.Serializable;
import java.util.ArrayList;

public class Mana implements Serializable {
    private int currentMana;
    private int maximumMana;
    private int changeManaByItem = 0;
    private ArrayList<ManaBuff> manaBuffs = new ArrayList<>();

    public Boolean decreaseMana (int spendMana) {
        if (currentMana < spendMana) {
            return false;
        } else {
            currentMana -= spendMana;
            return true;
        }
    }

    //todo in ja momkene bug bokhore, nmd chera
    public void configureMana(){
        if (maximumMana < 9)
            maximumMana++;
        changeManaByItem = 0;
        for(int i = manaBuffs.size() - 1; i > -1; i--)
            manaBuffs.get(i).effect(this);
        currentMana = Integer.min(maximumMana + changeManaByItem, 9);
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

    public void increaseChangeManaByItem(int amount){
        this.changeManaByItem += amount;
    }

    public void increaseMaximumMana(int amountOfIncreaseMana) {
        this.maximumMana += amountOfIncreaseMana;
    }

    public void addManaBuff(ManaBuff manaBuff){
        this.manaBuffs.add(manaBuff);
    }

    public ArrayList<ManaBuff> getManaBuffs() {
        return manaBuffs;
    }
}
