package Model.CollectionItem;

import Controller.AttackArea;
import Controller.Impact;
import Model.Enviroment.Cell;

import java.util.ArrayList;

public class Spell extends Card {
    private int Mp, price;

    public void impactSpell(Cell cell){
        Impact.impactSpell(this, cell);
    }
    public ArrayList<Cell> findImapctCell(Cell coordination){
        return AttackArea.getImpactCellsOfSpell(this, coordination);
    }

    public void multipleImpact(){}
    public void minionImpact(){}
    public void HeroImpact(){}
    public void cellImpact(){}
    public void kingsGurad(){}
    public void doAllKindOfAttacks(){}


    @Override
    public void showCardInBattle() {
        //TODO
    }

    @Override
    public String getInfo() {
        String info;
        info = "Type : Spell - Name : " + this.getName() + " - MP : " + this.getMp() + " - Desc : " + this.getDescription();
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
