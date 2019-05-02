package Model.CollectionItem;

import Model.Enviroment.Cell;

import java.util.ArrayList;

public abstract class Card extends CollectionItem {
    private String ID;
    //MP sabete hamishe vase ye card?
    private int MP;

    abstract public void showCardInBattle();

    public void showCardInCollection(){
        System.out.println(getInfo() + "\n");
    }

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
