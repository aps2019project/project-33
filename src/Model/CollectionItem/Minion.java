package Model.CollectionItem;

import Controller.Application;
import Model.Enviroment.Cell;
import javafx.scene.control.Label;

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
    public ArrayList<Label> getInfo() {
        ArrayList<Label> labels = new ArrayList<>();

        Label nameLabel = new Label("NAME : " + this.getName());
        labels.add(nameLabel);

        Label APLabel = new Label("AP : " + this.getAP());
        labels.add(APLabel);

        Label HPLabel = new Label("HP : " + this.getHP());
        labels.add(HPLabel);

        Label specialPowerLabel = new Label("SPECIAL POWER : " + this.getDescription());
        labels.add(specialPowerLabel);

        Label MPLabel = new Label("MP : " + this.getMP());
        labels.add(MPLabel);

        Label priceLabel = new Label("PRICE : " + this.getPrice());
        labels.add(priceLabel);

        return labels;
    }

    public boolean isHaveNefrineMarg() {
        return haveNefrineMarg;
    }

    public void setHaveNefrineMarg(boolean haveNefrineMarg) {
        this.haveNefrineMarg = haveNefrineMarg;
    }


}
