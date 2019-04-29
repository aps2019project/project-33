package Model;

import java.util.ArrayList;

public class Cell {
    private int x, y;
    private LivingCard livingCard;
    private ArrayList<Buff> effects;

    //Here is Setters && Getters

    public void insertCard(String livingCardID){
        LivingCard livingCard = CollectionItem.getLivingCardByID(livingCardID);
        this.livingCard = livingCard;
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
}
