package Model.CollectionItem;

import Controller.AttackArea;
import Controller.Battle;
import Controller.Impact;
import Model.Collection;
import Model.Enviroment.Cell;
import Model.Enviroment.Map1;

import java.util.ArrayList;

public class Minion extends LivingCard {

    private boolean nefrineMarg;

    public void readInformation(){
        //TODO
    }

    /*public static Minion createMinion(String playerName, String minionName){
        Minion minion = loadMinionFromJsonFile(minionName);
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
*/

    @Override
    public void showCardInCollection() {

    }

    @Override
    public void showCardInBattle() {
        //decreaseHPByAttack hamun attack powere?
        System.out.println(this.getID() + ": " + this.getName() + ", health : " + this.getHP() + ", location : (" +
                this.getPositionRow() + ", " + this.getPositionColumn() + "), power : " + this.getDecreaseHPByAttack());
    }

  //  public ArrayList<Cell> findCellOfSpecialPower() {

//        return  AttackArea.getCellsOfSpecialPower(this);
//    }

    public void doSpecialPower(String opponentID){
        if(getCoolDown() > 0){
            System.out.println("Special Power is not ready yet !!");
            return;
        }

        LivingCard opponentCard = CollectionItem.getLivingCardByID(opponentID);

        boolean canSpecialAttackToOpponent = false;
        ArrayList<Cell> impactCells = this.findImpactCellOfSpecialPower();
        for(Cell cell : impactCells)
            if(cell.getX() == opponentCard.getPositionRow() && cell.getY() == opponentCard.getPositionColumn())
                canSpecialAttackToOpponent = true;

        if(!canSpecialAttackToOpponent){
            System.out.println("Oppnent Card is not in special attack impact area");
        }

        Impact.specialPower(this, opponentCard);

        setCoolDown(CollectionItem.readCoolDownTime(this));

    }

    private ArrayList<Cell> findImpactCellOfSpecialPower() {
        return null;
    }

    //deghat konim description hamoon special power o in harfas
    @Override
    public String getInfo() {
        String info = "Type : Minion - Name : " + this.getName() + " - Class: " + this.getClass() + " - AP : " +
                this.getDecreaseHPByAttack() + " - HP : " + this.getHP() + " - MP : " + this.getMP() + " - Special power : "
                + this.getDescription();
        return info;
    }

    public boolean isNefrineMarg() {
        return nefrineMarg;
    }

    public void setNefrineMarg(boolean nefrineMarg) {
        this.nefrineMarg = nefrineMarg;
    }

    public void nefrineMarg(Map1 map){
        //TODO
    }
}
