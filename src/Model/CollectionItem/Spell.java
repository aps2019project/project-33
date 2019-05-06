package Model.CollectionItem;

import Controller.AttackArea;
import Controller.Battle;
import Controller.Impact;
import Model.Enviroment.Cell;

import java.util.ArrayList;
import Controller.Battle;

public class Spell extends Card {
    private int Mp, price;

    public void impactSpell(Cell cell, Battle battle){
        Impact.impactSpell(this, cell, battle);
    }
    public ArrayList<Cell> findImapctCell(Cell coordination){
        return AttackArea.getImpactCellsOfSpell(this, coordination, this.getBattle());
    }

    public void multipleImpact(){}
    public void minionImpact(){}
    public void HeroImpact(){}
    public void cellImpact(){}
    public void kingsGurad(){}
    public void doAllKindOfAttacks(){}

/*
    public static Spell createSpell(String playerName, String spellName){
        Spell spell = loadSpellFromJsonFile(spellName);
        int numberOfThisSpellType = 0;
        ArrayList<Spell> usedSpells = CollectionItem.getAllSpells();
        for(Spell usedSpell : usedSpells){
            if(usedSpell.getName().equals(spellName))
                numberOfThisSpellType++;
        }
        String ID = playerName + "_" + spellName + "_" + numberOfThisSpellType;
        spell.setID(ID);
        CollectionItem.addSpellToAllSpells(spell);
        return spell;
    }
*/

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
