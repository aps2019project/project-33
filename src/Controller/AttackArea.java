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

    public static ArrayList<Cell> getCellsOfPlayer(Information information, Player player) {
        ArrayList<Cell> impactCells = new ArrayList<>();
        if (information.isHeroImpact())
            impactCells.addAll(getCellOfLivingCard(player, (LivingCard) new Hero()));
        if (information.isMinionImpact())
            impactCells.addAll(getCellOfLivingCard(player, (LivingCard) new Minion()));
        return unique(impactCells);
    }

    public static ArrayList<Cell> getCellOfLivingCard(Player player, LivingCard livingCard) {
        ArrayList<Cell> cellsOfLivingCards = new ArrayList<>();
        for (CollectionItem collectionItem : player.getAliveCards()) {
            if (collectionItem.getClass().equals(livingCard.getClass())) {
                LivingCard livingCard1 = (LivingCard) collectionItem;
                cellsOfLivingCards.add(livingCard1.getCell());
            }
        }
        return unique(cellsOfLivingCards);
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

    public static ArrayList<Cell> getImpactCellsOfAttack(LivingCard livingCard, Battle battle) {
        ArrayList<Cell> impactedCells = new ArrayList<>();
        if (livingCard.getInformation().isCanDoHybridAttack())
            impactedCells.addAll(findHybridAttackArea(battle, livingCard));
        if (livingCard.getInformation().isCanDoMeleeAttack())
            impactedCells.addAll(findMeleeAttackArea(battle, livingCard));
        if (livingCard.getInformation().isCanDoRangedAttack())
            impactedCells.addAll(findRangedAttackArea(battle, livingCard));

        return unique(impactedCells);
    }

    public static ArrayList<Cell> getImpactCellsOfCounterAttack(LivingCard livingCard, Battle battle) {
        return unique(AttackArea.getImpactCellsOfAttack(livingCard, battle));
    }

    private static ArrayList<Cell> unique(ArrayList<Cell> listOfCells) {
        ArrayList<Cell> uniqueList = new ArrayList<>();
        for(Cell cell : listOfCells) {
            if (uniqueList.contains(cell))
                continue;
            uniqueList.add(cell);
        }
        return uniqueList;
    }

    public static ArrayList<Cell> merge(ArrayList<Cell> impactedCells, ArrayList<Cell> impactedCellsOfLivingCards) {
        ArrayList<Cell> result = new ArrayList<>();
        for (Cell cell : impactedCells)
            if (impactedCellsOfLivingCards.contains(cell))
                result.add(cell);
        return unique(result);
    }

    ///////////////////////////////////////////////////////

    public static ArrayList<Cell> getImpactCells(CollectionItem collectionItem, Cell cell, Battle battle) {
        ArrayList<Cell> impactedCellsOfLivingCards = new ArrayList<>();
        ArrayList<Cell> impactedCells = new ArrayList<>();

        Information information = collectionItem.getInformation();

        if (information.isEnemyImpact())
            impactedCellsOfLivingCards.addAll(getCellsOfPlayer(information, battle.getPlayerOff()));
        if (information.isUsImpact())
            impactedCellsOfLivingCards.addAll(getCellsOfPlayer(information, battle.getPlayerOn()));

        if (information.isLocationLimit()) {
            if (information.isSquareOfCellsImpact())
                impactedCells.addAll(getSquareOfCells(cell, battle, information.getLengthOfSquareOfCellsImpact()));
            if (information.isImpactColumn())
                impactedCells.addAll(getCellsOfColumn(cell, battle));
            if (information.isImpactRow())
                impactedCells.addAll(getCellsOfRow(cell, battle));
            if (information.isImpactNeighbors())
                impactedCells.addAll(getNeighbors(cell, battle));
            if (information.isKingsGuard()) {
                //todo, exception mikhore
                System.out.println(battle.getPlayerOn().getHeroPosition());
                impactedCells = getNeighbors(battle.getPlayerOn().getHeroPosition(), battle);
            }
            if(information.isImpactAllArea()){
                impactedCells.addAll(getAllArea(battle));
            }
            impactedCells = unique(impactedCells);
        }

        ArrayList<Cell> result = new ArrayList<>();
        result.addAll(impactedCells);
        result.addAll(impactedCellsOfLivingCards);
        result = unique(result);

        if(information.isLocationLimit())
            result = merge(result, impactedCells);
        if(information.isUsImpact() || information.isEnemyImpact())
            result = merge(result, impactedCellsOfLivingCards);
        if(information.isImpactItself())
            result.add(cell);
        return unique(result);
    }
}
