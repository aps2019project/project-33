package Controller;

import Model.CollectionItem.Hero;
import Model.CollectionItem.LivingCard;
import Model.CollectionItem.Spell;
import Model.Enviroment.Cell;

import java.util.ArrayList;

public class AttackArea {
    private boolean isEnemyImpact;
    private boolean isMultilpleImpact;
    private boolean isMinionImpact;
    private boolean isHeroImpact;
    private boolean cellImpact;
    private boolean isKingsGuard;

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

    public static ArrayList<Cell> getImpactCellsOfSpell(Spell spell, Cell coordination) {

        return null;
    }
}
