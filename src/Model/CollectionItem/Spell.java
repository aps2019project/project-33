package Model.CollectionItem;

import Model.Enviroment.Cell;

import java.util.ArrayList;

public class Spell extends Card {
    private int Mp, price;

    public void impactEnemy(){}
    public void multipleImpact(){}
    public void minionImpact(){}
    public void HeroImpact(){}
    public void cellImpact(){}
    public void kingsGurad(){}
    public void doAllKindOfAttacks(){}
    public ArrayList<Cell> findImapctHeros(){return null;}
    @Override
    public void showCardInCollection() {}
    @Override
    public void showCardInBattle() {}
    @Override
    public ArrayList<Cell> findImpactCell() {return null;}
    @Override
    public ArrayList<Cell> findImpactArea() {return null;}
    @Override
    public void doImpact() {}

    @Override
    public String getInfo() {
        String info;
        info = "Type : Spell - Name : " + this.getName() + " - MP : " + this.getMp() + " - Desc : " + this.getDesc();
        return info;
    }


    //Here is Setters && Getters
    public int getMp() {
        return Mp;
    }

    public void setMp(int mp) {
        Mp = mp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
