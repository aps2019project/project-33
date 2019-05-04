package Controller;

import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Player;

import java.util.ArrayList;

public class AttackArea {

    public static ArrayList<Cell> findMeleeAttackArea(Battle battle, LivingCard attackingCard){


        return null;
    }

    public static ArrayList<Cell> findRangedAttackArea(Battle battle, LivingCard attacikingCard){


        return null;
    }

    public static ArrayList<Cell> findHybridAttackArea(Battle battle, LivingCard attackingCard){


        return null;
    }

    public static ArrayList<Cell> getImpactCellsOfSpecialPower(LivingCard livingCard) {


        return null;
    }

    public static ArrayList<Cell> getImpactCellsOfAttack(LivingCard livingCard) {

        return null;
    }

    public static ArrayList<Cell> getImpactCellsOfCounterAttack(LivingCard livingCard) {

        return null;
    }

    public static ArrayList<Cell> getImpactCellsOfSpell(Spell spell, Cell coordination, Battle battle) {
        ArrayList<Cell> impactCells = new ArrayList<>();

        Information information = spell.getInformation();
        information.readInformation();

        if(information.isCellImpact()){
            if(information.)
        }
        else{
            if(information.isEnemyImpact())
                getCells(impactCells, information, battle.getPlayerOff());
            if(information.isUsImpact())
                getCells(impactCells, information, battle.getPlayerOn());
        }
        return impactCells;
    }

    private static void getCells(ArrayList<Cell> impactCells, Information information, Player player) {
        if(information.isHeroImpact())
            impactCells.addAll(addCellOfLivingCard(player, (LivingCard) new Hero()));
        if(information.isHeroImpact())
            impactCells.addAll(addCellOfLivingCard(player, (LivingCard) new Minion()));
    }

    private static ArrayList<Cell> addCellOfLivingCard(Player player, LivingCard livingCard){
        ArrayList<Cell> cellOfHero = new ArrayList<>();
        for(CollectionItem collectionItem : player.getUsedCards()){
            if(collectionItem.getClass().equals(livingCard.getClass())){
                LivingCard livingCard1 = (LivingCard) collectionItem;
                if(livingCard1.isAlive())
                    cellOfHero.add(livingCard1.getCell());
            }
        }
        return cellOfHero;
    }

}

