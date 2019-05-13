package Controller;

import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Enviroment.Map1;
import Model.Player;

import java.util.ArrayList;
import java.util.Collection;

public class AttackArea {

    //Inja moshakhas mikone ke che khoone hayi ro az cell ha migire, mostaghel az in ke che type i ro lazem dare


    public static ArrayList<Cell> getAllArea(Battle battle) {
        ArrayList<Cell> allCells = new ArrayList<>();
        Map1 map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                Cell cell = map.getCellByCoordination(i, j);
                allCells.add(cell);
            }
        return allCells;
    }

    public static ArrayList<Cell> getCellsInArea(Cell cell, int maxDistance, Battle battle) {
        ArrayList<Cell> cells = new ArrayList<>();
        Map1 map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                if (AttackArea.distance(cell.getX(), cell.getY(), i, j) > maxDistance) continue;
                Cell cell1 = map.getCellByCoordination(i, j);
                cells.add(cell1);
            }
        return cells;
    }

    public static ArrayList<Cell> getNeighbors(Cell cell, Battle battle) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        Map1 map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                if(cell == null){
                    System.out.println("PASHMAM");
                }
                if (!isNeighbor(cell.getX(), cell.getY(), i, j))
                    continue;
                if(i == cell.getX() && j == cell.getY())
                    continue;
                Cell cell1 = map.getCellByCoordination(i, j);
                neighbors.add(cell1);
            }
        return neighbors;
    }

    // in ja baraye spell e o hamaro satisfy mikone
    public static ArrayList<Cell> getCellsOfColumn(Cell cell, Battle battle) {
        ArrayList<Cell> cellsOfColumn = new ArrayList<>();
        Map1 map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                Cell cell1 = map.getCellByCoordination(i, j);
                if (j == cell.getY())
                    cellsOfColumn.add(cell1);
            }
        return cellsOfColumn;
    }

    public static ArrayList<Cell> getCellsOfRow(Cell cell, Battle battle) {
        ArrayList<Cell> cellsOfRow = new ArrayList<>();
        Map1 map = battle.getMap();
        for (int i = 0; i < map.getHeight(); i++)
            for (int j = 0; j < map.getWidth(); j++) {
                Cell cell1 = map.getCellByCoordination(i, j);
                if (i == cell.getX())
                    cellsOfRow.add(cell1);
            }
        return cellsOfRow;
    }

    public static ArrayList<Cell> getSquareOfCells(Cell cell, Battle battle, int length) {
        ArrayList<Cell> squareOfCells = new ArrayList<>();
        int x = cell.getX(), y = cell.getY();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int newX = x + i;
                int newY = y + j;
                Cell cell1 = battle.getMap().getCellByCoordination(newX, newY);
                if (cell1 != null) {
                    squareOfCells.add(cell1);
                }
            }
        }
        return squareOfCells;
    }

    private static int distance(int i, int j, int x, int y) {
        return Math.abs(i - x) + Math.abs(j - y);
    }

    private static boolean isNeighbor(int x1, int y1, int x2, int y2) {
        if (Math.abs(x1 - x2) > 1) return false;
        if (Math.abs(y1 - y2) > 1) return false;
        return true;
    }

    // in ja ham tamoom mishe

    // in ja miad mige che noe LivingCard hayi tahte tasire in lanati hastan
    // albate Cell hashoono mige behemoon khodeshoono kar nadare

    public static ArrayList<Cell> getCells(Information information, Player player) {
        ArrayList<Cell> impactCells = new ArrayList<>();
        if (information.isHeroImpact())
            impactCells.addAll(getCellOfLivingCard(player, (LivingCard) new Hero()));
        if (information.isMinionImpact())
            impactCells.addAll(getCellOfLivingCard(player, (LivingCard) new Minion()));
        return impactCells;
    }

    public static ArrayList<Cell> getCellOfLivingCard(Player player, LivingCard livingCard) {
        ArrayList<Cell> cellOfHero = new ArrayList<>();
        for (CollectionItem collectionItem : player.getAliveCards()) {
            if (collectionItem.getClass().equals(livingCard.getClass())) {
                LivingCard livingCard1 = (LivingCard) collectionItem;
                cellOfHero.add(livingCard1.getCell());
            }
        }
        return cellOfHero;
    }

    // tamoom shod :D

    //in ja 3 ta type e hamleye mohem ro joda gane zadim, har chand ahamiate ziadi nadaran

    public static ArrayList<Cell> findMeleeAttackArea(Battle battle, LivingCard attackingCard) {
        return getNeighbors(attackingCard.getCell(), battle);
    }

    public static ArrayList<Cell> findHybridAttackArea(Battle battle, LivingCard attackingCard) {
        return getCellsInArea(attackingCard.getCell(), attackingCard.getRangeOfAttack(), battle);
    }

    public static ArrayList<Cell> findRangedAttackArea(Battle battle, LivingCard attackingCard) {
        ArrayList<Cell>  impactedCells = new ArrayList<>();
        impactedCells.addAll(findHybridAttackArea(battle, attackingCard));
        for(Cell cell : findMeleeAttackArea(battle, attackingCard))
            impactedCells.remove(cell);
        return impactedCells;
    }

    //

    public static ArrayList<Cell> getimpactCellsOfSpellOfHero(Hero hero, Battle battle){
        ArrayList<Cell> impactedCells = new ArrayList<>();
        Information information = hero.getInformation();

        if(information.isImpactItself())
            impactedCells.add(hero.getCell());
        if(information.isImpactAllArea())
            impactedCells.addAll(getAllArea(battle));
        if(information.isImpactRow())
            impactedCells.addAll(getCellsOfRow(hero.getCell(), battle));

        ArrayList<Cell> cellsOfLivingCards = new ArrayList<>();

        if(information.isEnemyImpact())
            cellsOfLivingCards.addAll(getCells(information, battle.getPlayerOff()));
        if(information.isUsImpact())
            cellsOfLivingCards.addAll(getCells(information, battle.getPlayerOn()));

        return merge(impactedCells, cellsOfLivingCards);
    }

    public static ArrayList<Cell> getImpactCellsOfAttack(LivingCard livingCard, Battle battle) {
        ArrayList<Cell> impactedCells = new ArrayList<>();
        Information information = livingCard.getInformation();

        if(information.isCanDoHybridAttack() || (livingCard.getCounterAttackType() != null &&livingCard.getCounterAttackType().equals("hybrid")))
            impactedCells.addAll(findHybridAttackArea(battle, livingCard));
        if(information.isCanDoMeleeAttack() || (livingCard.getCounterAttackType() != null && livingCard.getCounterAttackType().equals("melee")))
            impactedCells.addAll(findMeleeAttackArea(battle, livingCard));
        if(information.isCanDoRangedAttack() || (livingCard.getCounterAttackType() != null && livingCard.getCounterAttackType().equals("ranged")))
            impactedCells.addAll(findRangedAttackArea(battle, livingCard));

        return impactedCells;
    }

    public static ArrayList<Cell> getImpactCellsOfCounterAttack(LivingCard livingCard, Battle battle) {
        return AttackArea.getImpactCellsOfAttack(livingCard, battle);
    }


    public static ArrayList<Cell> getImpactCellsOfSpecialPower(Minion minion, Battle battle) {
        Information information = minion.getInformation();
        if(!information.isSpecialMinion()){
            return getImpactCellsOfAttack((LivingCard) minion, battle);
        }

        ArrayList<Cell> impactedCells = new ArrayList<>();

        if(information.isImpactNeighbors())
            impactedCells.addAll(getNeighbors(minion.getCell(), battle));
        if(information.isImpactArea())
            impactedCells.addAll(getCellsInArea(minion.getCell(), information.getDistanceOfImpactArea(), battle));
        if(information.isImpactItself())
            impactedCells.add(minion.getCell());
        if(information.isImpactAllArea())
            impactedCells.addAll(getAllArea(battle));

        ArrayList<Cell> cellsOfLivingCards = new ArrayList<>();

        if(information.isEnemyImpact())
            cellsOfLivingCards.addAll(getCells(information, battle.getPlayerOff()));
        if(information.isEnemyImpact())
            cellsOfLivingCards.addAll(getCells(information, battle.getPlayerOn()));

        return merge(impactedCells, cellsOfLivingCards);
    }

    public static ArrayList<Cell> getImpactCellsOfSpell(Spell spell, Cell cell, Battle battle) {
        ArrayList<Cell> impactedCellsOfLivingCards = new ArrayList<>();

        Information information = spell.getInformation();
        //information.readInformation();

        if (information.isEnemyImpact())
            impactedCellsOfLivingCards.addAll(getCells(information, battle.getPlayerOff()));
        if (information.isUsImpact())
            impactedCellsOfLivingCards.addAll(getCells(information, battle.getPlayerOn()));

        if (information.isCellImpact()) {
            ArrayList<Cell> impactedCells = new ArrayList<>();
            if (information.isSquareOfCellsImpact())
                impactedCells.addAll(getSquareOfCells(cell, battle, information.getLengthOfSquareOfCellsImpact()));
            if (information.isImpactColumn())
                impactedCells.addAll(getCellsOfColumn(cell, battle));
            if(information.isImpactRow())
                impactedCells.addAll(getCellsOfRow(cell, battle));
            if(information.isImpactNeighbors())
                impactedCells.addAll(getNeighbors(cell, battle));
            if(information.isKingsGuard()){
                impactedCells = getNeighbors(battle.getPlayerOn().getHeroPosition(), battle);
            }
            impactedCellsOfLivingCards = merge(impactedCells, impactedCellsOfLivingCards);
        }

        return impactedCellsOfLivingCards;
    }

    // tabe haye komaki

    public static ArrayList<Cell> merge(ArrayList<Cell> impactedCells, ArrayList<Cell> impactedCellsOfLivingCards) {
       ArrayList<Cell> result = new ArrayList<>();
        for(Cell cell : impactedCells)
            if(impactedCellsOfLivingCards.contains(cell))
                result.add(cell);
        return result;
    }

    public static ArrayList<Cell> getImpactCellsOfItem(Item item, Battle battle){
        ArrayList<Cell> impactCells = new ArrayList<>(), result = new ArrayList<>();
        Information information = item.getInformation();

        if(information.isEnemyImpact())
            impactCells.addAll(getCells(information, battle.getPlayerOff()));
        if(information.isUsImpact())
            impactCells.addAll(getCells(information, battle.getPlayerOn()));
        for(Cell cell : impactCells){
            LivingCard livingCard = cell.getLivingCard();
            if(information.isForHybrid() && livingCard.getInformation().isCanDoHybridAttack())
                result.add(cell);
            if(information.isForMelee() && livingCard.getInformation().isCanDoMeleeAttack())
                result.add(cell);
            if(information.isForRange() && livingCard.getInformation().isCanDoRangedAttack())
                result.add(cell);
        }
        return result;
    }

}

