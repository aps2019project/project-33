package Model.Enviroment;

import Model.Buffs.Buff;
import Model.CollectionItem.CollectionItem;
import Model.CollectionItem.Item;
import Model.CollectionItem.LivingCard;

import java.util.ArrayList;

public class Cell {
    private int x, y;
    private LivingCard livingCard;
    private ArrayList<Buff> effects = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private boolean haveFlag = false;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isHaveFlag() {
        return haveFlag;
    }

    public void setHaveFlag(boolean haveFlag) {
        this.haveFlag = haveFlag;
    }

    //Here is Setters && Getters
    {
        this.livingCard = null;
    }


    public void insertCard(String livingCardID){
        LivingCard livingCard = CollectionItem.getLivingCardByID(livingCardID);
        this.livingCard = livingCard;
        for(Item item : this.items)
            livingCard.getItems().add(item);
        this.items.clear();
        return;
    }

    public void removeCard(){
        this.livingCard = null;
        return;
    }

    public void addEffect(Buff buff){
        this.effects.add(buff);
        return;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public LivingCard getLivingCard() {
        return livingCard;
    }

    public void setLivingCard(LivingCard livingCard) {
        this.livingCard = livingCard;
    }

    public ArrayList<Buff> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Buff> effects) {
        this.effects = effects;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
