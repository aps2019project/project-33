import java.util.ArrayList;

public abstract class Card extends CollectionItem {
    private String ID;

    public static Card getCardById(String ID){return null;}
    public static Card getCardByName(String name){return null;}
    abstract public void showCardInCollcetion();
    abstract public void showCardInBattle();
    public ArrayList<Cell> getImpactCell(){}
    abstract public ArrayList<Cell> findImpactCell();

    //Here is Setters && Getters

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
