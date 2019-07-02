package Model.CollectionItem;

import Controller.*;
import Model.Enviroment.Cell;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import Controller.Battle;
import javafx.scene.control.Label;

public class Spell extends Card {
    private int Mp, price;

    public void cellImpact(){}

    public static Spell createSpell(String spellName, String playerName) throws FileNotFoundException {
        String address = "Data/CollectionItem/Spell/" + spellName + ".json";
        Spell spell = (Spell) Application.readJSON(Spell.class, address);

        int numberOfThisSpellType = 0;
        ArrayList<Spell> allSpells = CollectionItem.getAllSpells();
        for(Spell usedSpell : allSpells){
            if(usedSpell.getName().equals(spellName))
                numberOfThisSpellType++;
        }
        String ID = playerName + "_" + spellName + "_" + numberOfThisSpellType;
        spell.setID(ID);
        CollectionItem.addSpellToAllSpells(spell);
        return spell;
    }

    @Override
    public void showCardInBattle() {
        System.out.println("Spell:");
        System.out.println("Name: " + this.getName());
        System.out.println("MP: " + this.getMP());
        System.out.println("Cost: " + this.getPrice());
        System.out.println("Desc: " + this.getDescription());
    }

    @Override
    public ArrayList<Label> getInfo() {
        ArrayList<Label> labels = new ArrayList<>();

        Label nameLabel = new Label("NAME : " + this.getName());
        labels.add(nameLabel);

        Label MPLabel = new Label("MP : " + this.getMP());
        labels.add(MPLabel);

        Label descLabel = new Label("DESC : " + this.getDescription());
        labels.add(descLabel);

        Label priceLabel = new Label("PRICE : " + this.getPrice());
        labels.add(priceLabel);

        return labels;
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
