package Model.CollectionItem;

import Controller.Application;
import Model.Enviroment.Cell;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Minion extends LivingCard {
    private ArrayList<LivingCard> attackedLivingCards = new ArrayList<>();
    private boolean haveNefrineMarg;
    private String comboAbility;

    public String getComboAbility(){
        return this.comboAbility;
    }

    public void addAttackedLivingCard (LivingCard livingCard){
        this.attackedLivingCards.add(livingCard);
    }

    public ArrayList<LivingCard> getAttackedLivingCards(){
        return this.attackedLivingCards;
    }

    public static Minion createMinion(String minionName, String playerName) throws FileNotFoundException {
        String address = "Data/CollectionItem/Minion/" + minionName + ".json";
        Minion minion = (Minion) Application.readJSON(Minion.class, address);

        int numberOfThisMinionType = 0;
        ArrayList<LivingCard> usedLivingCards = CollectionItem.getAllLivingCards();
        for(LivingCard livingCard : usedLivingCards){
            if(livingCard instanceof Minion){
                if(livingCard.getName().equals(minionName))
                    numberOfThisMinionType++;
            }
        }
        String ID = playerName + "_" + minionName + "_" + numberOfThisMinionType;
        minion.setID(ID);

        CollectionItem.addLivingCardToAllLivingCards(minion);
        return minion;
    }


    @Override
    public void showCardInBattle() {
        System.out.println("Minion:");
        System.out.println("Name: " + this.getName());
        System.out.println("HP: " + this.getHP() + " AP: " + this.getAP() + " MP: " + this.getMP());
        System.out.println("Range: " + this.getRangeOfAttack());
        System.out.println("Combo-ability:" + this.getComboAbility());
        System.out.println("Cost: " + this.getPrice());
        System.out.println("Desc: " + this.getDescription());
    }


    private ArrayList<Cell> findImpactCellOfSpecialPower() {
        return null;
    }

    //deghat konim description hamoon special power o in harfas
    @Override
    public String getInfo() {
        String info = "Type : Minion - Name : " + this.getName() + " - ID: " + this.getID() + " - Class: " + this.getClass() + " - AP : " +
                this.getAP() + " - HP : " + this.getHP() + " - MP : " + this.getMP() + " - Special power : "
                + this.getDescription();
        return info;
    }

    public boolean isHaveNefrineMarg() {
        return haveNefrineMarg;
    }

    public void setHaveNefrineMarg(boolean haveNefrineMarg) {
        this.haveNefrineMarg = haveNefrineMarg;
    }


}
