package Model.CollectionItem;

import Model.Enviroment.Cell;

import java.util.ArrayList;

public abstract class Card extends CollectionItem {
    private String ID;
    //MP sabete hamishe vase ye card?
    private int MP;

    public static Card getCardById(String ID){return null;}
    public static Card getCardByName(String name){return null;}
    abstract public void showCardInCollection();
    abstract public void showCardInBattle();
    public ArrayList<Cell> getImpactCell(){ return null;}
    abstract public ArrayList<Cell> findImpactCell();
    abstract public ArrayList<Cell> findImpactArea();

    //Here is Setters && Getters
    public void setMP(int MP){
        this.MP = MP;
    }

    public int getMP(){
        return this.MP;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
