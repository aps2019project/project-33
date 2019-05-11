package Controller;

import Model.CollectionItem.*;
import Model.Enviroment.Cell;
import Model.Enviroment.Map1;
import Model.Player;

import java.util.ArrayList;

public class AttackArea {
    //Inja moshakhas mikone ke che khoone hayi ro az cell ha migire, mostaghel az in ke che type i ro lazem dare

    public static ArrayList<Cell> getAllArea(Battle battle) {
        Map1 map = battle.getMap();
        return map.getCells();
    }

    public static ArrayList<Cell> getCellsInArea(Cell sourceCell, int maxDistance, Battle battle) {
        ArrayList<Cell> cells = new ArrayList<>();
        Map1 map = battle.getMap();
        for (Cell cell : map.getCells()) {
            if (AttackArea.distance(cell, sourceCell) > maxDistance)
                continue;
            cells.add(cell);
        }
        return unique(cells);
    }

    public static ArrayList<Cell> getNeighbors(Cell sourceCell, Battle battle) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        Map1 map = battle.getMap();
        for (Cell cell : map.getCells()) {
            if (!isNeighbor(cell, sourceCell))
                continue;
            neighbors.add(cell);
        }
        return unique(neighbors);
    }

    // in ja baraye spell e o hamaro satisfy mikone
    public static ArrayList<Cell> getCellsOfColumn(Cell sourceCell, Battle battle) {
        ArrayList<Cell> cellsOfColumn = new ArrayList<>();
        Map1 map = battle.getMap();
        for (Cell cell : map.getCells()) {
            if (cell.getY() != sourceCell.getY())
                continue;
            cellsOfColumn.add(cell);
        }
        return unique(cellsOfColumn);
    }

    public static ArrayList<Cell> getCellsOfRow(Cell sourceCell, Battle battle) {
        ArrayList<Cell> cellsOfRow = new ArrayList<>();
        Map1 map = battle.getMap();
        for (Cell cell : map.getCells()) {
            if (cell.getX() != sourceCell.getX())
                continue;
            cellsOfRow.add(cell);
        }
        return unique(cellsOfRow);
    }

    public static ArrayList<Cell> getSquareOfCells(Cell sourceCell, Battle battle, int length) {
        ArrayList<Cell> squareOfCells = new ArrayList<>();
        int x = sourceCell.getX(), y = sourceCell.getY();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int newX = x + i;
                int newY = y + j;
                Cell cell = battle.getMap().getCellByCoordination(newX, newY);
                if (cell == null)
                    continue;
                squareOfCells.add(cell);
            }
        }
        return unique(squareOfCells);
    }

    private static int distance(Cell cell1, Cell cell2) {
        int x1 = cell1.getX(), y1 = cell1.getY();
        int x2 = cell2.getX(), y2 = cell2.getY();
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static boolean isNeighbor(Cell cell1, Cell cell2) {
        int x1 = cell1.getX(), y1 = cell1.getY();
        int x2 = cell2.getX(), y2 = cell2.getY();
        if (distance(cell1, cell2) == 0)
            return false;
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
        ArrayList<Cell> cellsOfHero = new ArrayList<>();
        for (CollectionItem collectionItem : player.getAliveCards()) {
            if (collectionItem.getClass().equals(livingCard.getClass())) {
                LivingCard livingCard1 = (LivingCard) collectionItem;
                cellsOfHero.add(livingCard1.getCell());
            }
        }
        return unique(cellsOfHero);
    }

    // tamoom shod :D

    //in ja 3 ta type e hamleye mohem ro joda gane zadim, har chand ahamiate ziadi nadaran

    public static ArrayList<Cell> findMeleeAttackArea(Battle battle, LivingCard attackingCard) {
        return unique(getNeighbors(attackingCard.getCell(), battle));
    }

    public static ArrayList<Cell> findHybridAttackArea(Battle battle, LivingCard attackingCard) {
        ArrayList<Cell> hybridCells = getCellsInArea(attackingCard.getCell(), attackingCard.getRangeOfAttack(), battle);
        hybridCells.remove(attackingCard.getCell());
        return unique(hybridCells);
    }

    public static ArrayList<Cell> findRangedAttackArea(Battle battle, LivingCard attackingCard) {
        ArrayList<Cell> impactedCells = new ArrayList<>();
        impactedCells.addAll(findHybridAttackArea(battle, attackingCard));
        for (Cell cell : findMeleeAttackArea(battle, attackingCard))
            impactedCells.remove(cell);
        return unique(impactedCells);
    }

    //

    public static ArrayList<Cell> getimpactCellsOfSpellOfHero(Hero hero, Battle battle) {
        ArrayList<Cell> impactedCells = new ArrayList<>();
        Information information = hero.getInformation();

        if (information.isImpactItself())
            impactedCells.add(hero.getCell());
        if (information.isImpactAllArea())
            impactedCells.addAll(getAllArea(battle));
        if (information.isImpactRow())
            impactedCells.addAll(getCellsOfRow(hero.getCell(), battle));

        ArrayList<Cell> cellsOfLivingCards = new ArrayList<>();

        if (information.isEnemyImpact())
            cellsOfLivingCards.addAll(getCells(information, battle.getPlayerOff()));
        if (information.isUsImpact())
            cellsOfLivingCards.addAll(getCells(information, battle.getPlayerOn()));

        return unique(merge(impactedCells, cellsOfLivingCards));
    }

    public static ArrayList<Cell> getImpactCellsOfAttack(LivingCard livingCard, Battle battle) {
        ArrayList<Cell> impactedCells = new ArrayList<>();
        if (livingCard.getCounterAttackType().equals("hybrid"))
            impactedCells.addAll(findHybridAttackArea(battle, livingCard));
        if (livingCard.getCounterAttackType().equals("melee"))
            impactedCells.addAll(findMeleeAttackArea(battle, livingCard));
        if (livingCard.getCounterAttackType().equals("ranged"))
            impactedCells.addAll(findRangedAttackArea(battle, livingCard));

        return unique(impactedCells);
    }

    public static ArrayList<Cell> getImpactCellsOfCounterAttack(LivingCard livingCard, Battle battle) {
        return unique(AttackArea.getImpactCellsOfAttack(livingCard, battle));
    }


    public static ArrayList<Cell> getImpactCellsOfSpecialPower(Minion minion, Battle battle) {
        Information information = minion.getInformation();
        if (!information.isSpecialMinion()) {
            return getImpactCellsOfAttack((LivingCard) minion, battle);
        }

        ArrayList<Cell> impactedCells = new ArrayList<>();

        if (information.isImpactNeighbors())
            impactedCells.addAll(getNeighbors(minion.getCell(), battle));
        if (information.isImpactArea())
            impactedCells.addAll(getCellsInArea(minion.getCell(), information.getDistanceOfImpactArea(), battle));
        if (information.isImpactItself())
            impactedCells.add(minion.getCell());
        if (information.isImpactAllArea())
            impactedCells.addAll(getAllArea(battle));

        ArrayList<Cell> cellsOfLivingCards = new ArrayList<>();

        if (information.isEnemyImpact())
            cellsOfLivingCards.addAll(getCells(information, battle.getPlayerOff()));
        if (information.isEnemyImpact())
            cellsOfLivingCards.addAll(getCells(information, battle.getPlayerOn()));

        return unique(merge(impactedCells, cellsOfLivingCards));
    }

    public static ArrayList<Cell> getImpactCellsOfSpell(Spell spell, Cell cell, Battle battle) {
        ArrayList<Cell> impactedCellsOfLivingCards = new ArrayList<>();

        Information information = spell.getInformation();

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
            if (information.isImpactRow())
                impactedCells.addAll(getCellsOfRow(cell, battle));
            if (information.isImpactNeighbors())
                impactedCells.addAll(getNeighbors(cell, battle));
            if (information.isKingsGuard()) {
                impactedCells = getNeighbors(battle.getPlayerOn().getHeroPosition(), battle);
            }
            impactedCellsOfLivingCards = merge(impactedCells, impactedCellsOfLivingCards);
        }
        return unique(impactedCellsOfLivingCards);
    }

    private static ArrayList<Cell> unique(ArrayList<Cell> listOfCells) {
        ArrayList<Cell> uniquedList = new ArrayList<>();
        for(Cell cell : listOfCells) {
            if (uniquedList.contains(cell))
                continue;
            uniquedList.add(cell);
        }
        return uniquedList;
    }

    // tabe haye komaki

    public static ArrayList<Cell> merge(ArrayList<Cell> impactedCells, ArrayList<Cell> impactedCellsOfLivingCards) {
        ArrayList<Cell> result = new ArrayList<>();
        for (Cell cell : impactedCells)
            if (impactedCellsOfLivingCards.contains(cell))
                result.add(cell);
        return result;
    }

    public static ArrayList<Cell> getImpactCellsOfItem(Item item, Battle battle) {
        ArrayList<Cell> impactCells = new ArrayList<>(), result = new ArrayList<>();
        Information information = item.getInformation();

        if (information.isEnemyImpact())
            impactCells.addAll(getCells(information, battle.getPlayerOff()));
        if (information.isUsImpact())
            impactCells.addAll(getCells(information, battle.getPlayerOn()));
        for (Cell cell : impactCells) {
            LivingCard livingCard = cell.getLivingCard();
            if (information.isForHybrid() && livingCard.getInformation().isCanDoHybridAttack())
                result.add(cell);
            if (information.isForMelee() && livingCard.getInformation().isCanDoMeleeAttack())
                result.add(cell);
            if (information.isForRange() && livingCard.getInformation().isCanDoRangedAttack())
                result.add(cell);
        }
        return result;
    }

}

