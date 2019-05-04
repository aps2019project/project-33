package Model.CollectionItem;

import Controller.Battle;
import Model.Enviroment.Cell;

import java.util.ArrayList;

public abstract class Card extends CollectionItem {
    private String ID;
    private Battle battle;
    //MP sabete hamishe vase ye card?
    private int MP;
    private int positionColumn;
    private int positionRow;


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

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
    public int getPositionColumn(){
        return this.positionColumn;
    }

    public void setPositionColumn(int positionColumn){
        this.positionColumn = positionColumn;
    }

    public int getPositionRow(){
        return this.positionRow;
    }

    public void  setPositionRow(int positionRow){
        this.positionRow = positionRow;
    }

}
