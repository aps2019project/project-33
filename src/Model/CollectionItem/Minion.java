package Model.CollectionItem;

import Model.Enviroment.Cell;

import java.util.ArrayList;

public class Minion extends LivingCard {
    @Override
    void doSpecialPower() {

    }

    @Override
    public void showCardInCollection() {

    }

    @Override
    public void showCardInBattle() {

    }

    @Override
    public ArrayList<Cell> findImpactCell() {
        return null;
    }

    @Override
    public ArrayList<Cell> findImpactArea() {
        return null;
    }

    @Override
    public void doImpact() {

    }

    @Override
    public String getInfo() {
        String info = "Type : Minion - Name : " + this.getName() + " - Class: " + this.getClass() + " - AP : " +
                this.getAP() + " - HP : " + this.getHP() + " - MP : " + this.getMP() + " - Special power : "
                + this.getSpecialPower();
        return info;
    }
}
