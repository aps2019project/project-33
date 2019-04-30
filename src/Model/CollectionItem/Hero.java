package Model.CollectionItem;

import Model.Enviroment.Cell;

import java.util.ArrayList;

public class Hero extends LivingCard {
    @Override
    void doSpecialPower() {

    }

    @Override
    public void showCardInCollection() {
        System.out.println(getInfo() + "\n");
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
        String info = "Name : " + this.getName() + " - AP : " + this.getAP() + " - HP : "
                + this.getHP() + " Class : " + this.getClass() + " Special power: " + this.getSpecialPower();
        return info;
    }
}
