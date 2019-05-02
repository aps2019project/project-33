package Model.CollectionItem;

import Controller.AttackArea;
import Controller.Impact;
import Model.Enviroment.Cell;

import java.util.ArrayList;

public class Hero extends LivingCard {

    public void impactSpell(String opponentID){
        Impact.impactSpellOfHero(opponentID);
    }

    @Override
    public void showCardInBattle(){
        System.out.println("remaining HP : " + this.getRemainingHP() + " attack power : " + this.getDecreaseHPByAttack()
                + " required mana : " + this.getMP() + this.getDescription());
        //TODO
    }

    //deghat konim ke description baraye hero hamoon tozihare special power e !!
    @Override
    public String getInfo() {
        String info = "Name : " + this.getName() + " - AP : " + this.getDecreaseHPByAttack() + " - HP : "
                + this.getHP() + " Class : " + this.getClass() + " Special power: " + this.getDescription();
        return info;
    }

}
