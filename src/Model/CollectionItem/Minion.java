package Model.CollectionItem;

import Controller.AttackArea;
import Controller.Impact;
import Model.Enviroment.Cell;

import java.util.ArrayList;

public class Minion extends LivingCard {

    @Override
    public void showCardInCollection() {

    }

    @Override
    public void showCardInBattle() {
        //decreaseHPByAttack hamun attack powere?
        System.out.println(this.getID() + ": " + this.getName() + ", health : " + this.getHP() + ", location : (" +
                this.getPositionRow() + ", " + this.getPositionColumn() + "), power : " + this.getDecreaseHPByAttack());
    }

    public ArrayList<Cell> findImpactCellOfSpecialPower() {
        return  AttackArea.getImapctCellsOfSpecialPower(this);
    }

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

    //deghat konim description hamoon special power o in harfas
    @Override
    public String getInfo() {
        String info = "Type : Minion - Name : " + this.getName() + " - Class: " + this.getClass() + " - AP : " +
                this.getDecreaseHPByAttack() + " - HP : " + this.getHP() + " - MP : " + this.getMP() + " - Special power : "
                + this.getDescription();
        return info;
    }
}
